package com.example.road.to.effective.snapshot.testing.dialogs.dropshots.utils

import android.Manifest
import android.app.Activity
import android.graphics.Bitmap
import android.os.Build
import android.view.View
import androidx.test.rule.GrantPermissionRule
import com.dropbox.dropshots.Dropshots
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Bugfix: Dropshots must grant WRITE_EXTERNAL_STORAGE to record screenshots on emulators running API 29
 */
class DropshotsAPI29Fix(
    private val dropshots: Dropshots,
) : TestRule {
    override fun apply(base: Statement, description: Description): Statement =
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
            RuleChain.outerRule(
                // needed also in the manifest. Only required for API 29
                GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ).around(dropshots).apply(base, description)
        } else {
            dropshots.apply(base, description)
        }

    fun assertSnapshot(bitmap: Bitmap, name: String) {
        dropshots.assertSnapshot(bitmap, name)
    }

    fun assertSnapshot(activity: Activity, name: String) {
        dropshots.assertSnapshot(activity, name)
    }

    fun assertSnapshot(view: View, name: String) {
        dropshots.assertSnapshot(view, name)
    }
}
