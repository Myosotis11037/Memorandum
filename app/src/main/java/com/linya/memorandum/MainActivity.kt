package com.linya.memorandum

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.linya.memorandum.adapter.NoteAdapter
import com.linya.memorandum.db.SQLiteDB
import com.linya.memorandum.entity.NoteBean
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.core_base_list_layout.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val noteBeanList: MutableList<NoteBean> = ArrayList()
    private var mNoteAdapter: NoteAdapter? = null
    private var isFirst = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        loadData()
        mNoteAdapter = NoteAdapter(this, noteBeanList)
        core_list_view_id.layoutManager = layoutManager
        core_list_view_id.adapter = mNoteAdapter


        findViewById<ImageView>(R.id.add_note).setOnClickListener(this)
        findViewById<ImageView>(R.id.search).setOnClickListener(this)

    }

    override fun onResume() {
        super.onResume()
        if (!isFirst) loadData()
        isFirst = false
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            //点击新增icon
            R.id.add_note -> {
                val intent = Intent(this, WriteNoteActivity::class.java)
                startActivity(intent)
            }

            //点击搜索
            R.id.search -> {
                val type = edit_search_context.text.toString()
                if(type == ""){
                    loadData()
                    mNoteAdapter = NoteAdapter(this, noteBeanList)
                    mNoteAdapter!!.notifyDataSetChanged()
                    core_list_view_id.layoutManager = LinearLayoutManager(this)
                    core_list_view_id.adapter = mNoteAdapter
                }else{
                    noteBeanList.clear()
                    noteBeanList.addAll(SQLiteDB.getInstance(this)?.getAllNotesByType(type) ?: ArrayList())
                    note_count.text = String.format("共%s个备忘录", noteBeanList.size)
                    mNoteAdapter = NoteAdapter(this, noteBeanList)
                    mNoteAdapter!!.notifyDataSetChanged()
                    core_list_view_id.layoutManager = LinearLayoutManager(this)
                    core_list_view_id.adapter = mNoteAdapter
                }

            }
        }
    }

    private fun loadData() {
        noteBeanList.clear()
        noteBeanList.addAll(SQLiteDB.getInstance(this)?.getAllNotes() ?: ArrayList())
        note_count.text = String.format("共%s个备忘录", noteBeanList.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)

        mNoteAdapter = NoteAdapter(this, noteBeanList)
        mNoteAdapter!!.notifyDataSetChanged()
        core_list_view_id.layoutManager = LinearLayoutManager(this)
        core_list_view_id.adapter = mNoteAdapter

    }

}