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
        if (node.options.size != 0) {
            nodes.add(node)
            adapter.notifyDataSetChanged()
            tv_command.text = "..."
        }
    }

    fun setPosition(index: Int, value: Int) {
        nodes[index].position = value
    }

    fun resetNodes(index: Int) {
        val list = ArrayList<Node>()
        for (i in 0..index)
            list.add(nodes[i])
        nodes.clear()
        nodes.addAll(list)
        nodes[index].position = 0
        adapter.notifyDataSetChanged()
        tv_command.text = "..."
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
        try {
            val arrIds = resources.obtainTypedArray(id)
            for (i in 0 until arrIds.length()) {    
                if (i == 0)
                    n.options = getOptions(arrIds.getResourceId(i, 0))
                ids.add(arrIds.getResourceId(i, 0))
            }
            n.nextId = ids
            arrIds.recycle()
        } catch (e: Exception) {
            setText(id)
        }
        return n
    }

    private fun setText(id: Int) {
        tv_command.text = resources.getString(id)
    }
}
