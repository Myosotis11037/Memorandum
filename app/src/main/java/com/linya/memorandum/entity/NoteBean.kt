package com.linya.memorandum.entity

import android.os.Parcel
import android.os.Parcelable

class NoteBean() : Parcelable {
    var note_id: String ?= null
    var title: String ?= null
    var type: String ?= null
    var update_time: String ?= null
    var create_time: String ?= null
    var content: String ?= null


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(note_id)
        parcel.writeString(title)
        parcel.writeString(type)
        parcel.writeString(update_time)
        parcel.writeString(create_time)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NoteBean> {
        override fun createFromParcel(parcel: Parcel): NoteBean {
            val noteBean = NoteBean()
            noteBean.note_id = parcel.readString() ?: "" //读取id
            noteBean.title = parcel.readString() ?: "" //读取title
            noteBean.create_time = parcel.readString() ?: "" //读取创建时间
            noteBean.type = parcel.readString() ?: "" //读取种类（个人、生活、工作）
            noteBean.update_time = parcel.readString() ?: "" //读取更新时间
            noteBean.content = parcel.readString() ?: "" //读取备忘录内容

            return noteBean
        }

        override fun newArray(size: Int): Array<NoteBean?> {
            return arrayOfNulls(size)
        }
    }


}