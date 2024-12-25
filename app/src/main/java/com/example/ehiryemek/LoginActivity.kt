package com.example.ehiryemek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreferences = getSharedPreferences("kullanicilar", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val kullaniciInput = findViewById<EditText>(R.id.editTextUsername)
        val sifreInput = findViewById<EditText>(R.id.editTextPassword)
        val hatirlaBox = findViewById<CheckBox>(R.id.checkBoxRemember)
        val girisButton =findViewById<Button>(R.id.buttonLogin)

        val kaydetUsername = sharedPreferences.getString("kullanıcıAdi","")
        val kaydetPassword = sharedPreferences.getString("sifre","")
        val isRemembered = sharedPreferences.getBoolean("hatirla",false )

        if(isRemembered){
            kullaniciInput.setText(kaydetUsername)
            sifreInput.setText(kaydetPassword)
            hatirlaBox.isChecked =true
        }

        // Giriş butonu tıklama işlemi
        girisButton.setOnClickListener {
            val kullaniciAdi = kullaniciInput.text.toString()
            val sifre = sifreInput.text.toString()
            val hatirla = hatirlaBox.isChecked


            // null değerlrin kontrolü
            if (kullaniciAdi.isNotEmpty() && sifre.isNotEmpty()){
                if(kullaniciAdi == "Hüseyin" && sifre =="1234"){
                    if(hatirla){
                        //bilgileri kayfetme
                        editor.putString("kullanıcıAdi",kullaniciAdi)
                        editor.putString("sifre",sifre)
                        editor.putBoolean("hatirla",hatirla)
                    }else{
                        editor.clear()
                    }

                    editor.apply()
                    // yemek sayfasına geçiş
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "Giriş başarılı!", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this,"kullanıcı veya şifre yanlış",Toast.LENGTH_SHORT).show()

                }

            }else{
                Toast.makeText(this,"Lütfen tüm alanları doldur !!",Toast.LENGTH_SHORT).show()
            }
        }

    }
}