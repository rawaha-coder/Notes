package com.hybcode.notes.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hybcode.notes.data.model.Note
import com.hybcode.notes.databinding.NotePreviewBinding

class NoteAdapter(var notes: List<Note>):
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    //private var noteClickListener: NoteClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NotePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note: Note = notes[position]
        holder.bind(note)
        holder.itemView.setOnClickListener {
            navigate(note, it)
        }
    }

    override fun getItemCount(): Int = notes.size

    inner class NoteViewHolder(private val binding: NotePreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(note: Note){
                binding.viewTitle.text = note.title
                binding.viewContents.text = note.contents
            }
    }

    private fun navigate(note: Note, view: View){
        val index = note.id
        val action = NoteListFragmentDirections.actionNoteListFragmentToDisplayNoteFragment(index)
        Navigation.findNavController(view).navigate(action)
    }

}

interface NoteClickListener {
    fun onClick(note: Note)
}

