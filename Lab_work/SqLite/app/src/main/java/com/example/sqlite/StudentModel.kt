package com.example.sqlite

import java.util.*

data class StudentModel (
    var id: Int = getAutoId(),
    var name: String = "",
    var regno: String = "",
    var session: String = ""
){
    companion object {
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}