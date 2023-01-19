package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.dropshots

import android.app.Activity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.test.core.app.ActivityScenario
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForComposableRule

fun ActivityScenarioForComposableRule.setContent(
    content: @Composable () -> Unit
): ActivityScenario<out Activity> =
    activityScenario.onActivity {
        it.setContent { content() }
    }




