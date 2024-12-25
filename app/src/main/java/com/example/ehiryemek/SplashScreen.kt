package com.example.ehiryemek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.TextView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val countdownText = findViewById<TextView>(R.id.countdownText)
        // obje tanımamız gerekiyor. object : ise abstract sınıfdam obje üretimi

        val timer = object : CountDownTimer(5000, 1000) { // 5000ms = 5 saniye, 1000ms = 1 saniye aralık
            override fun onTick(millisUntilFinished: Long) {
                // Geriye kalan saniyeyi göster
                countdownText.text = "  ${millisUntilFinished / 1000} saniye"
            }

            override fun onFinish() {
                // Süre dolduğunda ana ekrana geçiş yap
                val intent = Intent(this@SplashScreen, LoginActivity::class.java)
                startActivity(intent)
                finish() // Splash ekranını kapat
            }
        }

        timer.start()
    }
}