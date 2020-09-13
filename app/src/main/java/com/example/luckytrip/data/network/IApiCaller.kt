package com.example.luckytrip.data.network

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.luckytrip.R
import com.example.luckytrip.ui.base.App
import com.example.luckytrip.util.handleUrlQueryParameters
import com.example.luckytrip.util.parseResponse
import com.example.luckytrip.util.showToast
import java.io.Serializable

object IApiCaller {

    private var queue: RequestQueue? = null

    fun initWith(con: Context) {

        queue = Volley.newRequestQueue(con)

    }

    fun callApiGet(
        _mutableLiveDataResponse: MutableLiveData<Any?>?,
        responseType: Serializable,
        args: MutableMap<String, String>
    ) {

        val request = JsonObjectRequest(
            Request.Method.GET,
            handleUrlQueryParameters(args),
            null,
            { response ->

                val responseObject = parseResponse(
                    response.toString(),
                    responseType
                )

                _mutableLiveDataResponse?.value = responseObject

            },
            {

                App.applicationContext()?.getString(R.string.api_call_failed)?.let { it1 ->
                    showToast(
                        it1
                    )
                }

            })

        request.retryPolicy = DefaultRetryPolicy(
            30000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        request.setShouldCache(false)

        queue?.add(request)

    }

}