package com.example.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SQLiteHelper(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "student.db"
        private const val TBL_STUDENT = "tbl_student"
        private const val ID = "id"
        private const val NAME = "name"
        private const val REGNO = "regno"
        private const val SESSION = "session"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblStudent = (" CREATE TABLE " + TBL_STUDENT + "("
                + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT ," + REGNO + " TEXT,"
                + SESSION + " TEXT " + ")")

        db?.execSQL(createTblStudent)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_STUDENT")
        onCreate(db)
    }

    // insert student
    fun insertStudent(std: StudentModel):Long{
        val db = this.writableDatabase

        val contentValue = ContentValues()
        contentValue.put(ID, std.id)
        contentValue.put(NAME, std.name)
        contentValue.put(REGNO, std.regno)
        contentValue.put(SESSION, std.session)

        val success = db.insert(TBL_STUDENT, null, contentValue)
        db.close()
        return success
    }
    // get student data
    @SuppressLint("Recycle", "Range")
    fun getAllStudent(): ArrayList<StudentModel>{
        val studList: ArrayList<StudentModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_STUDENT"
        val db = this.readableDatabase

        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var regno: String
        var session: String


        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                regno = cursor.getString(cursor.getColumnIndex("regno"))
                session = cursor.getString(cursor.getColumnIndex("session"))

                val std = StudentModel(id = id, name = name, regno = regno, session = session)
                studList.add(std)
            }while (cursor.moveToNext())
        }
        return studList

    }

    fun updateStudent(std: StudentModel): Int{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(REGNO, std.regno)
        contentValues.put(SESSION, std.session)

        val success = db.update(TBL_STUDENT, contentValues, "id=" +std.id, null)
        db.close()
        return success
    }

    fun delteStudentById(id: Int): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, id)
        val success = db.delete(TBL_STUDENT, "id= $id", null)
        db.close()
        return success
    }






}