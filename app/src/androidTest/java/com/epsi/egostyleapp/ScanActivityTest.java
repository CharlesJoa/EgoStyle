package com.epsi.egostyleapp;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class ScanActivityTest {

    @Rule
    public ActivityTestRule<ScanActivity> mActivityRule =
            new ActivityTestRule<>(ScanActivity.class);

    @Test
    public void scannerTitleDisplayTest(){
        onView(withId(R.id.textView3)).check(matches(withText("Scanner de QR code")));
    }

    @Test
    public void scanButtonDisplayTest() {
        onView(withId(R.id.btn_scan)).check(matches(isDisplayed()));
    }

}