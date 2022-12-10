package com.example.road.to.effective.snapshot.testing.coffeedrinkscompose.dropshots

import android.app.Activity
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.test.core.app.ActivityScenario
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForComposableRule
import sergio.sastre.uitesting.utils.utils.waitForView

fun ActivityScenarioForComposableRule.setContent(
    content: @Composable () -> Unit
): ActivityScenario<out Activity> =
    activityScenario.onActivity {
        it.setContent { content() }
    }

fun Activity.waitForComposeView(): ComposeView =
    waitForView {
        window
            .decorView
            .findViewById<ViewGroup>(android.R.id.content)
            .getChildAt(0) as ComposeView
    }



