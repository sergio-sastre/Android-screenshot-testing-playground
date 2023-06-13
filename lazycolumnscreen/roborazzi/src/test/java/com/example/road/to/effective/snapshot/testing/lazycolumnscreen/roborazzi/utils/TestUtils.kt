package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.utils

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.test.core.app.ActivityScenario
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioForComposableRule
import java.io.File

fun filePath(name: String): String {
    val path = System.getProperty("user.dir")
    val file = File("$path/src/test", "$name.png")
    return file.path
}

fun RobolectricActivityScenarioForComposableRule.setContent(
    content: @Composable () -> Unit
): ActivityScenario<out Activity> =
    activityScenario.onActivity {
        it.setContent { content() }
    }
