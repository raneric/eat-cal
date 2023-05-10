package com.sgg.healthykaly.uiTest.findRagment

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.sgg.healthykaly.R
import com.sgg.healthykaly.di.RemoteDataProviderModule
import com.sgg.healthykaly.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@UninstallModules(RemoteDataProviderModule::class)
@HiltAndroidTest
class FindRecipeUiWithData {

    @get:Rule
    val hiltRules = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRules.inject()
    }

    @Test
    fun testErrorAndLoadingIsHiddenWhenDataIsLoaded() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.errorWidget)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }
}

