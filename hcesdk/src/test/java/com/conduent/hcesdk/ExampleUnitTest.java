package com.conduent.hcesdk;

import com.conduent.hcesdk.core.HCEEngine;
import com.conduent.hcesdk.utils.HCEUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void hexStringToBinaryString() {
        String outputtr = HCEUtils.hexStringToBinaryString("24 b9 28 48 08 05 b8 ca 12 30 00 12 40 80 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ");
        System.out.println(outputtr);
    }

    @Test
    public void parseJsonFile() {
        HCEUtils.testParse();
    }

    @Test
    public void printKthBit() {
    }

    @Test
    public void bianaryToHex(){
        String output = HCEUtils.binaryToHexString("010010100001001000000011");
    }
}