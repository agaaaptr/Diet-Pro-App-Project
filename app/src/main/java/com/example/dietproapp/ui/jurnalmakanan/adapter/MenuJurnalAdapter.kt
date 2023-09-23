package com.example.dietproapp.ui.jurnalmakanan.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.dietproapp.core.data.source.model.Makanan
import com.example.dietproapp.databinding.ListJurnalBinding
import java.util.Locale

@SuppressLint("NotifyDataSetChanged")
class MenuJurnalAdapter : RecyclerView.Adapter<MenuJurnalAdapter.ViewHolder>(), Filterable {

    var data = ArrayList<Makanan>()
    private var filteredData = ArrayList<Makanan>()
    val checkedItems = mutableMapOf<Int, Boolean>()

    inner class ViewHolder(val itemBinding: ListJurnalBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Makanan, position: Int) {
            itemBinding.apply {
                item.id
                namaBahan.text = item.Nama_Bahan
                EnKkal.text = item.Energi_kkal
                protein.text = item.Protein_g
                lemak.text = item.Lemak_g
                Kh.text = item.KH_g
                serat.text = item.Serat_Total_g
                natrium.text = item.Natrium_mg
                kalium.text = item.Kalium_mg
            }
        }
    }

    fun addItems(items: List<Makanan>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString().toLowerCase(Locale.getDefault())
                filteredData.clear()
                if (charSearch.isEmpty()) {
                    filteredData.addAll(data)
                } else {
                    for (item in data) {
                        if (item.Nama_Bahan?.toLowerCase(Locale.getDefault())?.contains(charSearch) == true) {
                            filteredData.add(item)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredData
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredData = results?.values as ArrayList<Makanan>
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(
            ListJurnalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        // Tambahkan listener untuk checkbox di sini
        viewHolder.itemBinding.cbMakanan.setOnCheckedChangeListener { _, isChecked ->
            val position = viewHolder.adapterPosition
            checkedItems[position] = isChecked
        }

        return viewHolder
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val makanan = data[position]
        holder.bind(makanan, position)

        // Check if Energi_kkal is not null before setting it in your view
        val energiKkal = makanan.Energi_kkal
        holder.itemBinding.cbMakanan.isChecked = checkedItems[position] ?: false

        if (energiKkal != null) {
            holder.itemBinding.EnKkal.text = energiKkal
        } else {
            // Handle the case where Energi_kkal is null (optional)
            holder.itemBinding.EnKkal.text = "N/A"
        }
//        holder.bind(data[position], position)

        holder.itemBinding.cbMakanan.isChecked = checkedItems[position]?:false
        holder.bind(filteredData[position], position)
    }

    override fun getItemCount(): Int {
        return filteredData.size
    }
}
