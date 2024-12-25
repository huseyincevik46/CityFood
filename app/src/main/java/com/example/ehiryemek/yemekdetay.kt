package com.example.ehiryemek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ehiryemek.databinding.ActivityMainBinding
import com.example.ehiryemek.databinding.ActivityYemekdetayBinding

class yemekdetay : AppCompatActivity() {
    lateinit var binding: ActivityYemekdetayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYemekdetayBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        var intent = intent
        var isim = intent.getStringExtra("name")
        var malzeme = intent.getStringExtra("malzeme")
        var tarif = intent.getStringExtra("tarif")

        binding.isimid.text= isim
        binding.malzemeid.text=malzeme
        binding.tarifid.text = tarif

    }
}