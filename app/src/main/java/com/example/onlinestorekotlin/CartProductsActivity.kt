package com.example.onlinestorekotlin

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.onlinestorekotlin.Person.Companion.email
import kotlinx.android.synthetic.main.activity_cart_products.*
import kotlinx.android.synthetic.main.activity_fetch_eproducts.*
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.sign_up_layout.*

class CartProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_products)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);



        val typeface: Typeface = Typeface.createFromAsset(assets,"Tabby - Display.ttf")
        title_temporary_activity.typeface = typeface


        val cartProductURL =  "https://reggaerencontre.com/fetch_eproducts.php/fetch_temporary_order.php?email=$email"
        val cartProductList = ArrayList<Contemporary>()
        val requestQ: RequestQueue = Volley.newRequestQueue(this)
        val jsonAR = JsonArrayRequest(Request.Method.GET, cartProductURL ,null, {
                response ->

            for (productJOIndex in 0.until(response.length())){


                cartProductList.add(Contemporary(response.getJSONObject(productJOIndex).getInt("id") ,
                        response.getJSONObject(productJOIndex).getString("name"),

                        response.getJSONObject(productJOIndex).getInt("price"),
                        response.getJSONObject(productJOIndex).getString("email"),
                        response.getJSONObject(productJOIndex).getInt("amount")))

            }

            val cartAdapter = CoproductAdapter(this@CartProductsActivity, cartProductList)
            cartProductListView.layoutManager = LinearLayoutManager(this@CartProductsActivity)
            cartProductListView.adapter = cartAdapter


        }, { error ->


            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()




        })

        requestQ.add(jsonAR)



    }
}