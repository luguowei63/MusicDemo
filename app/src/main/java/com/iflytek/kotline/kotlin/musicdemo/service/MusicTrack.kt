package com.iflytek.kotline.kotlin.musicdemo.service

import android.os.Parcel
import android.os.Parcelable

/**
 *Created by lgw on 2020/10/15
 */
class MusicTrack() :Parcelable{

    val CREATOR: Parcelable.Creator<MusicTrack?> = object : Parcelable.Creator<MusicTrack?> {
        override fun createFromParcel(source: Parcel): MusicTrack? {
            return MusicTrack(source)
        }

        override fun newArray(size: Int): Array<MusicTrack?> {
            return arrayOfNulls(size)
        }
    }
    var mId: Long = 0
    var mSourcePosition = 0
    var mTitle: String? = null
    var mAlbum: String? = null
    var mArtist: String? = null


    constructor(id: Long, sourcePosition: Int) : this() {
        mId = id
        mSourcePosition = sourcePosition
//        mTitle = title;
//        mArtist = artist;
//        mAlbum = album;
    }

    constructor(`in`: Parcel) : this() {
        mId = `in`.readLong()
        mSourcePosition = `in`.readInt()
//        mTitle = in.readString();
//        mArtist = in.readString();
//        mAlbum = in.readString();
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(mId)
        dest.writeInt(mSourcePosition)
//        dest.writeString(mTitle);
//        dest.writeString(mArtist);
//        dest.writeString(mArtist);
    }

}