package com.hybcode.notes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.hybcode.notes.data.model.Note
import com.hybcode.notes.databinding.FragmentDisplayNoteBinding
import com.hybcode.notes.viewmodel.NoteListViewModel

class DisplayNoteFragment : Fragment() {

    private val binding get() = _binding!!
    private var _binding: FragmentDisplayNoteBinding? = null
    private val viewModel: NoteListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDisplayNoteBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}