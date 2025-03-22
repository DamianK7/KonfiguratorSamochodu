package com.example.konfiguratorsamochodu

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageView = findViewById<ImageView>(R.id.imageViewAuto)
        val radioGroup = findViewById<RadioGroup>(R.id.rodzajNadwoziaRG)
        val zamowButton = findViewById<Button>(R.id.zamowButton)
        val checkKlima = findViewById<CheckBox>(R.id.checkKlima)
        val checkSiedzenia = findViewById<CheckBox>(R.id.checkSiedzenia)
        val podsumowanie = findViewById<TextView>(R.id.podsumowanie)


        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val imageResource = when (checkedId) {
                R.id.sedan -> R.drawable.sedan
                R.id.suv -> R.drawable.suv
                R.id.hatchback -> R.drawable.hatchback
                else -> R.drawable.sedan
            }
            imageView.setImageResource(imageResource)
        }

        zamowButton.setOnClickListener {
            val selectedCarId = radioGroup.checkedRadioButtonId
            val selectedCar = when (selectedCarId) {
                R.id.sedan -> "Sedan"
                R.id.suv -> "Suv"
                R.id.hatchback -> "Hatchback"
                else -> "Nie wybrano typu nadwozia"
            }


            val dodatkoweWyp = mutableListOf<String>()
            if (checkKlima.isChecked) dodatkoweWyp.add("Klimatyzacja")
            if (checkSiedzenia.isChecked) dodatkoweWyp.add("Skórzane siedzenia")

            val dodatkoweWypText = if (dodatkoweWyp.isNotEmpty()) dodatkoweWyp.joinToString(", ") else "Brak dodatkowego wyposażenia"

            val zamowienie = "Zamówienie:\nRodzaj nadwozia: $selectedCar\nDodatkowe wyposażenie: $dodatkoweWypText"

            podsumowanie.text = zamowienie
        }
    }
}
