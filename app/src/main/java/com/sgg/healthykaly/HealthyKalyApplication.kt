package com.sgg.healthykaly

import android.app.Application
import com.sgg.healthykaly.data.RecipeDatabase

class HealthyKalyApplication : Application() {
    val database: RecipeDatabase by lazy { RecipeDatabase.getInstance(this) }
}