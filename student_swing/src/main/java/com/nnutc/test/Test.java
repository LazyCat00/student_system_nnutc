package com.nnutc.test;

import java.net.URL;

public class Test {
    public static void main(String[] args) {
        URL imgUrl = Test.class.getClassLoader().getResource("imgs/logo.jpg");
        System.out.println(imgUrl.toString());
    }
}
