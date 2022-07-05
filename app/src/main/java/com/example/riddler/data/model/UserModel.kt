package com.example.riddler.data.model

import com.example.riddler.R
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

class Avatars {
    companion object{
        val avatarsList = arrayListOf<Int>(
            R.drawable.av_01,
            R.drawable.av_02,
            R.drawable.av_03,
            R.drawable.av_04,
            R.drawable.av_05,
            R.drawable.av_06,
            R.drawable.av_07,
            R.drawable.av_08,
            R.drawable.av_09,
            R.drawable.av_10,
            R.drawable.av_11,
            R.drawable.av_12,
            R.drawable.av_13,
            R.drawable.av_14,
            R.drawable.av_15,
            R.drawable.av_16,
            R.drawable.av_17,
            R.drawable.av_18,
            R.drawable.av_19,
            R.drawable.av_20,
            R.drawable.av_21,
            R.drawable.av_22,
            R.drawable.av_23
        )
    }
}