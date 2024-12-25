package com.example.ehiryemek

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class YemekEkleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yemek_ekle)

        val edtYemekAdi: EditText = findViewById(R.id.edtYemekAdi)
        val edtMalzemeler: EditText = findViewById(R.id.edtMalzemeler)
        val edtTarif: EditText = findViewById(R.id.edtTarif)
        val btnKaydet: Button = findViewById(R.id.btnKaydet)

        btnKaydet.setOnClickListener {
            val yemekAdi = edtYemekAdi.text.toString()
            val malzemeler = edtMalzemeler.text.toString()
            val tarif = edtTarif.text.toString()

                if (yemekAdi.isNotEmpty() && malzemeler.isNotEmpty() && tarif.isNotEmpty()) {
                    val intent = Intent()
                    intent.putExtra("yemekAdi", yemekAdi)
                    intent.putExtra("malzemeler", malzemeler)
                    intent.putExtra("tarif", tarif)
                    setResult(Activity.RESULT_OK, intent)
                    setResult(Activity.RESULT_OK, intent)
                    startActivity(Intent(this, MainActivity::class.java)) // Ana ekrana yönlendir

                    finish() // Activity'yi kapat

                }
            }
    }
}
