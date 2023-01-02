package fekri.info.ux

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import fekri.info.R

class PersonAdapter(private val data: ArrayList<Person>) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    inner class PersonViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgPerson = itemView.findViewById<ImageView>(R.id.item_main_img_person)
        private val txtName = itemView.findViewById<TextView>(R.id.item_main_txt_name_person)
        private val txtPhoneNumber = itemView.findViewById<TextView>(R.id.item_main_txt_number_person)
        private val txtCountryName = itemView.findViewById<TextView>(R.id.item_main_txt_country_person)

        @SuppressLint("SetTextI18n")
        fun bindData(position: Int) {

            // set name, phone number, country name
            txtName.text = data[position].name
            txtPhoneNumber.text = "+${data[position].phoneNumber}"
            txtCountryName.text = data[position].country

            // set image -->
            Glide
                .with(itemView.context)
                .load(data[position].urlImage)
                .transform(CenterInside(), RoundedCorners(24))
                .into(imgPerson)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_main, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}