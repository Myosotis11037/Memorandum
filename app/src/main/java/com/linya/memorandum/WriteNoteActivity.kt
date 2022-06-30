package com.linya.memorandum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.linya.memorandum.db.SQLiteDB
import com.linya.memorandum.entity.NoteBean
import kotlinx.android.synthetic.main.activity_write_note.*
import java.text.SimpleDateFormat
import java.util.*

class WriteNoteActivity : AppCompatActivity() , View.OnClickListener{
    private var mSQLiteDB : SQLiteDB ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_note)

        findViewById<Button>(R.id.add).setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        var title = edit_title?.text.toString()
        var content = edit_content?.text.toString()
        val date = Date()
        val addTime = SimpleDateFormat("yyyy-MM-dd HH:mm").format(date)
        val updateTime = SimpleDateFormat("yyyy-MM-dd HH:mm").format(date)
        val type = edit_type?.text.toString()
        val note_id = date.time.toString()

        val noteBean = NoteBean()
        noteBean.note_id = note_id
        noteBean.type = type
        noteBean.title = title
        noteBean.content = content
        noteBean.create_time = addTime
        noteBean.update_time = updateTime
        mSQLiteDB = SQLiteDB.getInstance(this)

        if(mSQLiteDB?.addNote(noteBean)!!){
            Toast.makeText(this,"保存成功！",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, "保存失败！",Toast.LENGTH_SHORT).show()
        }


    }
}