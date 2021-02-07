package com.example.onlinestorekotlin


import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

import android.widget.ArrayAdapter
import com.android.volley.Request

import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cart_products.*
import kotlinx.android.synthetic.main.activity_home_screen.*

class CartProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_products)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        val typeface: Typeface = Typeface.createFromAsset(assets,"Tabby - Display.ttf")
        title_cart_tv.typeface = typeface







        var cartProductsUrl = "https://reggaerencontre.com/fetch_temporary_order.php?email=${Person.email}"
        var cartProductsList = ArrayList<String>()
        var requestQ = Volley.newRequestQueue(this@CartProductsActivity)
        var jsonAR = JsonArrayRequest(Request.Method.GET, cartProductsUrl, null, { response ->


            for (joIndex in 0.until(response.length())) { // id, name, price, email, amount

                cartProductsList.add(
                    " Id: ${response.getJSONObject(joIndex).getInt("id")}" +
                            " \n Name: ${response.getJSONObject(joIndex).getString("name")}" +
                            " \n Price: ${response.getJSONObject(joIndex).getInt("price")} €" +
                            " \n Email: ${response.getJSONObject(joIndex).getString("email")}" +
                            " \n Amount:  ${response.getJSONObject(joIndex).getInt("amount")}"
                )

            }

            var cartProductsAdapter = ArrayAdapter(
                this@CartProductsActivity,
                android.R.layout.simple_list_item_1,
                cartProductsList
            )
            cartProductsListView.adapter = cartProductsAdapter

        }, { error ->


        })


        requestQ.add(jsonAR)
    }




}