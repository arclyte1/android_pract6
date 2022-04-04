package com.example.pract6

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.pract6.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val keyField = binding.editTextTextPersonName
        val textField = binding.editTextTextMultiLine
        val pref = getPreferences(Context.MODE_PRIVATE)

        // Creating database instance
        val db = Room.databaseBuilder(
            applicationContext,
            WordDB::class.java, "word-db"
        ).allowMainThreadQueries().build()

        // Saving ro SharedPreferences
        binding.saveSP.setOnClickListener {
            with (pref.edit()) {
                putString(keyField.text.toString(), textField.text.toString())
                apply()
            }
        }

        // Reading from SharedPreferences
        binding.readSP.setOnClickListener {
            textField.setText(pref.getString(keyField.text.toString(), ""))
        }

        // Saving to file
        binding.saveFile.setOnClickListener {
            if (keyField.text.toString() != "") {
                openFileOutput(keyField.text.toString(), Context.MODE_PRIVATE).use {
                    it.write(textField.text.toString().toByteArray())
                }
            }
        }

        // Reading from file
        binding.readFile.setOnClickListener {
            try {
                textField.setText(openFileInput(keyField.text.toString()).bufferedReader().readText())
            }
            catch (e: Exception) {
                textField.setText("")
            }
        }

        // Saving to database
        binding.saveDB.setOnClickListener {
            if (keyField.text.toString() != "") {
                val word = Word(keyField.text.toString(), textField.text.toString())
                db.wordDao().insertAll(word)
            }
        }

        // Reading from database
        binding.readDB.setOnClickListener {
            try {
                val word = db.wordDao().getByKey(keyField.text.toString())
                textField.setText(word.value)
            }
            catch (e: Exception) {
                textField.setText("")
            }
        }
    }
}