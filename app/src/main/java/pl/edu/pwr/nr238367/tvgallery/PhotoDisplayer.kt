package pl.edu.pwr.nr238367.tvgallery

import android.view.View


interface PhotoDisplayer {
    fun updateMainPhoto(view: View)
    fun loadMoreData()
}