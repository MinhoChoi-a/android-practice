package com.example.helloworld.util

import android.content.Context
import com.example.android.architecture.R
import com.example.helloworld.R
import kotlin.random.Random

class DiceHelper {

    //can be called from class itself, don't have to create an instance of the class
    companion object {

        private fun getDie(): Int {
            return (1..6).random()}
    }

    fun rollDice(): IntArray {
        return intArrayOf(
            getDie(),
            getDie()
        )
    }

    //context retrieves the resource from the app
    fun evaluateDice(context: Context, dice: IntArray?): String {

        val result = mutableMapOf(
            Pair(1,0),
            Pair(2,0),
            Pair(3,0),
            Pair(4,0),
            Pair(5,0),
            Pair(6,0)
        )

        //!! -> asserting dice is not null, smart cast to non-nullable array
        for(i in 0 until dice!!.size) {
            //if exceeded the array boundaries, instead return a value of zero
            val currentCount = result.getOrElse(dice[i]) {0}
            result[dice[i]] = currentCount + 1
        }

        return when {
            result.containsValue(5) ->
                context.getString(R.string.fi)
        }

    }


    val drawableResource = when (randomInt) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    val drawableResource2 = when (randomInt2) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    diceImage.setImageResource(drawableResource)
    diceImage2.setImageResource(drawableResource2)

}