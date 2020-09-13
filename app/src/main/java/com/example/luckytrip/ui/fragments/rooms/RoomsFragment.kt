package com.example.luckytrip.ui.fragments.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.luckytrip.R
import com.example.luckytrip.data.models.Room
import com.example.luckytrip.databinding.FragmentRoomsBinding
import com.example.luckytrip.ui.base.BaseFragment
import com.example.luckytrip.ui.fragments.intro.IntroFragment.Companion.mMutableLiveItemSelected
import com.example.luckytrip.util.AppConstants.MY_ROOM
import com.example.luckytrip.util.MySharedPreferences
import com.example.luckytrip.util.mSQLite.SQLiteManager

class RoomsFragment : BaseFragment() {

    private lateinit var binding: FragmentRoomsBinding

    private lateinit var viewModel: RoomsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_rooms, container, false
        )

        viewModel = ViewModelProvider(this).get(RoomsViewModel::class.java)

        binding.roomsViewModel = viewModel

        setupViews()

        return binding.root
    }

    private fun setupViews() {

        setupListeners()

        initAdapter()

    }

    private fun initAdapter() {

        val roomAdapter = RoomsAdapter(
            object :
                RoomListener {
                override fun onClick(room: Room) {

                    mMutableLiveItemSelected.value = room

                    MySharedPreferences.setSerializableValue(MY_ROOM,room)

                    activity?.onBackPressed()

                }

            })

        binding.roomsRecyclerView.adapter = roomAdapter
        SQLiteManager.getSQLiteData()?.rooms?.let { roomAdapter.updateList(it) }
        roomAdapter.notifyDataSetChanged()
    }

    private fun setupListeners() {

        binding.backArrowIv.setOnClickListener {
            activity?.onBackPressed()
        }

    }

}