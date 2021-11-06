package com.example.sqlite

import android.annotation.SuppressLint
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.widget.Button
import com.example.sqlite.StudentAdapter


class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(){

    private var stdList: ArrayList<StudentModel> = ArrayList()
    private var onClickItem: ((StudentModel) -> Unit)? = null
    private var onClickDeleteItem: ((StudentModel) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: ArrayList<StudentModel>){
        this.stdList = items
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (StudentModel) -> Unit){
        this.onClickItem = callback
    }

    fun setOnClickDelete(callback: (StudentModel) -> Unit){
        this.onClickDeleteItem = callback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= StudentViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_layout, parent, false)
            )

    override fun onBindViewHolder(holder: StudentAdapter.StudentViewHolder, position: Int) {
        val std = stdList[position]
        holder.bindView(std)
        holder.itemView.setOnClickListener{ onClickItem?.invoke(std) }
        holder.btndelete.setOnClickListener{ onClickDeleteItem?.invoke(std)}
    }

    override fun getItemCount(): Int {
        return stdList.size
    }

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private var id = view.findViewById<TextView>(R.id.tvId)
        private var name = view.findViewById<TextView>(R.id.tvName)
        private var regno = view.findViewById<TextView>(R.id.tvRenNo)
        private var session = view.findViewById<TextView>(R.id.tvSession)
        var btndelete = view.findViewById<Button>(R.id.btndelete)

        fun bindView(std: StudentModel){
            id.text = std.id.toString()
            name.text = std.name
            regno.text = std.regno
            session.text = std.session
        }

    }

}