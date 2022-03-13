package com.example.pinterest.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pinterest.R
import com.example.pinterest.adapter.MessagesMessagesAdapter
import com.example.pinterest.adapter.MessagesUpdatesAdapter
import com.example.pinterest.model.Photos
import com.example.pinterest.network.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessagesUpdatesFragment : Fragment() {
    private lateinit var adapter: MessagesUpdatesAdapter
    private var currentPage = 1
    private var perPage = 20

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_message_updates, container, false)

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
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    apiPhotoList()
                }
            }
        })
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
        adapter = MessagesUpdatesAdapter(requireContext())
        recyclerView.adapter = adapter
    }
}