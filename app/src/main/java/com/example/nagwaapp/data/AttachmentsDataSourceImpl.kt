package com.example.nagwaapp.data

import com.example.nagwaapp.domain.model.Attachment
import com.example.nagwaapp.ui.model.AttachmentType

class AttachmentsDataSourceImpl : IAttachmentsDataSource {

    private val attachmentList = ArrayList<Attachment>()

    init {
        loadAttachments()
    }

    //fake responses
    private fun loadAttachments() {
        attachmentList.add(
            Attachment(
                1,
                "Video 1",
                AttachmentType.VIDEO.name,
                "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
            )
        )
        attachmentList.add(
            Attachment(
                2,
                "Video 2",
                AttachmentType.VIDEO.name,
                "https://bestvpn.org/html5demos/assets/dizzy.mp4"
            )
        )
        attachmentList.add(
            Attachment(
                3,
                "PDF 3",
                AttachmentType.PDF.name,
                "https://kotlinlang.org/docs/kotlin-reference.pdf"
            )
        )
        attachmentList.add(
            Attachment(
                4,
                "Video 4",
                AttachmentType.VIDEO.name,
                "https://storage.googleapis.com/exoplayer-test-media-1/mp4/frame-counter-one-hour.mp4"
            )
        )
        attachmentList.add(
            Attachment(
                5,
                "PDF 5",
                AttachmentType.PDF.name,
                "https://www.cs.cmu.edu/afs/cs.cmu.edu/user/gchen/www/download/java/LearnJava.pdf"
            )
        )
        attachmentList.add(
            Attachment(
                6,
                "Video 6",
                AttachmentType.VIDEO.name,
                "https://storage.googleapis.com/exoplayer-test-media-1/mp4/android-screens-10s.mp4"
            )
        )
        attachmentList.add(
            Attachment(
                7,
                "Video 7",
                AttachmentType.VIDEO.name,
                "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
            )
        )
        attachmentList.add(
            Attachment(
                8,
                "Video 8",
                AttachmentType.VIDEO.name,
                "https://storage.googleapis.com/exoplayer-test-media-1/mp4/android-screens-25s.mp4"
            )
        )
        attachmentList.add(
            Attachment(
                9,
                "PDF 9",
                AttachmentType.PDF.name,
                "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"
            )
        )
        attachmentList.add(
            Attachment(
                10,
                "PDF 10",
                AttachmentType.PDF.name,
                "https://en.unesco.org/inclusivepolicylab/sites/default/files/dummy-pdf_2.pdf"
            )
        )
        attachmentList.add(
            Attachment(
                11,
                "Video 11",
                AttachmentType.VIDEO.name,
                "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
            )
        )
        attachmentList.add(
            Attachment(
                12,
                "Video 12",
                AttachmentType.VIDEO.name,
                "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
            )
        )
    }

    override fun getAttachments(): List<Attachment> =
        attachmentList
}