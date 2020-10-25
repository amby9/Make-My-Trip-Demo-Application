package com.make.my.trip.demo.platform.utils;

import com.amazonaws.util.Base64;

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
