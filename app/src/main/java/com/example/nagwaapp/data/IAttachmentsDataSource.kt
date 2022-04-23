package com.example.nagwaapp.data

import com.example.nagwaapp.domain.model.Attachment

interface IAttachmentsDataSource {

    fun getAttachments(): List<Attachment>

}