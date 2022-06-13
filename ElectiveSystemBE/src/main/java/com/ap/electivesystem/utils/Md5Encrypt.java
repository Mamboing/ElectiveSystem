package com.ap.electivesystem.utils;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Md5Encrypt {

    private static String byteToHexString(byte b) {
        int bInt = b;
        if (bInt < 0) {
            b ^= (1 << 7);
            bInt = b + (1 << 7);
        }

        String lowBit = Integer.toHexString(bInt / 16);
        String highBit = Integer.toHexString(bInt % 16);

        return lowBit + highBit;
    }

    public static void main(String[] args) {
        Md5Encrypt md5Encrypt = new Md5Encrypt();
        String s = md5Encrypt.computeHexString("123454321");
        System.out.println(md5Encrypt.computeHexString(s + "Elective_System_0.0"));
    }

    public String computeHexString(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] resultBytes = md5.digest(str.getBytes(StandardCharsets.UTF_8));

            StringBuilder builder = new StringBuilder();
            for (byte b : resultBytes) {
                builder.append(byteToHexString(b));
            }

            return builder.toString();
        } catch (NoSuchAlgorithmException ex) {
            return "";
        }
    }

}
