package com.example.luckytrip.ui.fragments.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.luckytrip.data.models.Room
import com.example.luckytrip.databinding.ListItemBinding

class RoomsAdapter(private val clickListener: RoomListener) :
    RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {

    companion object {
        var data = listOf<Room>()
        var parentLayout : ConstraintLayout? = null
        var selectedTypesId : Long = -1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (selectedTypesId == data[position].roomID) {



        }

        holder.bind(data[position], clickListener, this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(
            item: Room,
            clickListener: RoomListener,
            bookingTypesAdapter: RoomsAdapter
        ) {
            binding.room = item

            binding.rootConstraint.setOnClickListener {

                //setItemSelected(it)

                if (selectedTypesId != item.roomID)
                    selectedTypesId = item.roomID!!

                clickListener.onClick(item)

            }

            binding.executePendingBindings()
        }
    }

    fun clearSelectedTypesId() {
        selectedTypesId = -1
    }

    fun getSelectedId(): Long {
        return selectedTypesId
    }

    fun updateList(list: List<Room>) {

        data = list.sortedBy { it.maxOccupancy }

    }

    override fun getItemCount() = data.size

}

interface RoomListener {

    fun onClick(

        room: Room

    )

}