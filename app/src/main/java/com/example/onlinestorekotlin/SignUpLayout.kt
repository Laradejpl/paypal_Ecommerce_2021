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
import kotlinx.android.synthetic.main.sign_up_layout.*

class SignUpLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_layout)

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);




        sign_up_layout_btn_TextLogin.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }



        sign_up_layout_btnSubmit.setOnClickListener {


            if (sign_up_layout_edtPassword.text.toString().equals(sign_up_layout_edtConfirmPass.text.toString())){


                //registration

                var  signUpUrl = "https://reggaerencontre.com/join_new_user.php?email=" +
                        sign_up_layout_edtEmail.text.toString() +
                        "&username=" +
                        sign_up_layout_edtUsername.text.toString() + "&pass=" + sign_up_layout_edtPassword.text.toString()

                val requestQ: RequestQueue = Volley.newRequestQueue(this)
                val stringRequest = StringRequest(Request.Method.GET,signUpUrl, {


                    response ->

                    // the message must be the same as the php file

                    if (response.equals("a user with the same address already exists ")){


                        val dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setTitle("alert")
                        dialogBuilder.setMessage(response)
                        dialogBuilder.create().show()

                    }else{

                        Person.email = sign_up_layout_edtEmail.text.toString()


                        Toast.makeText(this@SignUpLayout, response, Toast.LENGTH_SHORT).show()
                        val homeIntent = Intent(this, HomeScreen::class.java)
                        startActivity(homeIntent)







                    }



                }, { error->


                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("alert")
                    dialogBuilder.setMessage(error.message )
                    dialogBuilder.create().show()
                })

                requestQ.add(stringRequest)



            }else{

                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("alert")
                dialogBuilder.setMessage("Password Mismatch")
                dialogBuilder.create().show()





            }




        }

        sign_up_layout_btn_TextLogin.setOnClickListener {


            finish()
        }

    }

}