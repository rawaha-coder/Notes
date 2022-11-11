package com.hybcode.notes.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hybcode.notes.data.model.Note
import com.hybcode.notes.databinding.FragmentNoteListBinding
import com.hybcode.notes.viewmodel.NoteListViewModel
import com.hybcode.notes.viewmodel.NoteListViewModelFactory


class NoteListFragment : Fragment() {

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteListViewModel by viewModels {
        NoteListViewModelFactory(requireActivity().application)
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
        setupUI()
        binding.fab.setOnClickListener{
            val action = NoteListFragmentDirections.actionNoteListFragmentToNewNoteFragment()
            findNavController().navigate(action)
        }
    }

    private fun setupUI() {
        val adapter = setupAdapter()
        setupRecyclerView(adapter)
        observeListNotesChange(adapter)
    }

    private fun setupAdapter(): NoteAdapter {
        val adapter = NoteAdapter(listOf())
        return adapter
    }

    private fun setupRecyclerView(noteAdapter:NoteAdapter) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = noteAdapter
            setHasFixedSize(true)
        }
    }

    fun observeListNotesChange(noteAdapter:NoteAdapter){
        viewModel.noteList.observe(viewLifecycleOwner, Observer {
            it?.apply {
                noteAdapter.notes = it
                binding.recyclerView.adapter = noteAdapter
            }
        })
    }

    private fun deleteListNote(list: List<Note>) {
        viewModel.noteList.value?.let {
            viewModel.deleteNotesList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}