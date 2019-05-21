package com.example.myappforsortcup.util;

import java.util.Random;

/**
 * Created by 蒲公英之流 on 2019-05-21.
 */

public class SomeUtil {

    public static String RandomDoubleString(String s, int seed){
        int length = new Random().nextInt(seed);
        StringBuilder builder = new StringBuilder(s);
        for (int i = 0; i < length;i++){
            builder.append(s);
        }
        return builder.toString();
    }

}
