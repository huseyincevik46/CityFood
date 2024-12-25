package com.example.ehiryemek

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OyunActivity : AppCompatActivity() {
    private lateinit var txtKarisikHarfler: TextView
    private lateinit var edtCevap: EditText
    private lateinit var btnKontrol: Button
    private lateinit var txtSkor: TextView

    private var skor = 0
    private var currentCity = ""
    private var shuffledCity = ""

    private val cities = listOf("Bayburt", "Ankara", "İstanbul", "İzmir", "Antalya")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oyun)


        txtKarisikHarfler = findViewById(R.id.txtKarisikHarfler)
        edtCevap = findViewById(R.id.edtCevap)
        btnKontrol = findViewById(R.id.btnKontrol)
        txtSkor = findViewById(R.id.txtSkor)

        // İlk şehir ve karışık harfleri oluştur
        generateNewCity()

        // Kontrol Butonu İşlemi
        btnKontrol.setOnClickListener {
            val userAnswer = edtCevap.text.toString().trim()

            if (userAnswer.equals(currentCity, ignoreCase = true)) {
                skor++
                txtSkor.text = "Skor: $skor"
                Toast.makeText(this, "Doğru! Yeni şehre geçiliyor.", Toast.LENGTH_SHORT).show()
                generateNewCity()
            } else {
                Toast.makeText(this, "Yanlış! Tekrar deneyin.", Toast.LENGTH_SHORT).show()
            }

            edtCevap.text.clear()
        }
    }

    // Yeni Şehir Oluştur ve Karıştır
    private fun generateNewCity() {
        currentCity = cities.random()
        shuffledCity = currentCity.toCharArray().apply { shuffle() }.concatToString()
        txtKarisikHarfler.text = shuffledCity
    }
}
