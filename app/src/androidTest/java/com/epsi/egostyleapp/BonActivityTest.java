package com.epsi.egostyleapp;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class BonActivityTest {

    @Rule
    public ActivityTestRule<BonActivity> mActivityRule =
            new ActivityTestRule<>(BonActivity.class);

    @Test
    public void mesBonsTitleDisplayTest(){
        onView(withId(R.id.textView2)).check(matches(withText("Mes Bons")));
    }

}