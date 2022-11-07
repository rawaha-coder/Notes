package com.hybcode.notes.presentation

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.hybcode.notes.MainActivity
import com.hybcode.notes.R
import com.hybcode.notes.data.Note
import com.hybcode.notes.databinding.ShowNoteBinding

class ShowNote(private val note: Note, private val index: Int) : DialogFragment() {
    private var _binding: ShowNoteBinding? = null
    private val binding get() = _binding!!
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val callingActivity = activity as MainActivity
        val inflater = callingActivity.layoutInflater
        _binding = ShowNoteBinding.inflate(inflater)
        val builder = AlertDialog.Builder(callingActivity)
            .setView(binding.root)
        binding.textTitle.text = note.title
        binding.textContents.text = note.contents
        binding.buttonOK.setOnClickListener{
            dismiss()
        }
        binding.buttonDelete.setOnClickListener{
                    //callingActivity.deleteNote(index)
                    Toast.makeText(callingActivity, resources.getString(R.string.note_deleted),
                        Toast.LENGTH_SHORT).show()
                    dismiss()
                }
        return builder.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}