package com.sgj.ayibang.http;

import com.sgj.http.Http;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by John on 2016/4/26.
 */
public class HttpUtil {
    public void doPostConnection(){

        String urlStr = "https://www.baidu.com";
        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        DataInputStream inputStream = null;
        try {
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(3000);
            urlConnection.setRequestMethod("POST");

            urlConnection.connect();

            inputStream = new DataInputStream(urlConnection.getInputStream());

            if(urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK){
                // 网络异常
                return;
            }

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String state = bufferedReader.readLine();
            if("SUCCESS".equals(state)){

            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPostUploadFile(){



        String uploadFile = "";

        HttpURLConnection connection = null;
        DataOutputStream outputStream = null;
        FileInputStream inputStream = null;

        String end = "\r\n";
        String boundary = "******";

        try {
            URL url = new URL(uploadFile);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=boundary");

            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(boundary + "--" + end);
            outputStream.writeBytes("Content-Disposition: from-data; name=\"file\"; filename=\"abc.png\"" + end);
            outputStream.writeBytes(end);

            inputStream = new FileInputStream("/sdcard/abc.png");

            byte[] buffer = new byte[1024];
            int count = 0;

            while ((count = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, count);
            }

            inputStream.close();
            outputStream.writeBytes(boundary + "--" + end);
            outputStream.flush();
            outputStream.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
