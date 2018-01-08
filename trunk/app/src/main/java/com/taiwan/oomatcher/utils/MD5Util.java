package com.taiwan.oomatcher.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作 者: huanghuojun
 * 描 述: MD5工具类
 * 版 本:
 * 创建日期: 2016/4/6 12:04
 */
public class MD5Util {
    public static String getmd5(String source) {
        String resultHash = null;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        byte[] result = new byte[md5.getDigestLength()];
        md5.reset();
        try {
            md5.update(source.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        result = md5.digest();

        StringBuffer buf = new StringBuffer(result.length * 2);

        for (int i = 0; i < result.length; i++) {
            int intVal = result[i] & 0xff;
            if (intVal < 0x10) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(intVal));
        }
        resultHash = buf.toString();
        return resultHash.toString();
    }


    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }
}
