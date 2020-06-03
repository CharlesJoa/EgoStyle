package com.epsi.egostyleapp;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void homeTitleDisplayTest(){
        onView(withId(R.id.textView2)).check(matches(withText("Promotions actutelles")));
    }

    @Test
    public void recyclerViewDisplayTest(){
        // verify the visibility of recycler view on screen
        onView(withId(R.id.ListBon)).check(matches(isDisplayed()));
    }

    @Test
    public void bottomNavigationDisplayTest(){
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()));
    }

    /*
    @Test
    public void bottomNavigationTest(){
        onView(withId(R.id.mesbons)).perform(click());
        intended(hasComponent(CouponActivity.class.getName()));

       // onView(withId(R.id.scanner)).perform(click());
       // intended(hasComponent(ScanActivity.class.getName()));
    }

     */
}