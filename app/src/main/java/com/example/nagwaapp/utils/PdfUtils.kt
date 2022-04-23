package com.example.nagwaapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import android.widget.ImageView
import com.example.nagwaapp.utils.GlideUtils.Companion.viewAttachment
import java.io.File

class PdfUtils {
    companion object {
        fun ImageView.viewPdfOnImageView(context: Context, file: File?) {
            // Create the page renderer for the PDF document.
            val fileDescriptor =
                ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
            val pdfRenderer = PdfRenderer(fileDescriptor)

            // Open the page to be rendered.
            val page = pdfRenderer.openPage(0)

            // Render the page to the bitmap.
            val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)

            // Use the rendered bitmap.
            this.viewAttachment(context, bitmap)

            // Close the page when you are done with it.
            page.close()

            // Close the `PdfRenderer` when you are done with it.
            pdfRenderer.close()
        }
    }
}