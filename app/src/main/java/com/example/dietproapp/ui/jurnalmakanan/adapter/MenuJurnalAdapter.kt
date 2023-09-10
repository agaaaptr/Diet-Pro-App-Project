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

    private var data = ArrayList<Makanan>()
    private var filteredData = ArrayList<Makanan>()

    inner class ViewHolder(private val itemBinding: ListJurnalBinding) :
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
        return ViewHolder(
            ListJurnalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredData[position], position)
    }

    override fun getItemCount(): Int {
        return filteredData.size
    }
}
