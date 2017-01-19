package io.habets.targettester;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by wouter on 19-1-17.
 */
public class StopWatchTest {

    @Test
    public void test1() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

        Assert.assertEquals("00:01", stopWatch.stopAndGetTime());
    }

    @Test
    public void test2() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        Assert.assertEquals("00:02", stopWatch.stopAndGetTime());
    }
}