package com.user.ledger.platform.utils;

import com.amazonaws.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

    public static String enCode(String data)
    {
        return new String(Base64.encode(data.getBytes()));
    }

    public static String deCode(String data)
    {
        return new String(Base64.decode(data.getBytes()));
    }

}
