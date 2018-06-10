package pl.edu.pwr.nr238367.tvgallery

import android.graphics.Color
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


    override fun getItemCount(): Int {
        return photoUrls.size
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rowView = holder.rowView
        val url = photoUrls[position]

        rowView.setOnClickListener { v ->
            v.isSelected = true
        }
        rowView.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                //highlight focused item
                view.setBackgroundResource(R.drawable.focusable_border)
                onFocusChanged(view)
            }
            if (!hasFocus) {
                //unhighlight deselected item
                view.setBackgroundColor(Color.TRANSPARENT)
            }
        }
        //load the photo
        Picasso.get().load(url)
                .error(R.drawable.error)
                .fit()
                .into(rowView.imageCard.photo)
    }

    class ViewHolder(val rowView: View) :RecyclerView.ViewHolder(rowView)
}