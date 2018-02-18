package com.subutai.nova.arduino;


import junit.framework.Assert;
import org.junit.Test;

public class ByteArrayUtilTest {

    @Test
    public void copyTest() {
        byte[] in = new byte[]{10, 20};
        byte[] out = new byte[5];

        ByteArrayUtil.copy(in, out, 0, 2, 2);
        Assert.assertEquals(in[0], out[2]);
        Assert.assertEquals(in[1], out[3]);
    }
}
