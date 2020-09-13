package com.example.luckytrip.util

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.luckytrip.R
import com.example.luckytrip.ui.base.BaseDialogFragment

class LoadingDialog  : BaseDialogFragment() {

    private var dialogInterface: DialogInterface? = null
    var message = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_loading, container, false)
    }

    override fun setData() {

    }

    fun setMessage(message: String) = apply {
        this.message = message
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dialogInterface?.dismiss()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        dialogInterface?.cancel()
    }
}