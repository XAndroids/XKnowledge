package com.android.xknowledge.component.activity.lifecycler

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.xknowledge.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LifeCycleActivityTest {
    @get:Rule
    var mIntentsRule = IntentsTestRule(LifeCycleActivity::class.java)

    @Test
    fun test_Activity1() {
        onView(withId(R.id.lifecycler_button_toone)).perform(click())
//        intended(
//            allOf<Intent>(
//                hasComponent(hasShortClassName(".component.activity.lifecycler.ActivityOne")),
//                toPackage("com.android.xknowledge")
//            )
//        )

        intended(allOf<Intent>(hasAction("com.android.xknowledge.component.activity.lifecycler.ActivityOne")))
    }

    @Test
    fun test_Activity2() {
        onView(withId(R.id.lifecycler_button_toone)).perform(click())
//        intended(
//            allOf<Intent>(
//                hasComponent(hasShortClassName(".component.activity.lifecycler.ActivityOne")),
//                toPackage("com.android.xknowledge")
//            )
//        )

        intended(allOf<Intent>(hasAction("com.android.xknowledge.component.activity.lifecycler.ActivityOne")))
    }
}