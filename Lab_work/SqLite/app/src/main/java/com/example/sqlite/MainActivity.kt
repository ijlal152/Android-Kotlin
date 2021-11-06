package com.example.sqlite

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_items_layout.*


class MainActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var regno: EditText
    private lateinit var session: EditText

    private lateinit var submitbtn: Button
    private lateinit var viewbnt: Button
    private lateinit var updatebtn: Button
    private lateinit var btndelete:Button

    private lateinit var sqliteHelper:SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: StudentAdapter? = null
    private var std:StudentModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initRecyclerView()
        sqliteHelper = SQLiteHelper(this)

        submitbtn=findViewById(R.id.submitbtn)
        viewbnt = findViewById(R.id.viewbtn)
        //btndelete = findViewById(R.id.btndelete)

        submitbtn.setOnClickListener{addStudent()}
        viewbnt.setOnClickListener{getStudents()}
        updatebtn.setOnClickListener{updateStudent()}
        //btndelete.setOnClickListener{deleteStudent()}
        adapter?.setOnClickItem {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            // now we need to update record
            name.setText(it.name)
            regno.setText(it.regno)
            session.setText(it.session)
            std = it
        }

        adapter?.setOnClickDelete{
            deleteStudent(it.id)
        }


    }

    private fun getStudents() {
        val stdList = sqliteHelper.getAllStudent()
        Log.e("pppp", "${stdList.size}")

        // Now we need to display data in RecyclerView
        adapter?.addItems(stdList)

    }

    private fun addStudent(){
        val name = name.text.toString()
        val regno = regno.text.toString()
        val session = session.text.toString()

        if(name.isEmpty() || regno.isEmpty() || session.isEmpty()){
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
        }else{
            val std = StudentModel(name = name, regno = regno, session = session)
            val status = sqliteHelper.insertStudent(std)
            // check insert sussess or not
            if(status > -1){
                Toast.makeText(this, "Student Added...", Toast.LENGTH_SHORT).show()
                clearEditText()
                getStudents()
            }else{
                Toast.makeText(this, "Could not Add record...", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun updateStudent(){
        val name = name.text.toString()
        val regno = regno.text.toString()
        val session = session.text.toString()

        if(name == std?.name && regno == std?.regno && session == std?.session){
            Toast.makeText(this, "Record not changed...", Toast.LENGTH_SHORT).show()
            return
        }
        if(std == null) return
        val std = StudentModel(id = std!!.id, name = name, regno = regno, session = session)
        val status = sqliteHelper.updateStudent(std)
        if(status > -1){
            clearEditText()
            getStudents()
        }else{
            Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteStudent(id: Int){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete record ?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes"){dialog, _->
            sqliteHelper.delteStudentById(id)
            getStudents()
            dialog.dismiss()
        }
        builder.setNegativeButton("No"){dialog, _->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

    private fun clearEditText(){
        name.setText("")
        regno.setText("")
        session.setText("")
    }


    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter()
        recyclerView.adapter = adapter
    }


    private fun initView(){
        name = findViewById(R.id.name)
        regno = findViewById(R.id.regno)
        session = findViewById(R.id.session)
        submitbtn = findViewById(R.id.submitbtn)
        viewbnt = findViewById(R.id.viewbtn)
        updatebtn = findViewById(R.id.updatebtn)
        recyclerView = findViewById(R.id.recyclerView)


    }

}