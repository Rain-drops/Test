package com.sgj.ayibang.helpers;

import android.os.Parcel;
import android.os.Parcelable;

import com.sgj.ayibang.utils.TimberUtils;

/**
 * Created by John on 2016/4/27.
 */
public class MusicPlaybackTrack implements Parcelable {
    public long mId;
    public long mSourceId;
    public TimberUtils.IdType mSourceType;
    public int mSourcePosition;


    protected MusicPlaybackTrack(Parcel in) {
        mId = in.readLong();
        mSourceId = in.readLong();
        mSourcePosition = in.readInt();
        mSourceType = TimberUtils.IdType.getTypeById(in.readInt());
    }

    public MusicPlaybackTrack(long mId, long mSourceId, TimberUtils.IdType mSourceType, int mSourcePosition) {
        this.mId = mId;
        this.mSourceId = mSourceId;
        this.mSourceType = mSourceType;
        this.mSourcePosition = mSourcePosition;
    }

    public static final Creator<MusicPlaybackTrack> CREATOR = new Creator<MusicPlaybackTrack>() {
        @Override
        public MusicPlaybackTrack createFromParcel(Parcel in) {
            return new MusicPlaybackTrack(in);
        }

        @Override
        public MusicPlaybackTrack[] newArray(int size) {
            return new MusicPlaybackTrack[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeLong(mSourceId);
        dest.writeInt(mSourcePosition);
    }
}
