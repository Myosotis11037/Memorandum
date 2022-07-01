package com.linya.memorandum

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.linya.memorandum.adapter.NoteAdapter
import com.linya.memorandum.db.SQLiteDB
import com.linya.memorandum.entity.NoteBean
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.core_base_list_layout.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private val noteBeanList : MutableList<NoteBean> = ArrayList()
    private var mNoteAdapter: NoteAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        noteBeanList.clear()
        noteBeanList.addAll(SQLiteDB.getInstance(this)?.getAllNotes()?:ArrayList())
        note_count.text = String.format("共%s个备忘录",noteBeanList.size)
        mNoteAdapter = NoteAdapter(this, noteBeanList)
        core_list_view_id.layoutManager = layoutManager
        core_list_view_id.adapter = mNoteAdapter

        findViewById<ImageView>(R.id.add_note).setOnClickListener(this)

    }

    override fun onClick(v : View?) {
        when(v?.id){
            //点击新增icon
            R.id.add_note ->{
                val intent = Intent(this, WriteNoteActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        note_count.text = String.format("共%s个备忘录",noteBeanList.size)

    }

}