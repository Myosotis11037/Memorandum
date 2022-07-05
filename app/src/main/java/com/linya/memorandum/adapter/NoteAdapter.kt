package com.linya.memorandum.adapter//package com.linya.memorandum.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.linya.memorandum.MainActivity
import com.linya.memorandum.PreviewActivity
import com.linya.memorandum.R
import com.linya.memorandum.db.SQLiteDB
import com.linya.memorandum.entity.NoteBean
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(con : Context, private val noteList : MutableList<NoteBean>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>(){

    private val context : Context = con

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

        holder.itemView.note_delete.setOnClickListener {
            val pos = holder.absoluteAdapterPosition
            val note = noteList[pos]
            val id = note.note_id
            var sqLiteDB = SQLiteDB.getInstance(holder.itemView.context)
            sqLiteDB?.delNote(id!!)
            Toast.makeText(holder.itemView.context, "删除成功！",Toast.LENGTH_SHORT).show()
            noteList.removeAt(pos)
            notifyDataSetChanged()
            val intent = Intent(holder.itemView.context, MainActivity::class.java)
            context.startActivity(intent)
        }

        holder.itemView.setOnClickListener{
            val pos = holder.absoluteAdapterPosition
            val note = noteList[pos]
            val intent = Intent(context, PreviewActivity::class.java)
            intent.putExtra("id", note.note_id)
            context.startActivity(intent)
        }

        
    }

    override fun getItemCount() = noteList.size



}
