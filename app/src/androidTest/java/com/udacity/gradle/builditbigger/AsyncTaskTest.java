package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by vasam on 9/8/2017.
 */

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    @Mock
    Context context;
    GetJokeAsyncTask.AsyncResponse mAsyncResponse = mock(GetJokeAsyncTask.AsyncResponse.class);
    String joke = null;

    @Test
    public void checkJoke() throws InterruptedException, ExecutionException, TimeoutException {
        GetJokeAsyncTask endpointsAsyncTask = new
                GetJokeAsyncTask(InstrumentationRegistry.getTargetContext(), mAsyncResponse);
        endpointsAsyncTask.execute();
        joke = endpointsAsyncTask.get(20, TimeUnit.SECONDS);
        assertNotNull(joke);
        assertTrue(joke,joke.length()>0);
    }

}
