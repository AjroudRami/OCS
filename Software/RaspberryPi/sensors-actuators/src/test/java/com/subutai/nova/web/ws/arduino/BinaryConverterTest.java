package com.subutai.nova.web.ws.arduino;

import junit.framework.Assert;
import org.junit.Test;

public class BinaryConverterTest {

    @Test
    public void testConvertShort() {
        short testVal = 122;
        byte[] convertedArray = BinaryConverter.toBinary(testVal);
        short endVal = BinaryConverter.getShort(convertedArray);

        Assert.assertEquals(testVal, endVal);
    }

}
