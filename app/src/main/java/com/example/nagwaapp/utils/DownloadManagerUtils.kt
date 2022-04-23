package com.example.nagwaapp.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import java.io.File
import java.util.*

class DownloadManagerUtils {

    companion object {

        fun createFolderAndFile(): Pair<String, String> {
            val file: File = Environment.getDownloadCacheDirectory()
            val folder: String = file.getAbsolutePath().toString() + "/Downloader"
            val fileName = "Downloader_${Calendar.getInstance().timeInMillis}"

            return folder to fileName
        }

        fun getDownloadManagerRequest(
            context: Context,
            fileName: String,
            destinationDirectory: String?,
            url: String?
        ): DownloadManager.Request {

            val uri: Uri = Uri.parse(url)
            val request = DownloadManager.Request(uri)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalFilesDir(
                context,
                destinationDirectory,
                fileName
            )

            return request
        }
    }
}