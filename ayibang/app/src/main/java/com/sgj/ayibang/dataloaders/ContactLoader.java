package com.sgj.ayibang.dataloaders;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.List;

/**
 * Created by John on 2016/3/24.
 */
public class ContactLoader {
    private static final long[] cEmptyList = new long[0];

    public static List getAllContact(Context context){

        return null;
    }

    public static Cursor makeContactCursor(Context context, String selection, String[] paramArrayOfString){

        String[] projections = new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME};
        Cursor cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                projections, //需返回到字段
                selection, //where语句
                paramArrayOfString, //where中的占位参数
                null);// Order BY/Group By

        return cursor;
    }
}
