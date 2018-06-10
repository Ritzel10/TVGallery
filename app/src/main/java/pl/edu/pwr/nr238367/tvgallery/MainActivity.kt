package pl.edu.pwr.nr238367.tvgallery

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

const val PHOTO_AMOUNT = 200

class MainActivity : Activity() {
    private val urlList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewManager = LinearLayoutManager(this)
        val photoAdapter = PhotoAdapter( urlList, ::updateMainPhoto)
        //get photo urls from api
        PhotoDownloader.getPhotos(this, urlList, PHOTO_AMOUNT, photoAdapter)

        photosList.apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = photoAdapter
        }
    }
    private fun updateMainPhoto(view: View){
        val position = photosList.findContainingViewHolder(view)?.adapterPosition
        //load full resolution photo
        position?.let {
            Picasso.get().load(urlList[position])
                    .error(R.drawable.error)
                    .fit()
                    .into(main_photo)
        }
    }
}
