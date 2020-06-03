package com.epsi.egostyleapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ScanIntentTest {

    @Rule
    public IntentsTestRule<ScanActivity> intentsTestRule =
            new IntentsTestRule<>(ScanActivity.class);

    @Test
    public void testLaunchActivity() {
        onView(withId(R.id.home)).check(matches(withContentDescription("Liste"))).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.scanner)).check(matches(withContentDescription("Scanner"))).check(matches(isDisplayed())).perform(click());;
        onView(withId(R.id.mesbons)).check(matches(withContentDescription("Mes bons"))).check(matches(isDisplayed())).perform(click());;
    }

    @Test
    public void validateScanIntent() {
        Intent resultData = new Intent();
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
        intending(hasAction("com.google.zxing.client.android.SCAN")).respondWith(result);
        onView(withId(R.id.btn_scan)).perform(click());
    }

}


