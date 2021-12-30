package com.example.android_semester_project

import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import kotlinx.android.synthetic.main.row.view.*
import android.content.Intent


class MyAdapter1(val arrayList: ArrayList<Model1>,  val context: Context) :
    RecyclerView.Adapter<MyAdapter1.ViewHolder>(){


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(model: Model1){
            itemView.idTv.text = model.id.toString()
            itemView.titleTv.text = model.title
            //itemView.DescriptionTv.text = model.des
            //itemView.image1.setImageResource(model.image)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter1.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyAdapter1.ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

        holder.itemView.setOnClickListener{

            // get position of selected item
            val model = arrayList[position]
            // get title and description of selected item with intent
            val gid : Int = model.id
            val gTitle : String = model.title
            //val gDescription : String = model.des
            // get image with intent, which position is selected
            //val gImageView : Int = model.image

            // create intent
            if (gid == 1){
                val intent = Intent(context, TimePickerActivity::class.java)
                context.startActivity(intent)
            }else if (gid == 2){
                val intent = Intent(context, NotificationActivity::class.java)
                context.startActivity(intent)
            }else if (gid == 3){
                val intent = Intent(context, EmailActivity::class.java)
                context.startActivity(intent)
            }else if (gid == 4){
                val intent = Intent(context, ShareReferencesActivity::class.java)
                context.startActivity(intent)
            }else if (gid == 5){
                val intent = Intent(context, AsyncTaskActivity::class.java)
                context.startActivity(intent)
            }else if (gid == 6){
                //val intent = Intent(context, NotificationActivity::class.java)
                //context.startActivity(intent)
            }else if (gid == 7){
                val intent = Intent(context, SendSMSActivity::class.java)
                context.startActivity(intent)
            }else if (gid == 8){
                val intent = Intent(context, LocationActivity::class.java)
                context.startActivity(intent)
            }else if (gid == 9){
                val intent = Intent(context, FileHandlingActivity::class.java)
                context.startActivity(intent)
            }else if (gid == 10){
                val intent = Intent(context, ServiceAndBroadCastActivity::class.java)
                context.startActivity(intent)
            }else if (gid == 11){
                val intent = Intent(context, VollyLibraryActivity::class.java)
                context.startActivity(intent)
            }
            //val intent = Intent(context, AnotherActivity::class.java)
            //intent.putExtra("iTitle", gTitle)
            //intent.putExtra("iDescription", gDescription)
            //intent.putExtra("iImageView", gImageView)
            // start another activity
            //context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}