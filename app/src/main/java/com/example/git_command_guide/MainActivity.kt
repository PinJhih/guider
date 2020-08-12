package com.example.git_command_guide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: OptionsAdapter
    private var resIds = ArrayList<Int>()
    private var itemPositions = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        rv_spinners.layoutManager = linearLayoutManager
        resIds.add(1)
        itemPositions.add(0)
        adapter = OptionsAdapter(this, resIds, itemPositions)
        rv_spinners.adapter = adapter
    }

    fun addItem() {
        adapter.notifyDataSetChanged()
        resIds.add(resIds[resIds.size - 1])
        itemPositions.add(0)
    }

    fun setOption(index: Int, value: Int) {
        itemPositions[index] = value
    }
}
