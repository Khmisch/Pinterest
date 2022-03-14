package com.example.pinterest.fragment

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.a14_recyclerviewdragandswipekotlin.helper.SpacesItemDecoration
import com.example.pinterest.R
import com.example.pinterest.adapter.MessagesViewPagerAdapter
import com.example.pinterest.adapter.ProfileViewPagerAdapter
import com.example.pinterest.adapter.ResultPhotosAdapter
import com.example.pinterest.database.PinRepository
import com.example.pinterest.model.Photo
import com.example.pinterest.model.profile.ProfileResp
import com.example.pinterest.network.RetrofitHttp
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {
    private lateinit var rvSavedPhotos: RecyclerView
    private lateinit var photosAdapter: ResultPhotosAdapter
    private lateinit var pinRepository: PinRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    override fun onResume() {
        super.onResume()
        refreshAdapter()
    }

    private fun initViews(view: View) {
        rvSavedPhotos = view.findViewById(R.id.rv_saved_photos)

        rvSavedPhotos.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val decoration = SpacesItemDecoration(10)
        rvSavedPhotos.addItemDecoration(decoration)
        pinRepository = PinRepository(requireActivity().application)
        photosAdapter = ResultPhotosAdapter(requireContext())
        rvSavedPhotos.adapter = photosAdapter
    }

    private fun refreshAdapter() {
        val pinList = pinRepository.getAllSavedPhotos()
        val photoList = ArrayList<Photo>()
        for (item in pinList) {
            item.photoItem?.let { photoList.add(it) }
        }
        photosAdapter.addNewPhotos(photoList)
    }
}