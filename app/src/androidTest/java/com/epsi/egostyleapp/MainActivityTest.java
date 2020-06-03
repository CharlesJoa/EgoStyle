package com.epsi.egostyleapp;


import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
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
    public void recyclerViewScrollTest(){
        //verify it we can scroll through the list in recycler view
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.ListBon);
        int itemcount = recyclerView.getAdapter().getItemCount();
        onView(withId(R.id.ListBon)).perform(RecyclerViewActions.scrollToPosition(itemcount-1));
    }

    @Test
    public void bottomNavigationDisplayTest(){
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()));
    }

    @Test
    public void bottomNavigationListTest(){
        onView(withId(R.id.home)).perform(click()).check(matches(isDisplayed()));

    }

    @Test
    public void bottomNavigationScannerTest(){
        onView(withId(R.id.scanner)).perform(click()).check(matches(isDisplayed()));

    }

    @Test
    public void bottomNavigationMesCouponTest(){
        onView(withId(R.id.mesbons)).perform(click()).check(matches(isDisplayed()));

    }


}