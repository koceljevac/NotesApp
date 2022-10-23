package com.nikasoft.notesappproject

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDao: NotesDao) {
    val allNotesDao: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note){
        notesDao.insert(note)
    }

    suspend fun delet(note: Note){
        notesDao.delete(note)
    }

    suspend fun update(note:Note){
        notesDao.update(note)
    }
}