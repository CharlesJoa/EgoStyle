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
public class SplashScreenTest {

    @Rule
    public ActivityTestRule<SplashScreen> mActivityRule =
            new ActivityTestRule<>(SplashScreen.class);

    private int splashScreenWaitingTime = 2000;

    @Test
    public void splashTitleDisplayTest() {
        onView(withId(R.id.splash_title)).check(matches(withText("EgoStyle")));
    }

    @Test
    public void splashImageDisplayTest() {
        onView(withId(R.id.splash_img)).check(matches(isDisplayed()));
    }
/*
    @Test
    public void triggerIntentTest() {

        // check that the text and image is there
        onView(withId(R.id.splash_title)).check(matches(withText("EgoStyle")));
        onView(withId(R.id.splash_img)).check(matches(isDisplayed()));

        intended(hasComponent(MainActivity.class.getName()));

        //intended(toPackage("testing.android.vogella.com.simpleactivity"));
        // intended(hasExtra("URL", "https://www.vogella.com/"));
    }
*/


}