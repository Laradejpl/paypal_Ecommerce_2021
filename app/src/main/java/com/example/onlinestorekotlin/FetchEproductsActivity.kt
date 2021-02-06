package com.example.onlinestorekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_fetch_eproducts.*

class FetchEproductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_eproducts)

        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        val selectedBrand = intent.getStringExtra("BRAND")
        title_brand_text_view.text = "Products of $selectedBrand"

        var productsList = ArrayList<EProduct>()



        var productsUrl = "https://reggaerencontre.com/fetch_eproducts.php?brand=$selectedBrand"
        val requestQ: RequestQueue = Volley.newRequestQueue(this)

        var jsonAR = JsonArrayRequest(Request.Method.GET, productsUrl,null, Response.Listener {
            response ->

           for (productJOIndex in 0.until(response.length())){


               productsList.add(EProduct(response.getJSONObject(productJOIndex).getInt("id") , response.getJSONObject(productJOIndex).getString("name"),
                   response.getJSONObject(productJOIndex).getInt("price"),response.getJSONObject(productJOIndex).getString("picture") ))

           }


             val pAdapter = EProductAdapter(this@FetchEproductsActivity, productsList)
            productsRV.layoutManager = LinearLayoutManager(this@FetchEproductsActivity)
            productsRV.adapter = pAdapter


        }, Response.ErrorListener { error ->


            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()







        })

        requestQ.add(jsonAR)

    }
}