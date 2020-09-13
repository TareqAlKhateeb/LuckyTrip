package com.example.luckytrip.ui.fragments.intro

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.luckytrip.R
import com.example.luckytrip.data.models.Room
import com.example.luckytrip.databinding.FragmentIntroBinding
import com.example.luckytrip.ui.base.BaseFragment
import com.example.luckytrip.util.MySharedPreferences
import com.example.luckytrip.util.setViewVisibility

class IntroFragment : BaseFragment() {

    private lateinit var binding: FragmentIntroBinding

    private lateinit var viewModel: IntroViewModel

    private var bookingFilterPopup : PopupWindow? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_intro, container, false
        )

        viewModel = ViewModelProvider(this).get(IntroViewModel::class.java)

        binding.introViewModel = viewModel

        setupListener()

        responseHandler()

        checkForSelectedRoom()

        return binding.root
    }

    private fun checkForSelectedRoom() {

        if (MySharedPreferences.retrieveRoomObject(requireActivity()) != null) {

            binding.showCardView.setViewVisibility(true)

            Handler().postDelayed(
                {

                    showSelectedRoomPopup(MySharedPreferences.retrieveRoomObject(requireActivity()))


                }, 50
            )

        }

    }

    private fun setupListener() {

        binding.exploreCardView.setOnClickListener {

            this.findNavController().navigate(IntroFragmentDirections.actionIntroFragmentToRoomsFragment())

        }

        binding.showCardView.setOnClickListener {

            showSelectedRoomPopup(MySharedPreferences.retrieveRoomObject(requireActivity()))

        }

    }

    private fun responseHandler() {

        mutableLiveItemSelected.observe(viewLifecycleOwner, {

            Handler().postDelayed(
                {

                    showSelectedRoomPopup(it)


                }, 50
            )

        })

    }

    companion object {

        val mMutableLiveItemSelected = MutableLiveData<Room?>()
        private val mutableLiveItemSelected
            get() = mMutableLiveItemSelected

    }

    @SuppressLint("InflateParams", "SetTextI18n")
    private fun showSelectedRoomPopup(room: Room?) {
        // Inflate the popup_layout.xml

        if (bookingFilterPopup != null && bookingFilterPopup!!.isShowing) {
            return
        }

        val layoutInflater =
            activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout: View = layoutInflater.inflate(R.layout.list_item_selected, null)

        val rootConstraint = layout.findViewById<ConstraintLayout>(R.id.rootConstraint)

        setupPopupViews(layout, room)

        binding.alphaLayout.setViewVisibility(true)

        // Creating the PopupWindow
        bookingFilterPopup = PopupWindow(activity)
        bookingFilterPopup?.contentView = layout
        bookingFilterPopup?.animationStyle = R.style.popup_window_animation
        bookingFilterPopup?.width = LinearLayout.LayoutParams.MATCH_PARENT
        bookingFilterPopup?.height = LinearLayout.LayoutParams.MATCH_PARENT
        bookingFilterPopup?.isFocusable = true
        bookingFilterPopup?.isClippingEnabled = false

        bookingFilterPopup?.setOnDismissListener {
            binding.alphaLayout.setViewVisibility(false)
        }

        rootConstraint.setOnClickListener {

            bookingFilterPopup?.dismiss()

        }

        //Clear the default translucent background
        bookingFilterPopup?.setBackgroundDrawable(
            BitmapDrawable(
                context?.resources,
                null as Bitmap?
            )
        )

        // Displaying the popup at the specified location, + offsets.
        bookingFilterPopup?.showAtLocation(binding.rootView, Gravity.CENTER, 0, 0)

    }

    @SuppressLint("SetTextI18n")
    private fun setupPopupViews(layout: View, room: Room?) {

        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)

        val cardView = layout.findViewById<CardView>(R.id.cardView)
        val roomName = layout.findViewById<TextView>(R.id.roomName)
        val roomPrice = layout.findViewById<TextView>(R.id.roomPrice)
        val roomDesc = layout.findViewById<TextView>(R.id.roomDesc)
        val bedConfiguration = layout.findViewById<TextView>(R.id.bedConfiguration)
        val roomMaxOccupancy = layout.findViewById<TextView>(R.id.roomMaxOccupancy)
        val imageView = layout.findViewById<ImageView>(R.id.iv)

        cardView.layoutParams.height = (displayMetrics.heightPixels * 0.4).toInt()

        roomName.text = room?.name
        roomPrice.text = "${room?.price?.priceValue} ${room?.price?.currency}"
        roomDesc.text = room?.roomDescription
        bedConfiguration.text = "${room?.bedConfigurations?.get(0)?.count} ${room?.bedConfigurations?.get(
            0
        )?.name}"
        roomMaxOccupancy.text = getString(R.string.max_occ) + " " + room?.maxOccupancy
        Glide.with(this).load(room?.photos?.get(0)).into(imageView)

    }

}