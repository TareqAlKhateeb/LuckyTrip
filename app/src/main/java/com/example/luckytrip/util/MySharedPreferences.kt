package com.example.luckytrip.util

import android.content.Context
import android.content.SharedPreferences
import com.example.luckytrip.data.models.Room
import com.example.luckytrip.util.AppConstants.MY_ROOM
import com.google.gson.Gson
import java.io.Serializable

object MySharedPreferences {

    private var sharedPreference: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    fun initWith(context: Context) {
        if (sharedPreference == null) sharedPreference = context.getSharedPreferences(
            "PREFERENCE_NAME",
            Context.MODE_PRIVATE
        )
    }

    fun setSerializableValue(key: String, value: Serializable) {
        editor = sharedPreference?.edit()
        val gSon = Gson()
        val json = gSon.toJson(value)
        editor?.putString(key, json)
        editor?.apply()
    }

    fun retrieveRoomObject(context: Context): Room? {
        val gson = Gson()
        return gson.fromJson(getStringValue(MY_ROOM,""), Room::class.java)
    }

    private fun getStringValue(key: String, defaultValue: String): String? {
        return sharedPreference?.getString(key, defaultValue)
    }

}