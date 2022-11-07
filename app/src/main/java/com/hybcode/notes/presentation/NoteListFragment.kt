package com.hybcode.notes.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.hybcode.notes.R
import com.hybcode.notes.databinding.FragmentNoteListBinding


class NoteListFragment : Fragment() {

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NoteListViewModel by viewModels()
    private lateinit var noteAdapter: NoteAdapter

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
    }

    private fun setupUI() {
        setupMenu()
        setupAdapter()
        setupRecyclerView(noteAdapter)
    }

    private fun setupMenu() {
        binding.toolbar.inflateMenu(R.menu.menu_main)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> {
                    // Navigate to settings screen
                    true
                }
                else -> false
            }
        }
    }

    private fun setupAdapter() {
        noteAdapter = NoteAdapter()
        noteAdapter.noteList = viewModel.retrieveNotes(requireActivity())
        noteAdapter.notifyItemRangeInserted(0, noteAdapter.noteList.size)
    }

    private fun setupRecyclerView(noteAdapter: NoteAdapter) {
        binding.recyclerView.apply {
            adapter = noteAdapter as NoteAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}