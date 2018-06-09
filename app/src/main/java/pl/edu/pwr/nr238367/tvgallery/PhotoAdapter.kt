package pl.edu.pwr.nr238367.tvgallery

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_row.view.*


class PhotoAdapter(private val photoUrls:List<String>, private val onFocusChanged:(view: View)->Unit?) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.photo_row, parent, false)
        return ViewHolder(view)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return photoUrls.size
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rowView = holder.rowView
        val url = photoUrls[position]

        rowView.setOnClickListener { v ->
            v.clearAnimation()
            v.isSelected = true
            v.clearAnimation()
        }
        rowView.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.focusable_border)
                onFocusChanged(view)
            }
            if (!hasFocus) {
                view.setBackgroundResource(0)
            }
        }
//        Glide.with(context)
//                .load(url)
//                .placeholder(R.drawable.banner)
//                .error(R.drawable.banner)
//                .fitCenter()
//                .into(rowView.imageCard.photo)
        Picasso.get().load(url)
                .error(R.drawable.error)
                .fit()
                .into(rowView.imageCard.photo)
    }

    class ViewHolder(val rowView: View) :RecyclerView.ViewHolder(rowView)
}