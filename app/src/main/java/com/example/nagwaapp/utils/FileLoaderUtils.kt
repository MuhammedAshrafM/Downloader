package com.example.nagwaapp.utils

import android.content.Context
import com.krishna.fileloader.FileLoader
import com.krishna.fileloader.listener.FileRequestListener
import com.krishna.fileloader.pojo.FileResponse
import com.krishna.fileloader.request.FileLoadRequest
import java.io.File

class FileLoaderUtils() {

    companion object {
        fun loadFileByURL(
            context: Context, fileUri: String,
            onFileLoaded: (Pair<File?, Throwable?>) -> Unit
        ) {
            FileLoader.with(context)
                .load(fileUri, true)
                .fromDirectory("PdfFile", FileLoader.DIR_CACHE)
                .asFile(object : FileRequestListener<File?> {
                    override fun onLoad(request: FileLoadRequest, response: FileResponse<File?>) {
                        val loadedFile: File? = response.getBody()
                        onFileLoaded(loadedFile to null)
                    }

                    override fun onError(request: FileLoadRequest, t: Throwable) {
                        onFileLoaded(null to t)
                    }
                })
        }
    }
}