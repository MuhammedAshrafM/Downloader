package com.example.nagwaapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nagwaapp.databinding.RowItemFileBinding
import com.example.nagwaapp.domain.model.Attachment
import com.example.nagwaapp.ui.model.AttachmentType
import com.example.nagwaapp.ui.model.DownloadStatus
import com.example.nagwaapp.utils.FileLoaderUtils
import com.example.nagwaapp.utils.GlideUtils.Companion.viewAttachment
import com.example.nagwaapp.utils.PdfUtils.Companion.viewPdfOnImageView

class AttachmentAdapter(
    context: Context,
    private val onFileLoading: (Boolean) -> Unit,
    private val onDownloadClick: (Attachment) -> Unit
) : ListAdapter<Attachment, AttachmentAdapter.AttachmentViewHolder>(Companion) {

    private val _context: Context

    inner class AttachmentViewHolder(val binding: RowItemFileBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<Attachment>() {
        override fun areItemsTheSame(oldItem: Attachment, newItem: Attachment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Attachment, newItem: Attachment): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    init {
        _context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttachmentViewHolder {
        return AttachmentViewHolder(
            RowItemFileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AttachmentViewHolder, position: Int) {
        val attachment = currentList[position]

        holder.binding.apply {

            when (attachment.type) {
                AttachmentType.VIDEO.name ->
                    fileImage.viewAttachment(_context, attachment.url)
                AttachmentType.PDF.name ->
                    loadPdfFile(this, attachment.url)
            }
            nameText.text = attachment.name

            /*
            Check attachment status to mark the item that was downloaded
            and the item being downloaded in the UI
            */

            val (fileLoading, doneDownload) = when (attachment.status) {
                DownloadStatus.INITIAL -> false to false
                DownloadStatus.LOADING -> true to false
                DownloadStatus.DONE -> false to true
            }

            this.fileLoading = fileLoading
            this.doneDownload = doneDownload

            downloadImage.setOnClickListener {
                onDownloadClick(attachment)
            }
        }
    }

    private fun loadPdfFile(itemBinding: RowItemFileBinding, attachmentUrl: String) {
        try {
            onFileLoading(true)
            FileLoaderUtils.loadFileByURL(_context, attachmentUrl) {
                itemBinding.fileImage.viewPdfOnImageView(_context, it.first)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            onFileLoading(false)
        }

    }
}