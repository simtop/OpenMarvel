package com.simtop.openmarvel

import android.view.View
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.simtop.openmarvel.presentation.MainActivity
import com.simtop.openmarvel.robots.detailScreen
import com.simtop.openmarvel.robots.homeScreen
import com.simtop.openmarvel.utils.ViewVisibilityIdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/*
Remember to turn off:
Window animation scale
Transition animation scale
Animator duration scale
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
class E2ETest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private val progressBarVisibility by lazy {
        ViewVisibilityIdlingResource(
            R.id.progress_bar,
            View.GONE
        )
    }

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun shouldDisplayListOf100HeroesAndOpenDetailScreen() {
        homeScreen {
            setIdlingResourceTimeout(5)
            registerIdlingRegistry(progressBarVisibility)
            matchCountRecyclerViewItems(R.id.characters_recyclerview, 100)
            unregisterIdlingRegistry(progressBarVisibility)
            clickRecycler(R.id.characters_recyclerview, 0)
            matchText(R.id.hero_name, "3-D Man")
        }
    }

    @Test
    fun shouldDisplayListOf100Heroes() {
        homeScreen {
            setIdlingResourceTimeout(5)
            registerIdlingRegistry(progressBarVisibility)
            matchCountRecyclerViewItems(R.id.characters_recyclerview, 100)
            unregisterIdlingRegistry(progressBarVisibility)
        }
    }
}