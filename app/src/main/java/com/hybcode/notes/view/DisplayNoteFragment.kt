package com.hybcode.notes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hybcode.notes.databinding.FragmentDisplayNoteBinding

class DisplayNoteFragment : Fragment() {

    private val binding get() = _binding!!
    private var _binding: FragmentDisplayNoteBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDisplayNoteBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}