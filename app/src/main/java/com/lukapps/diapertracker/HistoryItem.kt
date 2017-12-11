package com.lukapps.diapertracker

import java.util.*

/**
 * Created by lvidal on 12/10/17.
 */
class HistoryItem(var userName: String, var date: Date) {
    constructor() : this("", Date())
}