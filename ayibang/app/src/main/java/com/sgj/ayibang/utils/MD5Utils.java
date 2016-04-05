package com.sgj.ayibang.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by John on 2016/4/5.
 */
public class MD5Utils {
    public static String encryptionFor32(String plainText){

        String re_md5 = new String();

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(plainText.getBytes());
            byte b[] = digest.digest();

            int i;
            StringBuffer stringBuffer = new StringBuffer("");
            for(int offset = 0; offset < b.length; offset++){
                i = b[offset];
                if(i < 0){
                    i+=256;
                }
                if (i < 16){
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            re_md5 = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        return re_md5;
    }
}
