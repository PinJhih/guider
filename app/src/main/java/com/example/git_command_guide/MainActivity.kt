package com.example.git_command_guide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: OptionsAdapter
    private var resIds = ArrayList<ArrayList<Int>>()
    private var options = ArrayList<ArrayList<String>>()
    private var itemPositions = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        rv_spinners.layoutManager = linearLayoutManager
        adapter = OptionsAdapter(this, options, resIds, itemPositions)
        rv_spinners.adapter = adapter
        addItem(R.array.g1)
    }

    fun addItem(id: Int) {
        getRes(id)
        itemPositions.add(0)
        adapter.notifyDataSetChanged()
    }

    fun setOption(index: Int, value: Int) {
        itemPositions[index] = value
    }

    private fun getOptions(id: Int): ArrayList<String> {
        val options = arrayListOf("...")
        val arrOptions = resources.getStringArray(id)
        options.addAll(arrOptions)
        return options
    }

    private fun getRes(id: Int) {
        val ids = ArrayList<Int>()
        val arrIds = resources.obtainTypedArray(id)
        for (i in 0 until arrIds.length()) {
            if (i == 0) {
                val optionId = arrIds.getResourceId(i, 0)
                options.add(getOptions(optionId))
            }
            ids.add(arrIds.getResourceId(i, 0))
        }
        arrIds.recycle()
        resIds.add(ids)
    }
}
