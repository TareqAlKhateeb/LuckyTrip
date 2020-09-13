package com.example.luckytrip.ui.base

import android.annotation.SuppressLint
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.luckytrip.util.LoadingDialog

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {

    private val loadingDialog: LoadingDialog by lazy {
        LoadingDialog()
    }

    protected fun showLoadingDialog(message: String? = "", isCancelable: Boolean = false) {
        hideLoadingDialog {
            loadingDialog.isCancelable = isCancelable
            loadingDialog.setMessage(message ?: "").show(supportFragmentManager, "")
        }
    }

    fun hideLoadingDialog(
        withDelay: Boolean = false, onDismissListener: (() -> Unit)? = null
    ) {
        if (loadingDialog.isVisible || loadingDialog.isAdded) {
            if (withDelay) {
                Handler().postDelayed({
                    loadingDialog.dismissAllowingStateLoss()
                    onDismissListener?.invoke()
                }, 400)
            } else {
                loadingDialog.dismissAllowingStateLoss()
                onDismissListener?.invoke()
            }
        } else {
            onDismissListener?.invoke()
        }
    }

}