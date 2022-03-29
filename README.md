part of blog posts featured in</br>
<a href="https://androidweekly.net/issues/issue-479">
<img src="https://androidweekly.net/issues/issue-479/badge">
</a><a href="https://androidweekly.net/issues/issue-485">
<img src="https://androidweekly.net/issues/issue-485/badge">
</a>

# Road to effective snapshot testing </br>

![snapshotVsUiTests](https://user-images.githubusercontent.com/6097181/144911921-bae6182b-dae7-4f59-9dba-c88c9052b9b7.gif)

A sample repo containing code samples for the best practices for snapshot testing on Android. These
have been discussed on:

1. my blog series
   on [snapshot testing](https://sergiosastre.hashnode.dev/an-introduction-to-snapshot-testing-on-android-in-2021)
   .
2. [Droidcon Berlin 2021](https://www.droidcon.com/2021/11/10/an-introduction-to-effective-snapshot-testing-on-android/)
3. [Droidcon London 2021](https://www.droidcon.com/2021/11/17/an-introduction-to-effective-snapshot-testing-on-android-2/)

This repo includes some samples to snapshot test Dialogs, ViewHolders, Activities and **Jetpack Compose**!. These tests are written as:

1. **Parameterized Tests**: Write the test once and run it under all desired configurations. This
   includes snapshot tests under various **font sizes**, **locales**, **orientations** and **UI modes (i.e. Dark/Light mode)**
2. **Filtered Parameterized Tests**: Create filters to group the configurations, so that we can run
   only the tests corresponding to a certain group. As described later in this ReadMe, this can be
   useful when having a large bunch of snapshot tests, in order to reduce building times. That way
   we can, for instance, select to run a group of them on every PR (e.g. the essential ones) and the
   rest only once a day.

All the samples use [Shot from pedrovgs](https://github.com/pedrovgs/Shot), what is a great library
that facilitates snapshot testing.

## Table of Contents

- [Before you start...](#before-you-start)
    - [Emulators](#emulators)
    - [Animations](#animations)
- [Parameterized snapshot tests](#parameterized-snapshot-tests)
    - [with Parameterized Runner](#with-parameterized-runner)
    - [with TestParameterInjector](#with-testparameterinjector)
- [Filter parameterized tests](#filter-parameterized-tests)
- [Jetpack Compose Examples!](#jetpack-compose-examples)
- [What is coming next](#what-is-coming-next)
- [Attribution of icons in the app](#attribution-of-icons-in-the-app)

## Before you start...

### Emulators

I've been using an emulator with API 27 for a matter of simplicity. That is because `Shot` requires
less configuration for API < 28. Moreover, if you are using Windows, beware that Shot 5.12.2 still
has [some issues on API 29](https://github.com/pedrovgs/Shot/issues/244)

From API 28 on, you need to execute the following commands before running the tests for the first
time. Otherwise some tests might not be recorded (e.g. Dialogs)

For Android 9 (API level 28)

```
adb shell settings put global hidden_api_policy_pre_p_apps 1
adb shell settings put global hidden_api_policy_p_apps 1
```

For Android 10 (API level 29) or higher

```
adb shell settings put global hidden_api_policy 1
```

### Animations

Snapshot tests might have issues due to running animations at the moment the snapshot are taken.

In order to keep animations disabled, this project uses `testOptions { animationsDisabled = true }`
in the gradle file. Unfortunately, it does not work on all APIs (e.g. API 26). Therefore, if you
come across some issues, disable animations on the emulator *via settings* before running the
snapshot tests.

## Parameterized Snapshot Tests

They enable us to test a given view with all possible different configurations, namely:

1. UI mode (Light and Dark)
2. Font size
3. Locale
4. Orientation 
5. View dimensions (width and height)
6. Specific view states
7. Others...

With Parameterized snapshot test we can write the test once and run it for all the desired
configurations!
This can be achieved by using `org.junit.runners.Parameterized`
or `com.google.testing.junit.testparameterinjector.TestParameterInjector` as in the examples below.

### with Parameterized Runner

1. Create a `TestItem` which encapsulates all the info we want to inject as parameter. I strongly
   recommend to add a testName to it, which must be unique among all tests. This is used to
   distinguish each snapshot file from the rest.

```kotlin
class DeleteDialogTestItem(
    val fontScale: FontSize,
    val uiMode: UiMode,
    val texts: Array<Int>,
    val dialogWidth: DialogWidth,
    val testName: String
)
```

2. Write the method that inflates the view and takes/compares the screenshot. Use the parameters
   from
   `DeleteDialogTestItem` to set the view into the right state. If you also want to enable different
   configurations to your tests (e.g. Locale, Font Size, Orientation, Light/Dark mode, etc...), you
   can add the
   [AndroidUiUtils](https://github.com/sergio-sastre/AndroidUiTestingUtils) library to your project.
   For example:

```kotlin
object DeleteDialogSnapshotHelper : ScreenshotTest {
    fun snapDeleteDialogWithActivityScenario(testItem: DeleteDialogTestItem) {
        val activityScenario = ActivityScenarioConfigurator.ForView()
            .setFontSize(testItem.fontScale)
            .setUiMode(testItem.uiMode)
            .launchConfiguredActivity()

        val activity = activityScenario.waitForActivity()

        val dialog = waitForView {
            DialogBuilder.buildDeleteDialog(
                activity,
                onDeleteClicked = {/* no-op*/ },
                bulletTexts = itemArray(activity, testItem.texts)
            )
        }

        compareScreenshot(
            dialog,
            name = testItem.testName,
            widthInPx = testItem.dialogWidth.widthInPx
        )
        
        activityScenario.close()
    }
}
```

3. Finally, we need to add the parameters. We will use `org.junit.runners.Parameterized` for that.
   We will pass the `TestItem` in the class constructor, and define each single configuration to be
   run inside a method annotated with `@Parameterized.Parameters`.

```kotlin
@RunWith(Parameterized::class)
class DeleteDialogTest(private val testItem: DeleteDialogTestItem) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Array<DeleteDialogTestItem> =
            arrayOf(
                // spacious item: small font, little wording, wide view
                DeleteDialogTestItem(
                    FontSize.SMALL,
                    UiMode.NIGHT,
                    arrayOf(R.string.shortest),
                    DialogWidth.WIDE,
                    "DARK_SMALL_WIDE"
                ),
                // suffocated item: huge font, a lot of wording, narrow view
                DeleteDialogTestItem(
                    FontSize.HUGE,
                    UiMode.NIGHT,
                    repeatedItem(7, R.string.largest),
                    DialogWidth.NARROW,
                    "DARK_HUGE_NARROW"
                )
            )
    }

    @UnhappyPath
    @Test
    fun snapDialog() {
        DeleteDialogSnapshotHelper.snapDeleteDialog(testItem)
    }
}
```

**Remark**: The Parameterized runner injects the parameter passed in the Test class constructor to every
single test. If that is not wished, consider using the TestParameterInjector.

Now you can just run your test with `./gradlew executeScreenshotTests -Precord` and you should see
the results!

### with TestParameterInjector

1. Same as with Parameterized runner. Create a `TestItem` which encapsulates all the info we want to inject as parameter.
```kotlin
class DeleteDialogTestItem(
    val fontScale: FontSize,
    val theme: DialogTheme,
    val texts: Array<Int>,
    val testName: String
)
```

2. Create an enum with the configurations under test
```kotlin
enum class UnhappyPathTest(val deleteItem: DeleteDialogTestItem) {
    SPACIOUS(
        DeleteDialogTestItem(
            SMALL,
            MATERIAL_DARK_DIALOG,
            arrayOf(R.string.shortest),
            DialogWidth.WIDE,
            "DARK_SMALL_WIDE"
        ),
    ),
    SUFFOCATED(
        DeleteDialogTestItem(
            FontSize.HUGE,
            UiMode.NIGHT,
            repeatedItem(7, R.string.largest),
            DialogWidth.NARROW,
            "DARK_HUGE_NARROW"
        )
    )
}
```
3. Same as with Parameterized runner. Write the method that inflates the view and takes/compares the screenshot. Use the parameters
   from `DeleteDialogTestItem` to set the view into the right state.

```kotlin
object DeleteDialogSnapshotHelper : ScreenshotTest {
    fun snapDeleteDialogWithActivityScenario(testItem: DeleteDialogTestItem) {
        val activityScenario = ActivityScenarioConfigurator.ForView()
            .setFontSize(testItem.fontScale)
            .setUiMode(testItem.uiMode)
            .launchConfiguredActivity()
                
        val activity = activityScenario.waitForActivity()

        val dialog = waitForView {
            DialogBuilder.buildDeleteDialog(
                activity,
                onDeleteClicked = {/* no-op*/ },
                bulletTexts = itemArray(activity, testItem.texts)
            )
        }

        compareScreenshot(
            dialog,
            name = testItem.testName,
            widthInPx = testItem.dialogWidth.widthInPx
        )
       
        activityScenario.close()
    }
}
```

4. Finally, we need to add the parameters. We will use `com.google.testing.junit.testparameterinjector.TestParameterInjector` as runner.
   To inject the `TestItem` we have defined as enums, we pass the enum class as argument in the test methods annotated with `@TestParameter`.
   
```kotlin
@RunWith(TestParameterInjector::class)
class DeleteDialogTest : ScreenshotTest {

    @UnhappyPath
    @Test
    fun snapDialogUnhappyPath(@TestParameter testItem: UnhappyPathTest) {
        snapDeleteDialog(testItem.deleteItem)
    }
}
```
Now you can just run your test with `./gradlew executeScreenshotTests -Precord` and you should see
the results!   

## Filtered Parameterized Tests

Running snapshot tests for all configurations on every PR can be very time-consuming and lead to
incrementally slower builds. One approach to solve that issue is to run only a part of those tests
on every PR (e.g. the most common config, our "smoke" tests) and all snapshot tests - or "all
non-smoke tests" - once a day (e.g. during the night or whenever it disturbs your team the least).
In doing so, we get informed of the most important visual regression bugs on every PR (i.e. blocking
bugs), and still get notified of the non-blocking bugs once a day.

We can accomplish this by filtering our tests with
`-Pandroid.testInstrumentationRunnerArguments.annotation=com.your.package.YourAnnotation`
or
`-Pandroid.testInstrumentationRunnerArguments.notAnnotation=com.your.package.YourAnnotation`

**Warning**: These arguments are supported by `org.junit.runners.Parameterized` and `com.google.testing.junit.testparameterinjector.TestParameterInjector`, but not by all
runners, e.g. `JUnitParams` fails if used. I strongly recommend to use `com.google.testing.junit.testparameterinjector.TestParameterInjector` for filtered parameterized tests,
because you can control which parameters are passed wo each test method. That is not the case with `org.junit.runners.Parameterized`, because the parameters are injected into every single test method.
Thus, you need to create different Test classes to inject different parameters.

So first of all, we need to create our annotations

```kotlin
annotation class UnhappyPath
```
and
```kotlin
annotation class HappyPath
```

And then we add another Test Class that reuses our previously defined `DeleteDialogTestItem`
and `snapDeleteDialog(testItem)`
containing the "most common config" parameters, like here below

```kotlin
@RunWith(TestParameterInjector::class)
class DeleteDialogTest : ScreenshotTest {

   enum class UnhappyPathTest(val deleteItem: DeleteDialogTestItem) {
      SPACIOUS(spaciousDialogUnhappyPath()),
      SUFFOCATED(suffocatedItemUnhappyPath())
   }

   enum class HappyPathTest(val deleteItem: DeleteDialogTestItem){
      NORMAL(normalItemHappyPath())
   }

   @UnhappyPath
   @Test
   fun snapDialogUnhappyPath(@TestParameter testItem: UnhappyPathTest) {
      snapDeleteDialogWithActivityScenario(testItem.deleteItem)
   }

   @HappyPath
   @Test
   fun snapDialogHappyPath(@TestParameter testItem: HappyPathTest) {
      snapDeleteDialogWithActivityScenario(testItem.deleteItem)
   }
}
```

Take a look
at [DeleteDialogTest.kt](https://github.com/sergio-sastre/RoadToEffectiveSnapshotTesting/blob/master/app/src/androidTest/java/com/example/road/to/effective/snapshot/testing/testparameterinjector/deletedialog/DeleteDialogTest.kt)
to see how these parameterized tests are implemented and run
`./gradlew executeScreenshotTests -Precord -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.utils.annotations.UnhappyPath`
to verify that only the `@UnhappyPath` tests run! The same goes for `@HappyPath`

## Jetpack Compose examples!

We've recently included some Jetpack Compose examples!
1. Screenshot testing an Activity made up of Composables
   - [CoffeeDrinkComposeActivityTest.kt](https://github.com/sergio-sastre/RoadToEffectiveSnapshotTesting/blob/master/app/src/androidTest/java/com/example/road/to/effective/snapshot/testing/testparameterinjector/compose/activity/CoffeeDrinkComposeActivityTest.kt)
2. Screenshot testing Composables:
   - [CoffeeDrinkListComposableTest.kt](https://github.com/sergio-sastre/RoadToEffectiveSnapshotTesting/blob/master/app/src/androidTest/java/com/example/road/to/effective/snapshot/testing/testparameterinjector/compose/composeitem/CoffeeDrinkListComposableTest.kt)
   - [CoffeeDrinkAppBarComposableTest.kt](https://github.com/sergio-sastre/RoadToEffectiveSnapshotTesting/blob/master/app/src/androidTest/java/com/example/road/to/effective/snapshot/testing/parameterized/compose/composeitem/CoffeeDrinkAppBarComposableTest.kt)

Special thanks to [Alex Zhukovich](https://github.com/AlexZhukovich) for his [CoffeeDrinksWithJetpackCompose](https://github.com/AlexZhukovich/CoffeeDrinksWithJetpackCompose) projects, from which
I've borrowed the Jetpack Compose examples!

## What is coming next:

1. Snapshot testing for foldables (Jetpack compose sample!)
2. Running snapshot tests on multiple devices in parallel
3. Tips to remove flakiness
4. Tips to increase test execution speed and more...

## Attribution of icons in the app

Icons made by <a href="https://www.freepik.com" title="Freepik">Freepik</a>
from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>
</br>
Icons made by <a href="https://www.flaticon.com/authors/surang" title="surang">surang</a>
from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>

