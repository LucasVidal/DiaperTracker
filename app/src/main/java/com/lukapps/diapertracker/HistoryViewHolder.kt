package com.lukapps.diapertracker

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.history_item.view.*

class HistoryViewHolder(v: View?) : RecyclerView.ViewHolder(v) {
    private var view : View? = v
    private var historyItem: HistoryItem? = null


    fun bindHistoryItem(historyItem: HistoryItem) {
        this.historyItem = historyItem

        view?.let {
            it.userNameTxt.text = historyItem.userName
            it.dateTxt.text =historyItem.date.toString()
        }

    }

}