package com.archcoders.starswarsproject.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

object Constants {

    val ts = Timestamp(System.currentTimeMillis()).time.toString()
    const val API_KEY = "5b95fd88ac3f08692354e62a28eb6779"
    const val PRIVATE_KEY = "89261cf967771a419174c63a9e6a7c019ec91eb4"
    const val NAME_DATA_BASE = "character-db"
    const val CHARACTER_ID = "characterID"
    fun hash(): String{
        val input = "$ts$PRIVATE_KEY$API_KEY"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32,'0')
    }
}