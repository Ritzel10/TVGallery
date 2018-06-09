package pl.edu.pwr.nr238367.tvgallery

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.photo_row.view.*


class MainActivity : Activity() {
    val urlList = listOf("https://rapdose.com/wp-content/uploads/2018/06/kanye-west-kid-cudi-kids-see-ghosts-album-stream.png", "https://i.imgur.com/U4d8MLm.jpg", "https://i.redd.it/tqt2ewnd9m901.jpg",  "https://i.redd.it/n0x244u8ajez.png", "https://i.redd.it/3fpc5bvecuq01.jpg", "https://imgur.com/8oKJVqi", "https://i.redd.it/g4eou5yk0sf01.jpg")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewManager = LinearLayoutManager(this)
        val photoAdapter = PhotoAdapter( urlList, ::updateMainPhoto)
        photosList.apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = photoAdapter
        }
    }
    private fun updateMainPhoto(view: View){
        val position = photosList.findContainingViewHolder(view)?.adapterPosition
        position?.let {
            Picasso.get().load(urlList[position])
                    .placeholder(R.drawable.banner)
                    .error(R.drawable.banner)
                    .fit()
                    .into(main_photo)
        }
    }
}
