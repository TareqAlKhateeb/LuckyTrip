package com.example.luckytrip.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Rooms : Serializable {

    @SerializedName("rooms")
    @Expose
    val rooms: List<Room>? = null

}

class Room : Serializable {

    @SerializedName("room_id")
    @Expose
    val roomID: Long? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("room_description")
    @Expose
    val roomDescription: String? = null

    @SerializedName("number_of_rooms_left")
    @Expose
    val numberOfRoomsLeft: Long? = null

    @SerializedName("max_occupancy")
    @Expose
    val maxOccupancy: Long? = null

    @SerializedName("price")
    @Expose
    val price: Price? = null

    @SerializedName("bed_configurations")
    @Expose
    val bedConfigurations: List<BedConfiguration>? = null

    @SerializedName("photos")
    @Expose
    val photos: List<String>? = null

}

class BedConfiguration : Serializable {

    @SerializedName("count")
    @Expose
    val count: Long? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

}

class Price : Serializable {

    @SerializedName("currency")
    @Expose
    val currency: String? = null

    @SerializedName("price_value")
    @Expose
    val priceValue: Double? = null

}
