package com.epsi.egostyleapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScanActivityTest {

    @Rule
    public ActivityTestRule<ScanActivity> scanActivityActivityTestRule = new ActivityTestRule<ScanActivity>(ScanActivity.class);

    private ScanActivity scanActivity = null;

    @Before
    public void setUp() throws Exception {
        scanActivity = scanActivityActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        scanActivity = null;
    }

    @Test
    public void testScanLaunch() {
        View view = scanActivity.findViewById(R.id.textView3);
        assertNotNull(view);
    }

    @Test
    public void onActivityResult() {
    }
}