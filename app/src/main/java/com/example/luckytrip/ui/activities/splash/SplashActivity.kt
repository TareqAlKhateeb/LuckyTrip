package com.example.luckytrip.ui.activities.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.luckytrip.R
import com.example.luckytrip.data.models.Rooms
import com.example.luckytrip.data.network.IApiCaller
import com.example.luckytrip.ui.activities.host.HostActivity
import com.example.luckytrip.util.AppConstants.LANGUAGE_CODE
import com.example.luckytrip.util.AppConstants.SHORT_PROGRESS_DURATION
import com.example.luckytrip.util.mSQLite.SQLiteManager
import java.util.*

class SplashActivity : AppCompatActivity() {

    private val _mutableLiveDataResponse = MutableLiveData<Any?>()
    private val mutableLiveDataResponse
        get() = _mutableLiveDataResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        setContentView(R.layout.activity_splash)

        responseHandler()

        handleSQLiteDB()

    }

    private fun callRoomsApi() {

        val bodyParams: MutableMap<String, String> = mutableMapOf()

        bodyParams[LANGUAGE_CODE] = Locale.getDefault().language

        IApiCaller.callApiGet(_mutableLiveDataResponse,Rooms(),bodyParams)

    }

    private fun responseHandler() {

        mutableLiveDataResponse.observe(this, {
            it?.let {
                when (it) {
                    is Rooms -> {

                        SQLiteManager.insertIntoSQLiteDB(it)

                        navigateToHost()

                    }
                }
            }
        })

    }

    private fun handleSQLiteDB() {

        if (SQLiteManager.getSQLiteData() == null) {

            callRoomsApi()

        } else {

            navigateToHost()

        }

    }

    private fun navigateToHost() {

        Handler().postDelayed(
            {

                val intent = Intent(this, HostActivity::class.java)
                startActivity(intent)
                this.finish()

            }, SHORT_PROGRESS_DURATION
        )

    }
}