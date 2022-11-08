package com.hybcode.notes.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hybcode.notes.databinding.FragmentNoteListBinding


class NoteListFragment : Fragment() {

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!

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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}