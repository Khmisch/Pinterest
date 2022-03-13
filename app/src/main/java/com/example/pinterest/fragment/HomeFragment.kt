package com.example.pinterest.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.a14_recyclerviewdragandswipekotlin.helper.SpacesItemDecoration
import com.example.pinterest.R
import com.example.pinterest.activity.DetailsActivity
import com.example.pinterest.adapter.HomeAdapter
import com.example.pinterest.model.Photo
import com.example.pinterest.network.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HomeAdapter
    private var currentPage = 1
    private var perPage = 30

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerPins)
        recyclerView.setLayoutManager(
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        )
        val decoration = SpacesItemDecoration(10)
        recyclerView.addItemDecoration(decoration)
        apiPhotoList()
    }

    private fun apiPhotoList() {
        RetrofitHttp.photoService.getPhotos(++currentPage, perPage)
            .enqueue(object : Callback<ArrayList<Photo>> {
                override fun onResponse(
                    call: Call<ArrayList<Photo>>,
                    response: Response<ArrayList<Photo>>
                ) {
                    val body = response.body()
                    Log.d("@@@",body.toString())
                    if (body != null) {
                        refreshAdapter(body)
                        //adapter.addPhotos(body)
                    }
                }

                override fun onFailure(call: Call<ArrayList<Photo>>, t: Throwable) {
                    Log.e("@@@onFailure", t.message.toString())
                }
            })
    }

    private fun refreshAdapter(items: ArrayList<Photo>) {
        adapter = HomeAdapter(this, items)
        recyclerView.adapter = adapter
    }

    fun callDetailsActivity(photo: Photo){
        var intent = Intent(activity, DetailsActivity::class.java)
        startActivity(intent)
    }
}