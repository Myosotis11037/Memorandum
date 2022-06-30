package com.linya.memorandum.adapter//package com.linya.memorandum.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.linya.memorandum.R
import com.linya.memorandum.db.SQLiteDB
import com.linya.memorandum.entity.NoteBean
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(private val noteList : MutableList<NoteBean>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>(){
    private val setOnClickListener : View.OnClickListener ?= null

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val note_title : TextView = view.findViewById(R.id.note_title)
        val note_type : TextView = view.findViewById(R.id.note_type)
        val note_update_time : TextView = view.findViewById(R.id.note_update_time)
        val note_content : TextView = view.findViewById(R.id.note_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = noteList[position]
        holder.note_title.text = note.title
        holder.note_type.text = note.type
        holder.note_update_time.text = note.update_time
        holder.note_content.text = note.content

        holder.itemView.note_delete.setOnClickListener(){
            val pos = holder.absoluteAdapterPosition
            val note = noteList[pos]
            val title = note.title
            var sqLiteDB = SQLiteDB.getInstance(holder.itemView.context)
            sqLiteDB?.delNote(title!!)
            Toast.makeText(holder.itemView.context, "删除成功！",Toast.LENGTH_SHORT).show()
            noteList.removeAt(pos)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = noteList.size



}
