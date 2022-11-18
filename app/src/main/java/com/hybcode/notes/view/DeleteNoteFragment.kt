package com.hybcode.notes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.hybcode.notes.data.model.Note
import com.hybcode.notes.databinding.FragmentDeleteNoteListBinding
import com.hybcode.notes.viewmodel.NoteListViewModel
import com.hybcode.notes.viewmodel.NoteListViewModelFactory

/**
 * A fragment representing a list of Items.
 */
class DeleteNoteFragment : Fragment() {

    private var _binding : FragmentDeleteNoteListBinding? = null
    private val binding get() = _binding!!
    private var adapter : DeleteNoteAdapter? = null

    private val viewModel: NoteListViewModel by viewModels {
        NoteListViewModelFactory(requireActivity().application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeleteNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        binding.fabDelete.setOnClickListener{
            val notesToBeDeleted = adapter?.notesToBeDeleted()
            if (notesToBeDeleted != null) {
                if (notesToBeDeleted.isNotEmpty())
                    viewModel.deleteNotesList(notesToBeDeleted)
            }
        }
    }

    private fun setupUI() {
        adapter = setupAdapter()
        setupRecyclerView(adapter!!)
        observeListNotesChange(adapter!!)
    }

    private fun setupAdapter(): DeleteNoteAdapter {
        return DeleteNoteAdapter(listOf())
    }

    private fun setupRecyclerView(noteAdapter:DeleteNoteAdapter) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = noteAdapter
            setHasFixedSize(true)
        }
    }

    fun observeListNotesChange(noteAdapter:DeleteNoteAdapter){
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