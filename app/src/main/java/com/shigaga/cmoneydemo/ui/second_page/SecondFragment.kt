package com.shigaga.cmoneydemo.ui.second_page

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialSharedAxis
import com.shigaga.cmoneydemo.R
import com.shigaga.cmoneydemo.data.ContentItem
import kotlinx.android.synthetic.main.row_item.view.*
import kotlinx.android.synthetic.main.second_fragment.*

class SecondFragment : Fragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }

    private lateinit var viewModel: SecondViewModel
    private lateinit var itemList: ArrayList<ContentItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        val forward = MaterialSharedAxis.create(MaterialSharedAxis.Y, true)
        enterTransition = forward

        val backward = MaterialSharedAxis.create(MaterialSharedAxis.Y, false)
        returnTransition = backward

        itemList = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView.layoutManager = GridLayoutManager(context, 4)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ItemListAdapter()



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SecondViewModel::class.java)

        Toast.makeText(context,"開始抓取Json資料", Toast.LENGTH_LONG).show()
        viewModel.fetchDataByHttpUrlConnection()
    }


    inner class ItemListAdapter : RecyclerView.Adapter<ItemHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item, parent,false)

            return ItemHolder(view)
        }

        override fun getItemCount(): Int {
            return itemList.size
        }

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
            /**holder.image
            holder.id = itemList.get(position)*/
        }
    }

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view){
        var image : ImageView = view.imageView
        var id : TextView = view.itemId
        var title : TextView = view.itemTitle
    }
}
