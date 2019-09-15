package com.example.xknowledge.test.robolectric

import android.content.Intent
import android.widget.Button
import com.example.xknowledge.R
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf


@RunWith(RobolectricTestRunner::class)
class RobolectricActivityTest {

    @Test
    fun clickingLogin_shouldStartLoginActivity() {
        val activity = Robolectric.setupActivity(RobolectricActivity::class.java)
        activity.findViewById<Button>(R.id.robolectric_button_login).performClick()

        val expectedIntent = Intent(activity, LoginActivity::class.java)
        val actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity()
        assertEquals(expectedIntent.component, actual.getComponent())
    }
}
