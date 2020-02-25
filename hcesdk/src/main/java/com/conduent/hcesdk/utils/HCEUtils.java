package com.conduent.hcesdk.utils;

import android.support.annotation.RestrictTo;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Deprecated
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
     * Utility method to convert a hexadecimal string to a binary string.
     * <p/>
     * <p>Behavior with input strings containing non-hexadecimal characters is undefined.
     *
     * @param hexString String containing hexadecimal characters to convert
     */
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

    /**
     * Utility method to convert a binary string to a Decimal.
     * <p/>
     * <p>Behavior with input strings containing non-binary characters is undefined.
     *
     * @param binString String containing binary characters to convert
     */
    public static int BinaryStringToDecimal(String binString) {
        return Integer.parseInt(binString, 2);
    }

    public static int StringToDecimal(String binString) {
        return Integer.parseInt(binString, 16);
    }

    /**
     * Utility method to convert a binary string to a hexadecimal string.
     * <p/>
     * <p>Behavior with input strings containing non-binary characters is undefined.
     *
     * @param binaryString String containing binary characters to convert
     */
    public static String BinaryStringToHexString(String binaryString) {
        String hexOutput = Integer.toHexString(BinaryStringToDecimal(binaryString));
        return hexOutput.toUpperCase();
    }

    /**
     * Utility method to convert a binary string to a Date string.
     * <p/>
     * <p>Behavior with input strings containing non-binary characters is undefined.
     *
     * @param binaryString String containing binary characters to convert
     */
    public static String getDate(String binaryString) {
        int days = Integer.parseInt(binaryString, 2);
        String dt = "01/01/1997";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
            c.add(Calendar.DATE, days);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    /**
     * Utility method to get required bits from data.
     * <p/>
     * <p>Behavior with input strings containing non-binary characters is undefined.
     *
     * @param startPos Int containing start position
     * @param endPos   Int containing end position
     * @param data     String containing binary characters to convert
     */
    public static String getCharByLength(int startPos, int endPos, String data) {
        String output = "";
        output = data.subSequence(startPos, endPos).toString();
        return output;
    }

    /**
     * Utility method to add padding '0' prefix.
     *
     * @param data       String containing binary characters to convert
     * @param dataLength indicates zero's to add.
     */
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

    /**
     * Utility method to add padding '0' prefix.
     *
     * @param data       String containing binary characters to convert
     * @param dataLength indicates zero's to add.
     */
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

    /**
     * Utility method to convert a hexadecimal string to number.
     * <p/>
     * <p>Behavior with input strings containing non-hexadecimal characters is undefined.
     *
     * @param mediaSerialNumber String containing hexadecimal characters to convert
     */
    public static long HexStringToDecimal(String mediaSerialNumber) {
        return Long.parseLong(mediaSerialNumber, 16);
    }

    /**
     * HEX String to byte array
     *
     * @param hexString input as HEX String
     * @return byte[]
     */
    public static byte[] HexStringToByteArray(String hexString) {
        byte[] b = new byte[hexString.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(hexString.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

    /**
     * HEx String to Base64 encoding
     *
     * @param hexString input as HEX String
     * @return Base64Encode String
     */
    public static String HexStringToBase64String(String hexString) {
        byte[] bytes = HexStringToByteArray(hexString);
        return Base64.encodeToString(bytes, 0);
    }

    /**
     * This method collect current date from system and return UTC time
     *
     * @return UTC time in String
     */
    public static String getCurrentUCTTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }

}
