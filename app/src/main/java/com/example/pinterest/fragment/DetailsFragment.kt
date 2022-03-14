package com.example.pinterest.fragment

import android.annotation.SuppressLint
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.a14_recyclerviewdragandswipekotlin.helper.SpacesItemDecoration
import com.example.pinterest.R
import com.example.pinterest.activity.DetailsActivity
import com.example.pinterest.activity.MainActivity
import com.example.pinterest.adapter.DetailsAdapter
import com.example.pinterest.database.PinRepository
import com.example.pinterest.model.Photo
import com.example.pinterest.model.Pin
import com.example.pinterest.model.RelatedPhotos
import com.example.pinterest.network.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsFragment (var photoItem: Photo) : Fragment() {

    private lateinit var adapter: DetailsAdapter
    private lateinit var pinRepository: PinRepository
    private lateinit var tvRelated: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pinRepository = PinRepository(requireActivity().application)
        adapter = DetailsAdapter(requireContext() as DetailsActivity)
        apiRelatedPhotos(photoItem.id!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        initViews(view)
        return view
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForColorStateLists")
    private fun initViews(view: View) {
        val ivBtnBack = view.findViewById<ImageView>(R.id.iv_btn_back)
        val ivPhoto = view.findViewById<ImageView>(R.id.iv_photo)
        val tvBtnSave = view.findViewById<TextView>(R.id.tv_btn_save)
        val ivProfile = view.findViewById<ImageView>(R.id.iv_profile)
        val tvFullName = view.findViewById<TextView>(R.id.tv_fullName)
        val tvFollowers = view.findViewById<TextView>(R.id.tv_followers)
        val tvDescription = view.findViewById<TextView>(R.id.tv_description)
        val ivProfileMe = view.findViewById<ImageView>(R.id.iv_profile_me)
        tvRelated = view.findViewById(R.id.tv_related)

        val rvDetails = view.findViewById<RecyclerView>(R.id.rv_details)
        rvDetails.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val decoration = SpacesItemDecoration(10)
        rvDetails.addItemDecoration(decoration)
        rvDetails.adapter = adapter

        val s1 = photoItem.alt_description
        val s2 = photoItem.description
        val s3 = photoItem.user!!.name

        Glide.with(requireContext()).load(photoItem.urls!!.small)
            .placeholder(ColorDrawable(Color.parseColor(photoItem.color))).into(ivPhoto)

        Glide.with(requireContext()).load(photoItem.user!!.profile_image!!.large)
            .placeholder(ColorDrawable(Color.parseColor(photoItem.color))).into(ivProfile)

        tvFullName.text = photoItem.user!!.name
        tvFollowers.text = "${photoItem.user!!.total_photos} Followers"
        tvDescription.text = getDescription(s1, s2, s3)

        Glide.with(requireContext()).load(R.drawable.im_sample_007)
            .placeholder(ColorDrawable(Color.parseColor(photoItem.color))).into(ivProfileMe)

        ivBtnBack.setOnClickListener {
            requireActivity().finish()
        }

        tvBtnSave.setOnClickListener {
            pinRepository.savePhoto(Pin(0, photoItem))
            tvBtnSave.backgroundTintList =
                requireContext().resources.getColorStateList(R.color.ic_default_color)
        }
    }

    private fun apiRelatedPhotos(id: String) {
        RetrofitHttp.photoService.getRelatedPhotos(id).enqueue(object : Callback<RelatedPhotos> {
            override fun onResponse(call: Call<RelatedPhotos>, response: Response<RelatedPhotos>) {

                val photoList: ArrayList<Photo> = response.body()!!.results!!
                if (photoList.size > 0) {
                    adapter.addPhotos(photoList)
                } else {
                    tvRelated.text = getString(R.string.related_photo_not_found)
                }
            }

            override fun onFailure(call: Call<RelatedPhotos>, t: Throwable) {
                Log.e("@@@", t.message.toString())
            }
        })
    }

    private fun getDescription(s1: Any?, s2: String?, s3: String?): String {
        return when {
            s1 != null -> s1.toString()
            s2 != null -> s2.toString()
            else -> "Photo was made by $s3"
        }
    }
}