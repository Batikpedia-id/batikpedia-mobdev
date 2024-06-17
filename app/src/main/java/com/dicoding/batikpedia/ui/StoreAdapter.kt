package com.dicoding.batikpedia.ui

import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.batikpedia.R
import com.dicoding.batikpedia.api.response.Store

class StoreAdapter(private var storeList: List<Store>) :
    RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store = storeList[position]
        holder.bind(store)
    }

    override fun getItemCount(): Int {
        return storeList.size
    }

    fun updateStores(newStores: List<Store>) {
        storeList = newStores
        notifyDataSetChanged()
    }

    inner class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val storeName: TextView = itemView.findViewById(R.id.storeName)
        private val storeAddress: TextView = itemView.findViewById(R.id.storeAddress)
        private val storeInstagram: TextView = itemView.findViewById(R.id.storeInstagram)
        private val storePhone: TextView = itemView.findViewById(R.id.storePhone)
        private val storeTokopedia: TextView = itemView.findViewById(R.id.storeTokopedia)
        private val storeTiktok: TextView = itemView.findViewById(R.id.storeTiktok)

        fun bind(store: Store) {
            storeName.text = store.name ?: ""
            storeAddress.text = store.address ?: ""
            storeInstagram.text = store.instagram ?: ""
            storePhone.text = store.phone ?: ""
            storeTokopedia.text = store.tokopedia ?: ""
            storeTiktok.text = store.tiktok ?: ""

            // Linkify Instagram, Tokopedia, and TikTok
            Linkify.addLinks(storePhone, Linkify.PHONE_NUMBERS)
            Linkify.addLinks(storeInstagram, Linkify.WEB_URLS)
            Linkify.addLinks(storeTokopedia, Linkify.WEB_URLS)
            Linkify.addLinks(storeTiktok, Linkify.WEB_URLS)
        }
    }
}