package com.nikasoft.notesappproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(val context: Context,
                     val noteDeletInterface: NoteClickDeletInterface,
                     val notesClickInterface: NoteClickInterface
                     ): RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val noteTv = itemView.findViewById<TextView>(R.id.tvNoteTitle)
        val timeTv = itemView.findViewById<TextView>(R.id.tvTimeStamp)
        val deletTv =itemView.findViewById<ImageView>(R.id.ivDelet)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTv.setText(allNotes.get(position).noteTitle)
        holder.timeTv.setText("Last UpdateL "+allNotes.get(position).timeStamp)

        holder.deletTv.setOnClickListener {
            noteDeletInterface.onDeletIconClick(allNotes.get(position))
        }

        holder.itemView.setOnClickListener {
            notesClickInterface.onNoteClick(allNotes.get(position))
        }

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface NoteClickDeletInterface{
    fun onDeletIconClick(note: Note)
}

interface NoteClickInterface{
    fun onNoteClick(note: Note)
}