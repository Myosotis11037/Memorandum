package com.linya.memorandum.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.linya.memorandum.entity.NoteBean
import kotlinx.coroutines.sync.Mutex

class SQLiteDB constructor(private val context : Context) {
    init{
        var dataBaseHelper = DataBaseHelper(context, DATABASE_NAME, DATABASE_VERSION)
        mSQLiteDatabase = dataBaseHelper.readableDatabase
    }

    companion object {
        private val DATABASE_NAME: String = "note.db";
        private val DATABASE_VERSION = 1;
        private var mSQLiteDB: SQLiteDB? = null
        private var mSQLiteDatabase: SQLiteDatabase? = null


        fun getInstance(context: Context): SQLiteDB? {
            if (mSQLiteDB == null) {
                mSQLiteDB = SQLiteDB(context)
            }
            return mSQLiteDB
        }
    }

    fun getAllNotes(): List<NoteBean>?{
        val noteBeans: MutableList<NoteBean> = ArrayList()
        val cursor = mSQLiteDatabase?.query("note", arrayOf("note_id","title","type","update_time","create_time","content"),null,null,null,null,"update_time desc",null)
        if(cursor != null){
            while(cursor.moveToNext()){
                val noteBean = NoteBean()
                noteBean.note_id = cursor.getString(cursor.getColumnIndexOrThrow("note_id"))
                noteBean.title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
                noteBean.type = cursor.getString(cursor.getColumnIndexOrThrow("type"))
                noteBean.update_time = cursor.getString(cursor.getColumnIndexOrThrow("update_time"))
                noteBean.create_time = cursor.getString(cursor.getColumnIndexOrThrow("create_time"))
                noteBean.content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
                noteBeans.add(noteBean)
            }
        }
        return noteBeans
    }

    fun getAllNotesByType(type : String) : List<NoteBean>?{
        val noteBeans: MutableList<NoteBean> = ArrayList()
        val cursor = mSQLiteDatabase?.rawQuery(
            "select * from note where type=?",
            arrayOf(type)
        )

        if(cursor != null){
            while(cursor.moveToNext()){
                val noteBean = NoteBean()
                noteBean.note_id = cursor.getString(cursor.getColumnIndexOrThrow("note_id"))
                noteBean.title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
                noteBean.type = cursor.getString(cursor.getColumnIndexOrThrow("type"))
                noteBean.update_time = cursor.getString(cursor.getColumnIndexOrThrow("update_time"))
                noteBean.create_time = cursor.getString(cursor.getColumnIndexOrThrow("create_time"))
                noteBean.content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
                noteBeans.add(noteBean)
            }
        }

        return noteBeans

    }

    fun addNote(noteBean: NoteBean):Boolean{
        var flag : Boolean = false
        var contentValues = ContentValues().apply {
            put("note_id", noteBean.note_id)
            put("title",noteBean.title)
            put("type",noteBean.type)
            put("create_time",noteBean.create_time)
            put("update_time",noteBean.update_time)
            put("content",noteBean.content)
        }
        var insert: Long? = mSQLiteDatabase?.insert("note", null, contentValues)
        var b = insert ?: 0 > 0
        if (b) {
            flag = true
        }
        return flag

    }

    fun findNoteById(id : String): NoteBean?{
        val noteBean = NoteBean()
        val cursor = mSQLiteDatabase?.rawQuery(
            "select * from note where note_id=?",
            arrayOf(id)
        )
        if(cursor != null){
            if(cursor.moveToFirst()){
                noteBean.note_id = cursor.getString(cursor.getColumnIndexOrThrow("note_id"))
                noteBean.title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
                noteBean.type = cursor.getString(cursor.getColumnIndexOrThrow("type"))
                noteBean.update_time = cursor.getString(cursor.getColumnIndexOrThrow("update_time"))
                noteBean.create_time = cursor.getString(cursor.getColumnIndexOrThrow("create_time"))
                noteBean.content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
            }
        }
        return noteBean
    }

    fun updateNote(noteBean: NoteBean): Boolean{
        var flag = false
        var contentValues = ContentValues()
        contentValues.put("title", noteBean.title)
        contentValues.put("type",noteBean.type)
        contentValues.put("content", noteBean.content)
        contentValues.put("update_time", noteBean.update_time)
        var update = mSQLiteDatabase?.update(
            "note", contentValues, "note_id=?",
            arrayOf(noteBean.note_id)
        )
        if (update ?: 0 > 0) {
            flag = true
        }
        return flag
    }

    fun delNote(title: String): Boolean {
        var flag = false
        var delete = mSQLiteDatabase?.delete("note", "note_id=?", arrayOf(title))
        if (delete ?: 0 > 0) {
            flag = true
        }
        return flag
    }
}