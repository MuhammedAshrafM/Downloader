package com.example.nagwaapp.ui

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nagwaapp.domain.GetAttachmentsUseCase
import com.example.nagwaapp.domain.model.Attachment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val getAttachmentsUseCase: GetAttachmentsUseCase
) : ViewModel() {

    private val _attachments: MutableLiveData<List<Attachment>> =
        MutableLiveData(mutableListOf())

    val attachments: LiveData<List<Attachment>> = _attachments

    private val _downloadProgress: MutableLiveData<Pair<Boolean, Int>> =
        MutableLiveData(false to 0)

    val downloadProgress: LiveData<Pair<Boolean, Int>> = _downloadProgress


    fun getAttachments() {
        _attachments.value = getAttachmentsUseCase.invoke()
    }


    @SuppressLint("Range")
    fun downloadFile(
        downloadManager: DownloadManager,
        downloadId: Long
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            var progress = 0
            var isDownloadFinished = false
            while (!isDownloadFinished) {
                val cursor: Cursor =
                    downloadManager.query(DownloadManager.Query().setFilterById(downloadId))
                if (cursor.moveToFirst()) {
                    val downloadStatus: Int =
                        cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                    when (downloadStatus) {
                        DownloadManager.STATUS_RUNNING -> {
                            val totalBytes: Long =
                                cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                            if (totalBytes > 0) {
                                val downloadedBytes: Long =
                                    cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                                progress = (downloadedBytes * 100 / totalBytes).toInt()
                            }
                        }
                        DownloadManager.STATUS_SUCCESSFUL -> {
                            progress = 100
                            isDownloadFinished = true
                        }
                        DownloadManager.STATUS_PAUSED, DownloadManager.STATUS_PENDING -> {}
                        DownloadManager.STATUS_FAILED -> isDownloadFinished = true
                    }

                    _downloadProgress.postValue(!isDownloadFinished to progress)
                }
            }
        }
    }

}