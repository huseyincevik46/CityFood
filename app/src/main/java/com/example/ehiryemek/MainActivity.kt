package com.example.ehiryemek

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.ehiryemek.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var yemekliste = ArrayList<Yemekler>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding kullanımı
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var galacos = Yemekler("Galacos","Ekmek, yoğurt, kıyma, tereyağı, baharat","Tereyağında kavrulan kıyma ve baharatlarla yapılan sos, üzerine yoğurt ve küçük doğranmış ekmeklerin serildiği bir yemektir.")
        var lorDolması= Yemekler("Lor Dolması","Lor peyniri, pazı yaprağı, pirinç, soğan, zeytinyağı, baharat","Pazı yapraklarına pirinç ve lor peynir karışımı doldurularak sarılır ve yavaşça pişirilir.")
        var eksililahana = Yemekler("ekşili Lahana","Lahana, kuzu eti, soğan, domates salçası, baharat","Lahana ve et, salça ve baharatlarla birlikte pişirilir. Ekşi bir tat vermesi için limon ya da ekşi erik eklenebilir.")


        yemekliste.add(galacos)
        yemekliste.add(lorDolması)
        yemekliste.add(eksililahana)

        //adapter
        // map fonksiyonu
        var adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,yemekliste.map { yemekler -> yemekler.name   })
        binding.listView.adapter= adapter
        binding.listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            var intent = Intent(this,yemekdetay ::class.java)
            intent.putExtra("name",yemekliste.get(position).name)
            intent.putExtra("malzeme",yemekliste.get(position).malzeme)
            intent.putExtra("tarif",yemekliste.get(position).tarif)

            startActivity(intent)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.yemekmenu, menu) //
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.yemekekle -> {
                val intent = Intent(this, YemekEkleActivity::class.java)
                startActivityForResult(intent, 1)
                // Yemek Ekle seçeneğine tıklanınca yapılacak işlemler
                Toast.makeText(this, "Yemek Ekle seçildi", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.oyun -> {
                val intent = Intent(this, OyunActivity::class.java)
                startActivity(intent) // Oyun Activity'yi başlat

                // Oyun seçeneğine tıklanınca yapılacak işlemler
                Toast.makeText(this, "Oyun seçildi", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val yemekAdi = data?.getStringExtra("yemekAdi")
            val malzemeler = data?.getStringExtra("malzemeler")
            val tarif = data?.getStringExtra("tarif")

            if (yemekAdi != null && malzemeler != null && tarif != null) {
                // Yeni yemek nesnesi oluştur
                val yeniYemek = Yemekler(yemekAdi, malzemeler, tarif)

                // Listeye ekle
                yemekliste.add(yeniYemek)

                // Adapter'ı güncelle
                adapter.notifyDataSetChanged()
            }
        }
    }



}