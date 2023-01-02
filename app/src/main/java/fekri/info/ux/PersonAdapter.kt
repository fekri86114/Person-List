package fekri.info.ux

import android.annotation.SuppressLint
import android.content.Context
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

class PersonAdapter(private val data: ArrayList<Person>, private val personEvents: PersonEvents) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    inner class PersonViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {

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
                .with(context)
                .load(data[position].urlImage)
                .transform(CenterInside(), RoundedCorners(24))
                .into(imgPerson)

            // if clicked on ite (not long), edit it
            itemView.setOnClickListener {
                personEvents.onPersonClicked(data[adapterPosition], adapterPosition)
            }

            // if clicked on ite long, delete it
            itemView.setOnLongClickListener {
                personEvents.onPersonLongClicked(data[adapterPosition], adapterPosition)
                true
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_main, parent, false)
        return PersonViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addPerson(newPerson: Person, position: Int) {
        // add new person to list -->
        data.add(0, newPerson)
        notifyItemInserted(0)
    }

    fun deletePerson(oldPerson: Person, oldPosition: Int) {
        // remove item from list -->
        data.remove(oldPerson)
        notifyItemChanged(oldPosition)
    }

    fun updatedPerson(newPerson: Person, position: Int) {
        // update food from list -->
        data[position] = newPerson
        notifyItemChanged(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: ArrayList<Person>) {
        // set new data to list -->
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
    }

    interface PersonEvents {
        /*
         * 1. create interface in adapter
         * 2. get an object of interface in args of adapter
         * 3. fill ( call ) objects of interface with your data
         * 4. implementation
         */

        fun onPersonClicked(person: Person, position: Int)
        fun onPersonLongClicked(person: Person, position: Int)
    }

}