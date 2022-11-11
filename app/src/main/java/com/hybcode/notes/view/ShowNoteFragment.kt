package com.hybcode.notes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hybcode.notes.data.model.Note
import com.hybcode.notes.databinding.FragmentDisplayNoteBinding
import com.hybcode.notes.viewmodel.NoteListViewModel
import com.hybcode.notes.viewmodel.NoteListViewModelFactory

class ShowNoteFragment : Fragment() {

    private val binding get() = _binding!!
    private var _binding: FragmentDisplayNoteBinding? = null
    private val viewModel: NoteListViewModel by viewModels{
        NoteListViewModelFactory(requireActivity().application)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDisplayNoteBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val index = arguments?.getInt("index") ?: -1
        displayNote(index)
        binding.displayNote.setOnClickListener{
            val action = ShowNoteFragmentDirections.actionDisplayNoteFragmentToNewNoteFragment(index, true)
            findNavController().navigate(action)
        }
    }

    private fun displayNote(index: Int) {
        viewModel.noteList.observe(viewLifecycleOwner) { list ->
            val note = list?.filter { note ->
                note.id == index
            }?.get(0)
            if (note != null) {
                setupNote(note)
            }
        }
    }

    private fun setupNote(note: Note){
        binding.noteContent.text = note.contents
        binding.noteTitle.text = note.title
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
