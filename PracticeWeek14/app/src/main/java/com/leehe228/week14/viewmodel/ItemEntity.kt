package com.leehe228.week14.viewmodel

data class ItemEntity(
    var itemName: String,
    var itemQuantity: Int,
    var itemID: Int
) {
    constructor() : this("no_info", 0, 0)
}
