package com.jino.jgank.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JINO on 2018/1/25.
 */
public class TimeUtilsTest {
    @Test
    public void convertUTCToStandarTime() throws Exception {
        String res = TimeUtils.convertUTCToStandarTime("2018-01-16T08:40:08.101Z");
        System.out.println(res);
        assertEquals("2018-01-16 16:40:08", res);
    }

}