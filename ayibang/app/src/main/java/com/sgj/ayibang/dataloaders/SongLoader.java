package com.sgj.ayibang.dataloaders;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.sgj.ayibang.model.Song;

import java.util.ArrayList;

/**
 * Created by John on 2016/4/1.
 */
public class SongLoader {

    public static ArrayList<Song> getAllSongs(Context context) {
       return getSongsForCursor(makeSongCursor(context, null, null));
    }

    public static Cursor makeSongCursor(Context context, String selection, String[] paramArray){

        String selectionStatement = " is_music = 1 AND title != ''";
        if(!TextUtils.isEmpty(selection)){
            selectionStatement = selectionStatement + " AND " + selection;
        }

        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{"_id", "title", "artist", "album", "duration", "track", "artist_id", "album_id"},
                selectionStatement, paramArray, null);
        return cursor;
    }

    public static ArrayList<Song> getSongsForCursor(Cursor cursor) {
        ArrayList<Song> arrayList = new ArrayList<>();
        if((cursor != null) && (cursor.moveToNext())){
            do {
                long id = cursor.getLong(0);
                String title = cursor.getString(1);
                String artist = cursor.getString(2);
                String album = cursor.getString(3);
                int duration = cursor.getInt(4);
                int track = cursor.getInt(5);
                long artistId = cursor.getLong(6);
                long albunId = cursor.getLong(7);

                arrayList.add(new Song(id, albunId, artistId, title, artist,
                        album, duration, track));

            }while (cursor.moveToNext());
        }
        if(cursor != null){
            cursor.close();
        }
        return arrayList;
    }
}
