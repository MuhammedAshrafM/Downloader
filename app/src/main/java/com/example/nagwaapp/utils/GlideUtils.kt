package com.example.nagwaapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nagwaapp.R

class GlideUtils {
    companion object {

        fun ImageView.viewAttachment(context: Context, attachmentUrl: String?) {
            Glide.with(context)
                .setDefaultRequestOptions(RequestOptions())
                .load(attachmentUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .timeout(30000)
                .into(this)

        }

        fun ImageView.viewAttachment(context: Context, bitmap: Bitmap?) {
            Glide.with(context)
                .setDefaultRequestOptions(RequestOptions())
                .load(bitmap)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .timeout(30000)
                .into(this)

        }

    }
}