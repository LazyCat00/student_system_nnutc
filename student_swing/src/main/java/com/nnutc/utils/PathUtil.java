package com.nnutc.utils;

public class PathUtil {
    private static final String Root_Path = "";

    public static String getRealPath(String relativePath) {
        return Root_Path + relativePath;
    }
}
