package com.conduent.hcesdk.utils;

import android.support.annotation.RestrictTo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class HCEUtils {

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    /**
     * Utility method to convert a byte array to a hexadecimal string.
     *
     * @param bytes Bytes to convert
     * @return String, containing hexadecimal representation.
     */
    public static String ByteArrayToHexString(byte[] bytes) {
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Utility method to convert a hexadecimal string to a byte string.
     * <p/>
     * <p>Behavior with input strings containing non-hexadecimal characters is undefined.
     *
     * @param s String containing hexadecimal characters to convert
     * @return Byte array generated from input
     */
    public static byte[] HexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String HexStringToBinaryString(String hexString) {
        String trimHex = hexString.replaceAll("\\s", "");
        char[] hexChars = trimHex.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char hex : hexChars) {
            int decimal = Integer.parseInt(String.valueOf(hex), 16);
            int remainder = decimal;
            int x8 = 8;
            int i = 0;
            while (i < 4) {
                int quotient = remainder / x8;
                remainder = decimal % x8;
                sb.append(quotient);
                x8 = x8 / 2;
                i++;
            }
        }
        return sb.toString();
    }

    public static int binaryStringToDecimal(String binString) {
        return Integer.parseInt(binString, 2);
    }

    public static String binaryToHexString(String binaryString) {
        String hexOutput= Integer.toHexString(binaryStringToDecimal(binaryString));
        return hexOutput.toUpperCase();
    }

    public static String getEnvApplicationValidityEndDate(String binString) {
        int days = Integer.parseInt(binString,2);
        String dt = "01/01/1997";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, days);
        return sdf.format(c.getTime());
    }

    public static String getDate(String binString) {
        int days = Integer.parseInt(binString,2);
        String dt = "01/01/1997";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, days);
        return sdf.format(c.getTime());
    }

    /**
     * Utility method to concatenate two byte arrays.
     *
     * @param first First array
     * @param rest  Any remaining arrays
     * @return Concatenated copy of input arrays
     */
    public static byte[] ConcatArrays(byte[] first, byte[]... rest) {
        int totalLength = first.length;
        for (byte[] array : rest) {
            totalLength += array.length;
        }
        byte[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (byte[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    public static String[] StringSplit255(String text) {
        // Give the list the right capacity to start with. You could use an array
        // instead if you wanted.
        String[] ret = new String[(text.length() + 254) / 255];
        int retcounter = 0;

        for (int start = 0; start < text.length(); start += 255) {
            ret[retcounter] = (text.substring(start, Math.min(text.length(), start + 255)));
            retcounter += 1;
        }
        return ret;
    }

    public static void testParse() {
        int startPos = 0;
        int endPos = 0;
        boolean isth = true;
        String recBinaryData = "001001001011100100101000010010000000100000000101 1011 1000 1100 1010 0001 0010 0011 0000 0000 0000 0001 0010 0100 0000 1000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000";
        if (isth) {
            endPos = startPos + 6;
            String versionNumber = getCharByLength(startPos, endPos, recBinaryData);
            startPos = endPos;
        }

        if (isth) {
            endPos = startPos + 7;
            String bitmap = getCharByLength(startPos, endPos, recBinaryData);
            startPos = endPos;
        }

    }

    public static String getCharByLength(int startPos, int endPos, String data) {
        String output = "";
        output = data.subSequence(startPos, endPos).toString();
        return output;
    }

    public static String padLeft(String data, int dataLength) {
        int noz = dataLength - data.length();
        StringBuilder sb = new StringBuilder();
        while (noz > 0) {
            sb.append("0");
            noz--;
        }
        sb.append(data);
        return sb.toString();
    }

    public static int padLeft(int data, int dataLength) {
        int noz = dataLength - data;
        StringBuilder sb = new StringBuilder();
        while (noz > 0) {
            sb.append("0");
            noz--;
        }
        sb.append(data);
        return Integer.parseInt(sb.toString());
    }

}
