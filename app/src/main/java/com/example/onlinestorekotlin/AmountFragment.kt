package com.example.onlinestorekotlin


import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*


class AmountFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var fragmentView = inflater.inflate(R.layout.fragment_amount, container, false)
        var edtEnterAmountProduct = fragmentView.findViewById<EditText>(R.id.edtEnterAmount)
        var btnAddCart = fragmentView.findViewById<ImageButton>(R.id.btnAddToCart)
        btnAddCart.setOnClickListener {

            var addbuttonUrl ="https://reggaerencontre.com/insert_temporary_order.php?email=${Person.email}" +
                    "&product_id=${Person.addToCartProductID}&amount=${edtEnterAmountProduct.text}"
            var requestQ = Volley.newRequestQueue(activity)
            var stringRequest = StringRequest(Request.Method.GET, addbuttonUrl, {
                    response ->

                var intent = Intent(activity,CartProductsActivity::class.java)
                startActivity(intent)



            }, { error->


                val dialogBuilder = AlertDialog.Builder(activity)
                dialogBuilder.setTitle("alert")
                dialogBuilder.setMessage("Something wrong happens")
                dialogBuilder.create().show()




            })


            requestQ.add(stringRequest)





        }
        return fragmentView
    }



}