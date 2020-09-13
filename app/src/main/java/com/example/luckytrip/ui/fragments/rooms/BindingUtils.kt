package com.example.luckytrip.ui.fragments.rooms

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.luckytrip.data.models.Room

@BindingAdapter("roomName")
fun TextView.setRoomName(item: Room?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("roomPrice")
fun TextView.setRoomPrice(item: Room?) {
    item?.let {
        text = "${item.price?.priceValue} ${item.price?.currency}"
    }
}

@BindingAdapter("roomDesc")
fun TextView.setRoomDesc(item: Room?) {
    item?.let {
        text = item.roomDescription
    }
}

@BindingAdapter("roomBedConf")
fun TextView.setRoomBedConf(item: Room?) {
    item?.let {
        text = "${item.bedConfigurations?.get(0)?.count} ${item.bedConfigurations?.get(0)?.name}"
    }
}

@BindingAdapter("roomOccupancy")
fun TextView.setRoomOccupancy(item: Room?) {
    item?.let {
        text = "Max occupancy ${item.maxOccupancy.toString()}"
    }
}