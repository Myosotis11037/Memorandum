package com.linya.memorandum.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.linya.memorandum.logic.model.LoginResponse
import retrofit2.Callback
import java.util.prefs.PreferencesFactory

class DataBaseHelper(
    private val context: Context,
    name: String,
    version: Int,
):SQLiteOpenHelper(context, name, null, version)  {

    private val createNote = "create table note(" +
            "note_id Integer Primary key," +
            "title varchar(20)," +
            "type varchar(20)," +
            "content TEXT," +
            "update_time DATE_TIME," +
            "create_time DATE_TIME)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createNote)
        Toast.makeText(context , "Create database succeed", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }
}