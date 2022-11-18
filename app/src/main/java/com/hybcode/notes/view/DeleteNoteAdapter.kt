package com.hybcode.notes.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.Navigation
import com.hybcode.notes.data.model.Note
import com.hybcode.notes.databinding.FragmentDeleteNoteBinding


class DeleteNoteAdapter(
    var notes: List<Note>
) : RecyclerView.Adapter<DeleteNoteAdapter.ViewHolder>() {

    private val notesToBeDeleted = mutableListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentDeleteNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)

        holder.checkBox.setOnClickListener{
            if (holder.checkBox.isChecked) notesToBeDeleted.add(note) else notesToBeDeleted.remove(note)
            return@setOnClickListener
        }

        holder.itemView.rootView.setOnClickListener {
            navigateBack(it)
        }
    }

    fun notesToBeDeleted(): List<Note>{
        return notesToBeDeleted
    }

    override fun getItemCount(): Int = notes.size

    inner class ViewHolder(private val binding: FragmentDeleteNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val titleView: TextView = binding.title
        private val contentView: TextView = binding.contents
        val checkBox: CheckBox = binding.checkBox

        fun bind(note: Note){
            titleView.text = note.title
            contentView.text = note.contents
        }
    }

    private fun navigateBack(view: View) {
        Navigation.findNavController(view).popBackStack()
    }
}