package com.sgj.ayibang.utils;

import android.provider.MediaStore;

import java.util.IllegalFormatException;

/**
 * Created by John on 2016/4/27.
 */
public class TimberUtils {

    public enum IdType{
        NA(0),
        Artist(1),
        Album(2),
        Playlist(3);

        public final int mId;

        IdType(final int id){
            this.mId = id;
        }

        public static IdType getTypeById(int id){
            for(IdType type : values()){
                if(type.mId == id){
                    return type;
                }
            }

            throw new IllegalArgumentException("Unrecognized id:" + id);
        }

    }
}
