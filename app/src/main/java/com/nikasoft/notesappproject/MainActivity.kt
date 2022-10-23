package com.nikasoft.notesappproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.controls.actions.FloatAction
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),NoteClickInterface,NoteClickDeletInterface {
    lateinit var notesRV: RecyclerView
    lateinit var  addFAB : FloatingActionButton
    lateinit var viewModel: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesRV= findViewById(R.id.rvNotes)
        addFAB = findViewById(R.id.flyBtnAddNote)
        notesRV.layoutManager = LinearLayoutManager(this)

        val noteRvAdapter = NotesRVAdapter(this,this,this)
        notesRV.adapter = noteRvAdapter
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this,{ list->
            list?.let { noteRvAdapter.updateList(it) }
        })

        addFAB.setOnClickListener{
            val intent = Intent(this@MainActivity,AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }

    override fun onDeletIconClick(note: Note){
        viewModel.deletNote(note)
        Toast.makeText(this,"${note.noteTitle} Deleted",Toast.LENGTH_LONG).show()

    }

    override fun onNoteClick(note: Note) {
        val intent =Intent(this@MainActivity,AddEditNoteActivity::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("noteID",note.id)
        startActivity(intent)
        this.finish()



    }
}