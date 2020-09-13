package com.example.luckytrip.util

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.example.luckytrip.ui.base.App
import com.example.luckytrip.util.AppConstants.BASE_URL
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

fun View?.setViewVisibility(isVisible: Boolean) {
    this?.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    if (App.applicationContext() != null)
        Toast.makeText(App.applicationContext(), message, duration).show()
}

fun parseResponse(response: String, java: Any): Any? {
    return if (isJSONValid(response)) {
        val jsonObject = JSONObject(response)
        val jsonString = jsonObject.toString()
        Gson().fromJson(jsonString, java::class.java)
    } else
        false
}

private fun isJSONValid(test: String?): Boolean {
    try {
        JSONObject(test.toString())
    } catch (ex: JSONException) {
        try {
            JSONArray(test.toString())
        } catch (ex1: JSONException) {
            return false
        }
    }
    return true
}

fun handleUrlQueryParameters(
    args: MutableMap<String, String>
): String {

    for (arg in args) {

        return BASE_URL.plus("?${arg.key}=${arg.value}")

    }

    return ""

}

fun setItemSelected(view: ConstraintLayout){

}

fun setItemNotSelected(view: ConstraintLayout){

}