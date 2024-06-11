package com.example.road.to.effective.snapshot.testing.lazycolumnscreen

/*
 * Copyright 2023 The Android Open Source Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

/*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams.RenderingMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameter.TestParameterValuesProvider
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.reflections.Reflections
import org.reflections.scanners.Scanners
import org.reflections.util.ConfigurationBuilder
import org.reflections.util.FilterBuilder
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.lang.reflect.Proxy
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.jvm.isAccessible

/**
 * A custom [PreviewParameterProvider] that provides a sequence of [T]s, each with a name.
 * If the name is null, the preview will be ignored for Screenshot tests.
 */
abstract class NamedPreviewParameterProvider<T> : PreviewParameterProvider<T> {
    abstract val nameToValue: Sequence<Pair<String?, T>>
    final override val values: Sequence<T>
        get() = nameToValue.map { it.second }
}

object PaparazziRuleProvider {
    fun get(
        deviceConfig: DeviceConfig = DeviceConfig.PIXEL_5,
    ) = Paparazzi(
        deviceConfig = deviceConfig,
        maxPercentDifference = 0.01,
        showSystemUi = false,
    )
}

/**
 * Automatically finds all [Preview] methods in the codebase and runs them as screenshot tests.
 */
@RunWith(TestParameterInjector::class)
class PreviewTests {

    @get:Rule
    val paparazzi: Paparazzi = PaparazziRuleProvider.get()

    @Test
    fun snapshot(
        @TestParameter(valuesProvider = ComposablePreviewProvider::class)
        composablePreview: ComposablePreview,
    ) {
        paparazzi.unsafeUpdateConfig(renderingMode = composablePreview.renderingMode)
        paparazzi.snapshot {
            composablePreview()
        }
    }

    /**
     * Provides a list of [ComposablePreview]s to be used as test parameters.
     */
    private object ComposablePreviewProvider : TestParameterValuesProvider {

        private const val APPLICATION_PACKAGE = "com.example.road.to.effective.snapshot.testing"

        override fun provideValues(): List<ComposablePreview> {
            val composablePreviews = mutableListOf<ComposablePreview>()

            findPreviewMethodsInCodebase()
                //.filterNot { method -> method.annotations.any { it is IgnoreScreenshotTest } }
                .onEach { if (Modifier.isPrivate(it.modifiers)) it.isAccessible = true }
                .forEach { method ->
                    val providerAnnotation = method.findPreviewParameterAnnotation()

                    if (providerAnnotation == null) {
                        // The [Preview] doesn't have a parameter annotated with [PreviewParameter],
                        // create a [ComposablePreview] with no parameters.
                        composablePreviews.add(method.toComposablePreview())
                    } else {
                        // Create an instance of the PreviewParameterProvider.
                        val provider = providerAnnotation.provider.createInstanceUnsafe()

                        // Get a sequence with the name and value for each parameter
                        // that the [Preview] should be called with.
                        val nameToValue = if (provider is NamedPreviewParameterProvider<*>) {
                            provider.nameToValue.mapNotNull { (name, value) ->
                                // ignore previews with a parameter that has null name
                                if (name == null) return@mapNotNull null

                                name to value
                            }
                        } else {
                            provider.values.mapIndexed { index, value ->
                                index.toString() to value
                            }
                        }

                        // Create a [ComposablePreview] for each name and value pair.
                        nameToValue.forEach { (nameSuffix, value) ->
                            composablePreviews.add(method.toComposablePreview(nameSuffix, value))
                        }
                    }
                }

            return composablePreviews
        }

        /**
         * Finds all [Preview] methods in the codebase.
         *
         * Additionally finds methods annotated with the [ThemePreviews] and [DevicePreviews]
         * multi-preview annotations specific to Now in Android.
         */
        private fun findPreviewMethodsInCodebase(): Set<Method> {
            val configuration = ConfigurationBuilder()
                .forPackage(APPLICATION_PACKAGE)
                .filterInputsBy(FilterBuilder().includePackage(APPLICATION_PACKAGE))
                .addScanners(Scanners.MethodsAnnotated)
            val reflections = Reflections(configuration)
            throw RuntimeException("size: " + reflections.getMethodsAnnotatedWith(Preview::class.java).size)
            return reflections.getMethodsAnnotatedWith(Preview::class.java)
        }

        /**
         * Finds the [PreviewParameter] annotation on the method's parameters.
         */
        private fun Method.findPreviewParameterAnnotation(): PreviewParameter? {
            return this.parameterAnnotations
                .flatMap { it.toList() }
                .find { it is PreviewParameter } as PreviewParameter?
        }

        /**
         * Creates an instance of the [PreviewParameterProvider] using the no-args constructor
         * even if the class or constructor is private.
         */
        private fun KClass<out PreviewParameterProvider<*>>.createInstanceUnsafe(): PreviewParameterProvider<*> {
            val noArgsConstructor = constructors
                .single { it.parameters.all(KParameter::isOptional) }
            noArgsConstructor.isAccessible = true
            return noArgsConstructor.call()
        }
    }
}

interface ComposablePreview {
    val renderingMode: RenderingMode

    @Composable
    operator fun invoke()
}

private const val NAME_SEPARATOR = "_"

/**
 * Creates a [ComposablePreview] from a [Method].
 * Because the method is a [Composable] function that has extra parameters added by the compiler
 * ([Composer] being one of them), we can't call it directly. Instead, we rely on
 * Java dynamic proxy to handle the call using a [ComposablePreviewInvocationHandler] instance.
 */
private fun Method.toComposablePreview(
    nameSuffix: String = "",
    parameter: Any? = ComposablePreviewInvocationHandler.NoParameter,
): ComposablePreview {
    val proxy = Proxy.newProxyInstance(
        ComposablePreview::class.java.classLoader,
        arrayOf(ComposablePreview::class.java),
        ComposablePreviewInvocationHandler(composableMethod = this, parameter = parameter),
    ) as ComposablePreview

    // Wrap the call to the proxy in an object so that we can override the toString method
    // to provide a more descriptive name for the test and resulting snapshot filename.
    return object : ComposablePreview by proxy {
        override fun toString(): String {
            return buildList<String> {
                add(declaringClass.simpleName)
                add(name)
                if (nameSuffix.isNotEmpty()) add(nameSuffix)
            }.joinToString(NAME_SEPARATOR)
        }
    }
}

/**
 * Used to handle calls to a [composableMethod].
 * If a [parameter] is provided, it will be used as the first parameter of the call.
 */
private class ComposablePreviewInvocationHandler(
    private val composableMethod: Method,
    private val parameter: Any?,
) : InvocationHandler {

    /**
     * Used to indicate that no parameter should be used when calling the [composableMethod].
     * We can't use null here as we might want to pass null as an actual parameter to a function.
     */
    object NoParameter

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        val safeArgs = args ?: emptyArray()
        val safeArgsWithParam = if (parameter != NoParameter) {
            arrayOf(parameter, *safeArgs)
        } else {
            safeArgs
        }
        return composableMethod.invoke(null, *safeArgsWithParam)
    }
}

 */