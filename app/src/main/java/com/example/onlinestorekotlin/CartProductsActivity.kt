package com.example.onlinestorekotlin


import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import android.view.WindowManager

import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response

import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cart_products.*
import kotlinx.android.synthetic.main.activity_home_screen.*

class CartProductsActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.cart_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.continueShoppingItem) {

            var intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        } else if (item?.itemId == R.id.declineOrderItem) {

            val deleteUrl = "https://reggaerencontre.com/decline_order.php?email=${Person.email}"
            val requestQ = Volley.newRequestQueue(this@CartProductsActivity)
            val stringRequest = StringRequest(Request.Method.GET, deleteUrl, {
                response ->


                var intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)

            }, {
                error ->



            })

            requestQ.add(stringRequest)
        }else if (item?.itemId == R.id.verifyOrderItem) {


            var verifyOrderUrl = "https://reggaerencontre.com/verify_order.php?email=${Person.email}"
            var requestQ = Volley.newRequestQueue(this@CartProductsActivity)
            var stringRequest = StringRequest(Request.Method.GET, verifyOrderUrl, {
                response ->


                val intent = Intent(this@CartProductsActivity, FinalizeShoppingActivity::class.java)
                Toast.makeText(this, response, Toast.LENGTH_LONG).show()
                intent.putExtra("LATEST_INVOICE_NUMBER", response)
                startActivity(intent)


            }, { error ->  })



            requestQ.add(stringRequest)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_products)






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