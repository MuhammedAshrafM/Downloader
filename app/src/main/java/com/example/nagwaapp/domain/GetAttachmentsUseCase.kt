package com.example.nagwaapp.domain

import com.example.nagwaapp.data.IAttachmentsDataSource

class GetAttachmentsUseCase constructor(private val attachmentsDataSource: IAttachmentsDataSource) {

    fun invoke() = attachmentsDataSource.getAttachments()

}