package com.example.git_command_guide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_option.view.*

class OptionsAdapter(
    private val context: Context,
    private val nodes: ArrayList<Node>
) : RecyclerView.Adapter<OptionsAdapter.ViewHolder>() {
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_option, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = nodes.size

    override fun onBindViewHolder(holder: ViewHolder, index: Int) {
        val spinnerAdapter =
            ArrayAdapter(
                context,
                android.R.layout.simple_spinner_dropdown_item,
                nodes[index].options
            )
        holder.itemView.spr_option.adapter = spinnerAdapter
        holder.itemView.spr_option.setSelection(nodes[index].position)
        holder.itemView.spr_option.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    if (position != nodes[index].position && position != 0) {
                        (context as MainActivity).setPosition(index, position)
                        context.addItem(nodes[index].nextId[position])
                    }
                }
            }
    }
}
