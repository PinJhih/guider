package com.example.git_command_guide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: OptionsAdapter
    private var nodes = ArrayList<Node>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        rv_spinners.layoutManager = linearLayoutManager
        adapter = OptionsAdapter(this, nodes)
        rv_spinners.adapter = adapter
        addItem(R.array.g1)
    }

    fun addItem(id: Int) {
        val node = getRes(id)
        nodes.add(node)
        adapter.notifyDataSetChanged()
    }

    fun setPosition(index: Int, value: Int) {
        nodes[index].position = value
    }

    private fun getOptions(id: Int): ArrayList<String> {
        val options = arrayListOf("...")
        val arrOptions = resources.getStringArray(id)
        options.addAll(arrOptions)
        return options
    }

    private fun getRes(id: Int): Node {
        val n = Node()
        val ids = ArrayList<Int>()
        val arrIds = resources.obtainTypedArray(id)
        for (i in 0 until arrIds.length()) {
            if (i == 0)
                n.options = getOptions(arrIds.getResourceId(i, 0))
            ids.add(arrIds.getResourceId(i, 0))
        }
        n.nextId = ids
        arrIds.recycle()
        return n
    }
}
