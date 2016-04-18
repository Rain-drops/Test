package com.sgj.ayibang.utils;

import com.sgj.ayibang.model.Person;
import com.sgj.util.GenericsUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by John on 2016/4/14.
 */
public class XMLUtils<T> {

    int index = 0;

    public void parse(File fileToParse, Class clazz){

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            //设置支持命名空间
            factory.setNamespaceAware(true);

            XmlPullParser parser = factory.newPullParser();

            FileInputStream inputStream = new FileInputStream(fileToParse);
            parser.setInput(inputStream, "UTF-8");

            int eventType = parser.getEventType();

            ArrayList<Person> persons = null;

            while (XmlPullParser.END_DOCUMENT != eventType){
                if(XmlPullParser.START_TAG == eventType){
                    String startTagName = parser.getName();
                    if("".equals(startTagName)){

                    }else if("".equals(startTagName)){

                    }
                }else if(XmlPullParser.END_TAG == eventType){
                    String endTagName = parser.getName();
                    if("".equals(endTagName)){

                    }else if("".equals(endTagName)){

                    }
                }

                eventType = parser.next();
            }



        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
