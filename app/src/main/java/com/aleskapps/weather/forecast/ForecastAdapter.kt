package com.aleskapps.weather.forecast

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aleskapps.weather.databinding.ForecastListItemBinding

class ForecastAdapter(
    private val context: Context
): RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    var items: List<ForecastViewModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ForecastListItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(private val binding: ForecastListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ForecastViewModel) {
            binding.forecast = item
            binding.executePendingBindings()
        }

    }

}