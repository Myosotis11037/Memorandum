package com.linya.memorandum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.linya.memorandum.db.SQLiteDB
import com.linya.memorandum.entity.NoteBean
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity : AppCompatActivity() , View.OnClickListener{
    var id : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        val intent = getIntent()
        id = intent.getStringExtra("id")
        var sqLiteDB = SQLiteDB.getInstance(this)
        val note : NoteBean? = sqLiteDB?.findNoteById(id!!)

        preview_title.setText(note?.title)
        preview_content.setText(note?.content)
        preview_create_time.setText("创建时间：${note?.create_time}")
        preview_update_time.setText("更新时间：${note?.update_time}")

        preview_content.movementMethod = ScrollingMovementMethod.getInstance()

        findViewById<TextView>(R.id.preview_content).setOnClickListener(this)

    }

    override fun onResume() {
        super.onResume()
        id = intent.getStringExtra("id")
        var sqLiteDB = SQLiteDB.getInstance(this)
        val note : NoteBean? = sqLiteDB?.findNoteById(id!!)

        preview_title.setText(note?.title)
        preview_content.setText(note?.content)
        preview_create_time.setText("创建时间：${note?.create_time}")
        preview_update_time.setText("更新时间：${note?.update_time}")

        preview_content.movementMethod = ScrollingMovementMethod.getInstance()
    }

    override fun onClick(v: View?) {
        intent = Intent(this, EditActivity::class.java)
        intent.putExtra("id", id)
        Toast.makeText(this, "进入编辑模式",Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }


}