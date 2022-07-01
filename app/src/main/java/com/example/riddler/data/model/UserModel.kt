package com.example.riddler.data.model

import java.io.Serializable

class UserProfile ()
    : Serializable{
    var email: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var profilePic: Int = 1

    override fun toString(): String {
        return "$firstName $lastName"
    }
}