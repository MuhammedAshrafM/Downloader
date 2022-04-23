package com.example.nagwaapp.domain.model

import com.example.nagwaapp.ui.model.DownloadStatus

data class Attachment(
    val id: Int,
    val name: String,
    val type: String,
    val url: String
) {
    var status = DownloadStatus.INITIAL
}