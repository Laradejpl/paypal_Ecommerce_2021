package com.example.onlinestorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);


        tv_subscribe.setOnClickListener{

            val intent = Intent(this@MainActivity, SignUpLayout::class.java)
            startActivity(intent)


        }

        btnLogin.setOnClickListener {

            val loginURL = "https://reggaerencontre.com/login_app_user.php?email=" +
                    activity_main_edtEmail.text.toString() +
                    "&pass=" + activity_main_edtPassword.text.toString()


            val requestQ: RequestQueue = Volley.newRequestQueue(this)

            val stringRequest = StringRequest(Request.Method.GET,loginURL, Response.Listener {


                response ->

                if (response.equals("The user does exist ")){
                    // pour garder une trace de la personne qui s'est logger ou enregistrer.
                    Person.email = activity_main_edtEmail.text.toString()

                    Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
                    val homeIntent = Intent(this, HomeScreen::class.java)
                    startActivity(homeIntent)


                }else{

                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Message")
                    dialogBuilder.setMessage(response)
                    dialogBuilder.create().show()




                }





            },  Response.ErrorListener { error ->


                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Message")
                dialogBuilder.setMessage("error.message")
                dialogBuilder.create().show()
            })

            requestQ.add(stringRequest)




        }




    }
}