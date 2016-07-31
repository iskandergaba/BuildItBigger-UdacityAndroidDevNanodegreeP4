package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

public class RetrieveJokeTaskTest extends AndroidTestCase {

    public void testRetrieveJoke() throws Exception {
        assertFalse(new RetrieveJokeTask(null).execute().get().equals(""));
    }
}