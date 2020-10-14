package com.example.helloworld.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.helloworld.LOG_TAG
import com.example.helloworld.R
import com.example.helloworld.util.DiceHelper

class DiceViewModel(app: Application): AndroidViewModel(app) {

    val headline = MutableLiveData<String>()
    val dice = MutableLiveData<IntArray>()
    private val context = app


    init {
        Log.i(LOG_TAG, "VIEW MODEL CREATED")

        headline.value = context.getString(R.string.welcome)
        dice.value = intArrayOf(1,2,3,4,5)

    }

    fun rollDice() {
        dice.value = DiceHelper.rollDice()
        headline.value = DiceHelper.evaluateDice(context, dice.value)
    }
}