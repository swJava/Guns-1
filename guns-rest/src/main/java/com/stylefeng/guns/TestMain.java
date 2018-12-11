package com.stylefeng.guns;

import org.apache.shiro.crypto.hash.Sha256Hash;

public class TestMain{

    public static void main(String[] args){
        String result = new Sha256Hash("abcd1234").toHex().toUpperCase();
        System.out.println(result);
    }
}