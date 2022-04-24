package com.example.nagwaapp.ui

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nagwaapp.R
import com.example.nagwaapp.adapter.AttachmentAdapter
import com.example.nagwaapp.databinding.ActivityMainBinding
import com.example.nagwaapp.domain.model.Attachment
import com.example.nagwaapp.ui.model.DownloadStatus
import com.example.nagwaapp.utils.DownloadManagerUtils
import com.example.nagwaapp.utils.DownloadManagerUtils.Companion.createFolderAndFile
import com.example.nagwaapp.utils.LoadingProgressBarDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel: MainViewModel by viewModels()

    private lateinit var attachmentAdapter: AttachmentAdapter

    @Inject
    lateinit var loadingProgressBarDialog: LoadingProgressBarDialog

    private var progress: ProgressDialog? = null

    var attachments = ArrayList<Attachment>()

    private var attachmentDownloadedId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAttachmentAdapter()
        setupAttachmentRecyclerView()

        viewModel.getAttachments()

        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        viewModel.attachments.observe(this) {
            attachments.addAll(it)
            setAttachments()
        }

        viewModel.downloadProgress.observe(this) {
            if (it.first) {
                progress?.setProgress(it.second)
            } else {
                //Download finished
                if (it.second == 100) {
                    progress?.setProgress(it.second)

                    //Mark the item that was downloaded
                    attachments.find { it.id == attachmentDownloadedId }?.status =
                        DownloadStatus.DONE
                }else{
                    //Mark the item that was not downloaded
                    attachments.find { it.id == attachmentDownloadedId }?.status =
                        DownloadStatus.INITIAL
                    progress?.dismiss()
                }
                //Refresh the list
                setAttachments()
            }
        }
    }

    private fun setupAttachmentAdapter() {
        attachmentAdapter =
            AttachmentAdapter(this, onFileLoading = { displayLoading(it) }) { attachment ->

                //Mark the item being downloaded
                attachments.find { it.id == attachment.id }?.status = DownloadStatus.LOADING
                //Refresh the list
                setAttachments()

                downloadFile(attachment)
            }
    }

    private fun setupAttachmentRecyclerView() = binding.rvAttachments.apply {
        adapter = attachmentAdapter
        layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAttachments() {
        attachmentAdapter.submitList(attachments)
        attachmentAdapter.notifyDataSetChanged()
    }

    private fun displayLoading(show: Boolean) {
        if (show)
            loadingProgressBarDialog.show()
        else
            loadingProgressBarDialog.dismiss()
    }

    private fun downloadFile(
        attachment: Attachment
    ) {
        attachmentDownloadedId = attachment.id

        val (folder, fileName) = createFolderAndFile()
        val fileExtension = attachment.url.substringAfterLast(".")
        val url = attachment.url

        val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManagerUtils.getDownloadManagerRequest(
            this,
            "$fileName.$fileExtension",
            folder,
            url
        )
        val downloadId = downloadManager.enqueue(request)

        showProgress(attachment.name, fileExtension)
        viewModel.downloadFile(downloadManager, downloadId)
    }


    private fun initProgress() {
        if (progress == null) {
            progress = ProgressDialog(this)
            progress?.setTitle(getString(R.string.downloading))
            progress?.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            progress?.setIndeterminate(false)
        }
    }

    private fun showProgress(
        fileName: String,
        fileExtension: String
    ) {
        initProgress()
        progress?.setMessage("$fileName.$fileExtension")
        progress?.setProgress(0)
        progress?.show()
    }
}