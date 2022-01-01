package com.example.filehandling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Environment.MEDIA_MOUNTED_READ_ONLY
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.*

class MainActivity : AppCompatActivity() {

    private val filepath = "MyFileStorage"
    internal var myExternalFile: File?=null

    private val isExternalStorageReadOnly: Boolean get() {
        val extStorageState = Environment.getExternalStorageState()
        return MEDIA_MOUNTED_READ_ONLY == extStorageState
    }

    private val isExternalStorageAvailable: Boolean get() {
        val extStorageState = Environment.getExternalStorageState()
        //print(extStorageState)
        Log.d("ddd", extStorageState)
        return Environment.MEDIA_MOUNTED == extStorageState
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fileName = findViewById<EditText>(R.id.filenametxt)
        val displaydata = findViewById<EditText>(R.id.displaydata)
        val writeBtn = findViewById<Button>(R.id.writeBtn) as Button
        val readData = findViewById<Button>(R.id.readData)

        writeBtn.setOnClickListener {
            myExternalFile = File(getExternalFilesDir(filepath), fileName.text.toString())
            /*if (myExternalFile!!.exists()){
                val content:String = displaydata.text.toString()
                myExternalFile!!.appendText(content)
            }*/
            try {
                val fileOutPutStream = FileOutputStream(myExternalFile,true)
                fileOutPutStream.write(displaydata.text.toString().toByteArray())
                fileOutPutStream.close()
            }catch (e:IOException){
                e.printStackTrace()
            }
            Toast.makeText(applicationContext,"File created and data saved", Toast.LENGTH_SHORT).show()
        }

        readData.setOnClickListener {
            myExternalFile = File(getExternalFilesDir(filepath), fileName.text.toString())

            val filename = fileName.text.toString()
            myExternalFile = File(getExternalFilesDir(filepath),filename)
            if (filename.toString().trim() != ""){
                var fileInputStream =FileInputStream(myExternalFile)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({ text = bufferedReader.readLine(); text }() != null) {
                    stringBuilder.append(text)
                }
                fileInputStream.close()
                // display data on  EditText
                Toast.makeText(applicationContext,stringBuilder.toString(),Toast.LENGTH_SHORT).show()
                //displaydata.setText(stringBuilder.toString())
            }
            if (!isExternalStorageAvailable || isExternalStorageReadOnly) {
                writeBtn.isEnabled = false
            }
        }


    }
}