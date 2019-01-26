package com.company.autocompletionexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatAutoCompleteTextView
import android.view.View
import android.widget.AdapterView
import android.widget.Toast

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    lateinit var autoCompleteTextView : AppCompatAutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        autoCompleteTextView = findViewById(R.id.autoTextView)
        setAdapter()
        autoCompleteTextView.onItemClickListener = this
    }

    private fun setAdapter() {
        autoCompleteTextView.setAdapter(AutoCompleteAdapter(this))
        autoCompleteTextView.threshold = 1
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this, "item : " +
                autoCompleteTextView.adapter.getItem(position), Toast.LENGTH_LONG).show()
    }
}
