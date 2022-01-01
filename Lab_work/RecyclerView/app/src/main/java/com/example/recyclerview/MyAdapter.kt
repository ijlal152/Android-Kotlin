package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.widget.TextView

class MyAdapter(private val productList: List<Any>, mainActivity: MainActivity) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    private lateinit var binding: MyAdapter

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        /*fun bindItems(model: Model){
            itemView.titleTv.text = model.title
            itemView.DescriptionTv.text = model.des
            itemView.image1.setImageResource(model.image)
        }*/
        val itemID = itemView.findViewById<TextView>(R.id.itemid)
        val quantity = itemView.findViewById<TextView>(R.id.regno_tv)

        /*fun bind(quantity: MyCustomeApi){
            //val name ="Product Item :${quantity.q.toString()}"
            //itemID.text = quantity.qua

            //Picasso.get().load(quantity.quantityInfo.flag).into(imageView)
        }*/






    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count :${productList.size} ")
        //holder.bindItems(arrayList[position])

        //holder.itemView.setOnClickListener{

            // get position of selected item
            //val model = arrayList[position]
            // get title and description of selected item with intent
            //val gTitle : String = model.title
            //val gDescription : String = model.des
            // get image with intent, which position is selected
            //val gImageView : Int = model.image

            // create intent

            //val intent = Intent(context, AnotherActivity::class.java)
            //intent.putExtra("iTitle", gTitle)
            //intent.putExtra("iDescription", gDescription)
            //intent.putExtra("iImageView", gImageView)
            // start another activity
            //context.startActivity(intent)
        }

    override fun getItemCount(): Int {
        return productList.size
    }
}
