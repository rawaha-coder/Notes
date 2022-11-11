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
        binding.fabSave.setOnClickListener {
            val title = binding.editNoteTitle.text.toString()
            val contents = binding.editNoteTitle.text.toString()
            val note = Note(
                title = title,
                contents = contents,
                releaseDate = Date())
            viewModel.saveNote(note)
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}