package com.lukapps.diapertracker

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by lvidal on 12/10/17.
 */

class HistoryAdapter(private var history: List<HistoryItem>)
    : RecyclerView.Adapter<HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HistoryViewHolder {
        val inflatedView = parent?.inflate(R.layout.history_item, false)
        return HistoryViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder?, position: Int) {
        holder?.bindHistoryItem(history[position])
    }

    override fun getItemCount() = history.size

    fun add(historyItem: HistoryItem) {
        history += historyItem
        notifyDataSetChanged()
    }

    fun remove(historyItem: HistoryItem) {
        val item = history.find { it.date == historyItem.date }
        item?.let {
            history -= it
            notifyDataSetChanged()
        }
    }
}

