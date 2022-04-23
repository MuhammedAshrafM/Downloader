package com.example.nagwaapp.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import com.example.nagwaapp.R
import com.example.nagwaapp.databinding.LayoutProgressBarDialogBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadingProgressBarDialog @Inject constructor(context: Context) :
    Dialog(context, R.style.MaterialDialogSheet) {

    private var _binding: LayoutProgressBarDialogBinding? = null

    init {
        init()
    }

    private fun init() {

        setCancelable(false)
        window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        window?.setGravity(Gravity.CENTER)
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setCanceledOnTouchOutside(false)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = layoutInflater.inflate(R.layout.layout_progress_bar_dialog, null)
        _binding = LayoutProgressBarDialogBinding.bind(view)
        setContentView(view)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _binding = null
    }

}