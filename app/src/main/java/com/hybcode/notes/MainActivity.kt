package com.hybcode.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hybcode.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

//    override fun onStart() {
//        super.onStart()
//        val nightThemeSelected = sharedPreferences.getBoolean("theme", false)
//        if (nightThemeSelected) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        val showDividingLines = sharedPreferences.getBoolean("dividingLines", false)
//        if (showDividingLines) binding.recyclerView.addItemDecoration(
//            DividerItemDecoration(this,
//            LinearLayoutManager.VERTICAL)
//        )
//        else if (binding.recyclerView.itemDecorationCount > 0) binding.recyclerView.removeItemDecorationAt(0)
//    }

//

//
//    fun createNewNote(note: Note) {
//        adapter.noteList.add(note)
//        adapter.notifyItemInserted(adapter.noteList.size -1)
//        saveNotes()
//    }
//
//    private fun saveNotes() {
//        val notes = adapter.noteList
//        val gson = GsonBuilder().create()
//        val jsonNotes = gson.toJson(notes)
//        var writer: Writer? = null
//        try {
//            val out = this.openFileOutput(FILEPATH, Context.MODE_PRIVATE)
//            writer = OutputStreamWriter(out)
//            writer.write(jsonNotes)
//        } catch (e: Exception) {
//            writer?.close()
//        } finally {
//            writer?.close()
//        }
//    }
//
//    private fun retrieveNotes(): MutableList<Note>{
//        var noteList = mutableListOf<Note>()
//        if (this.getFileStreamPath(FILEPATH).isFile){
//            var reader: BufferedReader? = null
//            try {
//                val fileInput = this.openFileInput(FILEPATH)
//                reader = BufferedReader(InputStreamReader(fileInput))
//                val stringBuilder = StringBuilder()
//                for (line in reader.readLine()) stringBuilder.append(line)
//                if (stringBuilder.isNotEmpty()){
//                    val listType = object : TypeToken<List<Note>>() {}.type
//                    noteList = Gson().fromJson(stringBuilder.toString(), listType)
//                }
//            } catch (e: Exception) {
//                reader?.close()
//            } finally {
//                reader?.close()
//            }
//        }
//        return noteList
//    }
//
//    fun deleteNote(index: Int) {
//        adapter.noteList.removeAt(index)
//        adapter.notifyItemRemoved(index)
//        saveNotes()
//    }
//
//    fun showNote(index: Int) {
//        val dialog = ShowNote(adapter.noteList[index], index)
//        dialog.show(supportFragmentManager, "")
//    }
}