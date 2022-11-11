package com.hybcode.notes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hybcode.notes.data.model.Note
import com.hybcode.notes.databinding.FragmentNewNoteBinding
import com.hybcode.notes.viewmodel.NoteListViewModel
import com.hybcode.notes.viewmodel.NoteListViewModelFactory
import java.util.*

class NewNoteFragment : Fragment() {

    private var _binding : FragmentNewNoteBinding? = null
    private val binding get() = _binding!!
    private var note : Note? = null
    private val viewModel: NoteListViewModel by viewModels {
        NoteListViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val index = arguments?.getInt("index") ?: -1
        val update = arguments?.getBoolean("update") ?: false
        if (update) displayNote(index)
        binding.fabSave.setOnClickListener {
            if (update){
                updateNote(index)
            }else{
                saveNewNote()
            }
            findNavController().popBackStack()
        }
    }

    private fun updateNote(index: Int) {
        note = Note(id = index,
            title = binding.editNoteTitle.text.toString(),
            contents = binding.editNoteTitle.text.toString(),
            releaseDate = Date()
        )
        viewModel.updateNote(note!!)
    }

    private fun saveNewNote() {
        val note = Note(
            title = binding.editNoteTitle.text.toString(),
            contents = binding.editNoteTitle.text.toString(),
            releaseDate = Date()
        )
        viewModel.saveNote(note)
    }

    private fun displayNote(index: Int) {
        viewModel.noteList.observe(viewLifecycleOwner) { list ->
            note = list?.filter { note ->
                note.id == index
            }?.get(0)
            note?.let { setupNote(it) }
        }
    }

    private fun setupNote(note: Note){
         binding.editNoteContents.setText(note.contents)
        binding.editNoteTitle.setText(note.title)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}