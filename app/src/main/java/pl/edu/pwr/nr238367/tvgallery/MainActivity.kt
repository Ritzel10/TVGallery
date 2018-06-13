package pl.edu.pwr.nr238367.tvgallery

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

const val PHOTO_AMOUNT = 200

class MainActivity : Activity(), PhotoDisplayer {

    private val urlList: ArrayList<String> = ArrayList()
    private var pagesLoaded: Int = 0
    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewManager = LinearLayoutManager(this)
        photoAdapter = PhotoAdapter(this, urlList, this)

        //get photo urls from api
        PhotoDownloader.addPhotos(this, urlList, PHOTO_AMOUNT, photoAdapter)
        pagesLoaded++

        //create recycler view
        photosList.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = photoAdapter
        }
    }

    override fun updateMainPhoto(view: View) {
        val position = photosList.findContainingViewHolder(view)?.adapterPosition
        if (position in 0 until urlList.size) {
            //load full resolution photo
            position?.let {
                Glide.with(this)
                        .load(urlList[position])
                        .centerCrop()
                        .into(main_photo)
//            Picasso.get().load(urlList[position])
//                    .error(R.drawable.error)
//                    .fit()
//                    .into(main_photo)
            }
        }
    }

    override fun loadMoreData() {
        pagesLoaded++
        PhotoDownloader.addPhotos(this, urlList, PHOTO_AMOUNT, photoAdapter, pagesLoaded)
    }
}
