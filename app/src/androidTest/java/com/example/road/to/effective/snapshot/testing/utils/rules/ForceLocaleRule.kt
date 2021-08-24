package com.example.road.to.effective.snapshot.testing.utils.rules

import android.content.res.Configuration
import android.content.res.Resources
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.*


class ForceLocaleRule(testLocale: Locale) : TestRule {
    private var testLocale: Locale? = null
    private var deviceLocale: Locale? = null
    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                try {
                    testLocale?.run {
                        deviceLocale = Locale.getDefault()
                        setLocale(this)
                    }
                    base.evaluate()
                } finally {
                    deviceLocale?.run { setLocale(this) }
                }
            }
        }
    }

    fun setLocale(locale: Locale) {
        val resources: Resources = InstrumentationRegistry.getInstrumentation().targetContext.resources
        Locale.setDefault(locale)
        val config: Configuration = resources.configuration
        //config.locale = locale
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    init {
        this.testLocale = testLocale
    }
}