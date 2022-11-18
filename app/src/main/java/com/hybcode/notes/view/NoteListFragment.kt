package com.hybcode.notes.view

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hybcode.notes.R
import com.hybcode.notes.databinding.FragmentNoteListBinding
import com.hybcode.notes.viewmodel.NoteListViewModel
import com.hybcode.notes.viewmodel.NoteListViewModelFactory


class NoteListFragment : Fragment() {

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private val viewModel: NoteListViewModel by viewModels {
        NoteListViewModelFactory(requireActivity().application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
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
        navController = findNavController()
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

    private fun setupAdapter(): NoteListAdapter {
        val adapter = NoteListAdapter(listOf())
        return adapter
    }

    private fun setupRecyclerView(noteAdapter:NoteListAdapter) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = noteAdapter
            setHasFixedSize(true)
        }
    }

    fun observeListNotesChange(noteAdapter:NoteListAdapter){
        viewModel.noteList.observe(viewLifecycleOwner, Observer {
            it?.apply {
                noteAdapter.notes = it
                binding.recyclerView.adapter = noteAdapter
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}