package com.clase.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class Hash {
	public static String md5(String src) {
	    try {
	        MessageDigest m = MessageDigest.getInstance("MD5");
	        m.update(src.getBytes(), 0, src.length());
	        BigInteger i = new BigInteger(1,m.digest());
	        return String.format("%1$032x", i);         
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}