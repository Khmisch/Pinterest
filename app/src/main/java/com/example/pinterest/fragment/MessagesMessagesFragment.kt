package com.example.pinterest.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.a14_recyclerviewdragandswipekotlin.helper.SpacesItemDecoration
import com.example.pinterest.R
import com.example.pinterest.adapter.HomeAdapter
import com.example.pinterest.adapter.MessagesMessagesAdapter
import com.example.pinterest.model.Photos
import com.example.pinterest.network.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessagesMessagesFragment : Fragment() {
    private lateinit var adapter: MessagesMessagesAdapter
    private var currentPage = 1
    private var perPage = 10

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_message_message, container, false)

        initViews(view)
        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiPhotoList()
    }


    private fun initViews(view: View) {
        var recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setLayoutManager(GridLayoutManager(context,1))

        refreshAdapter(recyclerView)
    }

    private fun apiPhotoList() {
        RetrofitHttp.photoService.getPhotos(++currentPage, perPage)
            .enqueue(object : Callback<Photos> {
                override fun onResponse(call: Call<Photos>, response: Response<Photos>) {
                    adapter.addPhotos(response.body()!!)
                }

                override fun onFailure(call: Call<Photos>, t: Throwable) {
                    Log.e("@@@onFailure", t.message.toString())
                    Log.e("@@@onFailure", t.toString())
                }
            })
    }

    private fun refreshAdapter(recyclerView: RecyclerView) {
        adapter = MessagesMessagesAdapter(requireContext())
        recyclerView.adapter = adapter
    }

}