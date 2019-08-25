package com.pnr.pexelsapp.ui.activities

import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.pnr.pexelsapp.R
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchActivityTest {

    @get:Rule
    val searchActivityTestRule: ActivityTestRule<SearchActivity> =
        ActivityTestRule(SearchActivity::class.java, false, true)

    /**
     * Test launch of activity
     */
    @Test
    fun testLaunch() {
        val view: View = searchActivityTestRule.activity.findViewById(R.id.recyclerView)
        assertNotNull(view)
    }

    /**
     * Test toolbar view
     */
    @Test
    fun testToolBarView() {
        val view: View = searchActivityTestRule.activity.findViewById(R.id.toolbar)
        assertNotNull(view)
    }
}