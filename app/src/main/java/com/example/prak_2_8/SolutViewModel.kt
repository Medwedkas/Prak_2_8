package com.example.prak_2_8

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SolutViewModel : ViewModel() {
    val rez: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun setvalue(a: Int, b: Int, c: Int, task: String) {
        when (task) {
            "Сумма длины ребер" -> {
                rez.value = ((a * 4) + (b * 4) + (c * 4)).toString()
            }
            "Площадь поверхности" -> {
                rez.value = (2 * (a * b + a * c + b * c)).toString()
            }
            "Объем" -> {
                rez.value = (a * b * c).toString()
            }
        }
    }

    fun Err(){
        rez.value = "Ошибка ввода"
    }

    fun getSolution(): String {
        return rez.value.toString()
    }

}