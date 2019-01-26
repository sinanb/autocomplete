package com.company.autocompletionexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView

class AutoCompleteAdapter(context : Context) : BaseAdapter(), Filterable {

    private var itemList = arrayOf("Apple", "Pear", "Watermelon", "Melon", "Pineapple", "Coconut", "Banana",
        "Strawberry", "Blueberry", "Grape", "Orange", "Tangerine", "Bully")

    private val layoutInflater : LayoutInflater = LayoutInflater.from(context)
    private var resultsMatchingList: MutableList<String> = mutableListOf()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val view : View?
        val viewHolder : ListRowHolder

        if (convertView == null) {
            view = layoutInflater.inflate(android.R.layout.simple_dropdown_item_1line, parent,
                false)

            viewHolder = ListRowHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ListRowHolder
        }

        viewHolder.label.text = resultsMatchingList[position]

        return view
    }

    override fun getItem(position: Int): Any {
        return resultsMatchingList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return resultsMatchingList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filterResults = FilterResults()

                if (!constraint.isNullOrBlank()) {
                    resultsMatchingList = findMatches(constraint.toString())

                    filterResults.values = resultsMatchingList
                    filterResults.count = resultsMatchingList.size
                }

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null && results.count > 0) {
                    @Suppress("UNCHECKED_CAST")
                    resultsMatchingList = results.values as MutableList<String>

                    notifyDataSetChanged()
                } else {
                    notifyDataSetInvalidated()
                }
            }
        }
    }

    private fun findMatches(constraint : String) : MutableList<String> {
        return itemList
            .filter { constraint.equals(it, true) ||
                    it.contains(constraint, true) }
            .toMutableList()
    }

    private class ListRowHolder(row: View?) {
        val label: TextView = row?.findViewById(android.R.id.text1) as TextView
    }
}
