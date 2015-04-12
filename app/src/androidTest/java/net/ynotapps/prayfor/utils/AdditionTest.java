package net.ynotapps.prayfor.utils;

import android.test.InstrumentationTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class AdditionTest extends InstrumentationTestCase {

    @Test
    public void testAdd() throws Exception {
        assertEquals("3 + 5 = 8", 8, Addition.add(3, 5));
    }
}