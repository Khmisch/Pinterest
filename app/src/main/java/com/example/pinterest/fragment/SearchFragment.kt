package com.example.pinterest.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.a14_recyclerviewdragandswipekotlin.helper.SpacesItemDecoration
import com.example.pinterest.R
import com.example.pinterest.adapter.SearchAdapter
import com.example.pinterest.model.Photo
import com.example.pinterest.model.Search
import com.example.pinterest.network.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private lateinit var edt_search: EditText
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: SearchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        edt_search = view.findViewById(R.id.et_search)
        edt_search.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val keyword = edt_search.text.toString()
                apiSearchPhoto(keyword)
                return@OnEditorActionListener true
            }
            false
        })
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setLayoutManager(
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        )
        val decoration = SpacesItemDecoration(10)
        recyclerView.addItemDecoration(decoration)

        val keyword = edt_search.text.toString()
        apiSearchPhoto(keyword)
    }

    private fun apiSearchPhoto(query: String){
        RetrofitHttp.photoService.searchPhoto(query, 1, 30).enqueue(object: Callback<Search>{
            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                var body = response.body()
                if(body != null){
                    refreshAdapter(body.results as ArrayList<Photo>)
                }
            }

            override fun onFailure(call: Call<Search>, t: Throwable) {
                Log.d("@@@", "Search -> ${t.localizedMessage}")
            }

        })
    }

    private fun refreshAdapter(items: ArrayList<Photo>) {
        adapter = SearchAdapter(this, items)
        recyclerView.adapter = adapter
    }
}