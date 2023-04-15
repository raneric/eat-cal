package com.sgg.healthykaly.uiTest.findRagment

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.sgg.healthykaly.R
import com.sgg.healthykaly.data.FakeDataProvider
import com.sgg.healthykaly.data.RecipeDataSourceProvider
import com.sgg.healthykaly.di.RemoteDataProviderModule
import com.sgg.healthykaly.di.RemoteSource
import com.sgg.healthykaly.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Singleton

@UninstallModules(RemoteDataProviderModule::class)
@HiltAndroidTest
class FindRecipeUiWithData {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class FakeDataProviderModule {
        @RemoteSource
        @Singleton
        @Binds
        abstract fun provideDataSourceProvider(fakeDataProvider: FakeDataProvider): RecipeDataSourceProvider
    }


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

