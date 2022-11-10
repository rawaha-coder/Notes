package com.hybcode.notes.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hybcode.notes.NoteApplication
import com.hybcode.notes.data.model.Note
import com.hybcode.notes.data.repository.NotesRepoRoomImp
import com.hybcode.notes.databinding.FragmentNoteListBinding
import com.hybcode.notes.viewmodel.NoteListViewModel
import com.hybcode.notes.viewmodel.NoteListViewModelFactory


class NoteListFragment : Fragment() {

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NoteListViewModel by activityViewModels {
        NoteListViewModelFactory(NotesRepoRoomImp(), NoteApplication())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener{
            val action = NoteListFragmentDirections.actionNoteListFragmentToNewNoteFragment()
            findNavController().navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter =  NoteAdapter(emptyList<Note>(), requireContext())


    }







    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}