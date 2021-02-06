package com.example.onlinestorekotlin

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.util.ArrayList

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        val typeface: Typeface = Typeface.createFromAsset(assets,"Tabby - Display.ttf")
        title_brand_text_view.typeface = typeface



        var brandsUrl = "https://reggaerencontre.com/fetch_brands.php"
        var brandsList = ArrayList<String>()
        val requestQ: RequestQueue = Volley.newRequestQueue(this)
        var jsonAR = JsonArrayRequest(Request.Method.GET, brandsUrl,null, {
            response ->

            for (jsonObject in 0.until(response.length())){

                brandsList.add(response.getJSONObject(jsonObject).getString("brand"))

            }


            var brandsListAdapter = ArrayAdapter(this@HomeScreen,R.layout.brand_item_text_view, brandsList)
            brandsListView.adapter = brandsListAdapter




        }, { error ->


            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()




        })

        requestQ.add(jsonAR)

        brandsListView.setOnItemClickListener { adapterView, view, i, l ->

            val  tappedBrand = brandsList.get(i)
            val intent = Intent(this@HomeScreen , FetchEproductsActivity::class.java )

            intent.putExtra("BRAND" , tappedBrand)
            startActivity(intent)

        }




    }
}