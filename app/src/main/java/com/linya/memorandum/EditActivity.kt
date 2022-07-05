package com.linya.memorandum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.linya.memorandum.db.SQLiteDB
import com.linya.memorandum.entity.NoteBean
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_write_note.*
import java.text.SimpleDateFormat
import java.util.*

class EditActivity : AppCompatActivity(), View.OnClickListener {
    var id : String? = ""
    var title : String? = ""
    var type : String? = ""
    var content : String? = ""
    var update_time : String? = ""
    var create_time : String? = ""

    private var msqLiteDB : SQLiteDB? = SQLiteDB.getInstance(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val intent = getIntent()
        id = intent.getStringExtra("id")
        val note : NoteBean? = msqLiteDB?.findNoteById(id!!)
        title = note!!.title
        type = note!!.type
        content = note!!.content
        update_time = note!!.update_time
        create_time = note!!.create_time

        update_edit_title?.setText(title)
        update_edit_type?.setText(type)
        update_edit_content?.setText(content)

        update.setOnClickListener(this)
        cancel.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.update -> {
                val newTitle = update_edit_title.text.toString()
                val newType = update_edit_type.text.toString()
                val newContent = update_edit_content.text.toString()

                val newNote : NoteBean = NoteBean()
                newNote.note_id = id
                newNote.title = newTitle
                newNote.type = newType
                newNote.content = newContent
                newNote.create_time = create_time
                newNote.update_time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())

                if(msqLiteDB?.updateNote(newNote)!!) {
                    val intent = Intent(this, PreviewActivity::class.java)
                    intent.putExtra("id",newNote.note_id)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "保存失败！", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.cancel -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }


    }
}