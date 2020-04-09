package com.epsi.egostyleapp;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
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
       // Intents.intending(not(isInternal())).respondWith(result);
    }

    @Test
    public void onActivityResult(){
        
    }
}

        /*
        Intents.intending(hasAction("com.google.zxing.client.android.SCAN"));
        onView(withId(R.id.btn_scan)).check(matches(isDisplayed())).perform(click());
        Intents.intended(Matchers.allOf(
                hasAction("com.google.zxing.client.android.SCAN"),
                hasExtra("SCAN_MODE", "QR_CODE_MODE")));
*/


