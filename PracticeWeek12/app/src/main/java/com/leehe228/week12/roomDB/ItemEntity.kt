package com.leehe228.week12.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemTable")
data class ItemEntity (
    var itemName: String,
    var itemQuantity: Int,
    @PrimaryKey(autoGenerate = true)
    val itemID: Int = 0
)
