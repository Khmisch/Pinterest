package com.example.pinterest.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.pinterest.R
import com.example.pinterest.activity.MainActivity
import com.example.pinterest.adapter.DetailsAdapter
import com.example.pinterest.model.Photo

class DetailsFragment (var items: ArrayList<Photo>, var position: Int) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var detailsAdapter: DetailsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        initViews(view)
        (requireContext() as MainActivity).hideBottomNavigation()
        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.rv_details)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        refreshAdapter(items)
        recyclerView.scrollToPosition(position)


        Log.d("ClickedPosition", "Fragment -> $position")
    }

    private fun refreshAdapter(items: ArrayList<Photo>){
        detailsAdapter = DetailsAdapter(requireContext(), items)
        recyclerView.adapter = detailsAdapter
    }
    private fun getDescription(s1: Any?, s2: String?, s3: String?): String {
        return when {
            s1 != null -> s1.toString()
            s2 != null -> s2.toString()
            else -> "Photo was made by $s3"
        }
    }

//    fun setTransparentStatusBar() {
//        view.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.statusBarColor = Color.TRANSPARENT
//        }
//    }
}