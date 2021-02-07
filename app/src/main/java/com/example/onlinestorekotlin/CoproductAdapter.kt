package com.example.onlinestorekotlin



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cart_row.view.*



class CoproductAdapter (var context: Context, var arrayList: ArrayList<Contemporary>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val cardView = LayoutInflater.from(context).inflate(R.layout.cart_row,parent,false)

        return ProductViewHolder(cardView)



    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        (holder as CoproductAdapter.ProductViewHolder).initializeRowUIComponents(arrayList[position].id,
                arrayList[position].name,
                arrayList[position].price,
                arrayList[position].email,
                arrayList[position].amount,

        )

    }

    override fun getItemCount(): Int {

        return arrayList.size

    }

    inner class ProductViewHolder(pView: View) : RecyclerView.ViewHolder(pView){

        fun initializeRowUIComponents(id: Int ,name: String, price:Int,email:String,amount:Int){

            itemView.txtId_cart_temporary.text = id.toString()
            itemView.txtName_cart_temporary.text = name
            itemView.txtPrice_cart_temporary.text = price.toString()
            itemView.txEmail_cart_temporary.text = email
            itemView.txtAmount_cart_temporary.text = amount.toString()
           // val picaURL = "https://reggaerencontre.com/"
            //picURL = picURL.replace("", "%20")

           // Picasso.with(context).load(picaURL +picName).into(itemView.imgProduct_cart_temporary)




        }


    }
}