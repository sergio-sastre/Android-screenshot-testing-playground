package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtest

import sergio.sastre.uitesting.shot.ShotScreenshotTestRule
import sergio.sastre.uitesting.utils.ScreenshotConfig
import sergio.sastre.uitesting.utils.ScreenshotTestRule

class SharedTestRule(
    private val config: ScreenshotConfig,
): ScreenshotTestRule by ShotScreenshotTestRule(config)
