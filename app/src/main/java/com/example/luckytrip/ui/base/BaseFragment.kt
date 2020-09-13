package com.example.luckytrip.ui.base

import android.annotation.SuppressLint
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.luckytrip.util.LoadingDialog
import com.example.luckytrip.util.OnBackPressed

@SuppressLint("Registered")
abstract class BaseFragment : Fragment(), OnBackPressed {

    private val loadingDialog: LoadingDialog by lazy {
        LoadingDialog()
    }

    override fun onBackPressed() {

    }

    protected fun showLoadingDialog(message: String? = "", isCancelable: Boolean = false) {

        try {
            hideLoadingDialog {
                loadingDialog.isCancelable = isCancelable
                loadingDialog.setMessage(message ?: "").show(childFragmentManager, "")
            }
        } catch (e: IllegalStateException) {
            Log.e("TAGTAG", "Loading dialog cant be shown fragment is not attached")
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