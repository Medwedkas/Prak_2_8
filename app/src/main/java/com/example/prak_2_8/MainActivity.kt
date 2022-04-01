package com.example.prak_2_8

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: SolutViewModel
    private lateinit var arg: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val provider = ViewModelProvider(this)
        viewModel = provider.get(SolutViewModel::class.java)
        val button = findViewById<Button>(R.id.getSolution)
        val task = resources.getStringArray(R.array.task)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, task)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            val solution = findViewById<TextView>(R.id.solution)
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when {
                    task[p2].toString() == "Сумма длины ребер" -> {
                        arg = "Сумма длины ребер"
                    }
                    task[p2].toString() == "Площадь поверхности" -> {
                        arg = "Площадь поверхности"
                    }
                    task[p2].toString() == "Объем" -> {
                        arg = "Объем"
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                solution.text = "Как же так"
            }

        }
        button.setOnClickListener {
            val a = findViewById<EditText>(R.id.sideA)
            val b = findViewById<EditText>(R.id.sideB)
            val c = findViewById<EditText>(R.id.sideC)
            when {
                a.text.trim().isNotEmpty() and b.text.trim().isNotEmpty() and c.text.trim()
                    .isNotEmpty() -> {
                    val valueA = a.text.toString().toInt()
                    val valueB = b.text.toString().toInt()
                    val valueC = c.text.toString().toInt()
                    viewModel.setvalue(valueA, valueB, valueC, arg).toString()
                }
                else -> {
                    viewModel.Err()
                }

            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.rez.observe(this, Observer { solution.text = viewModel.getSolution() })
    }
}