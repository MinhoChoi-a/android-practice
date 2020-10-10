package com.example.helloworld.util

import android.content.Context
//import com.example.android.architecture.R
import com.example.helloworld.R
import kotlin.random.Random

class DiceHelper {

    //can be called from class itself, don't have to create an instance of the class
    companion object {

        private fun getDie(): Int {
            return (1..6).random()
        }

        fun rollDice(): IntArray {
            return intArrayOf(
                getDie(),
                getDie(),
                getDie(),
                getDie(),
                getDie()
            )
        }

        //context retrieves the resource from the app
        fun evaluateDice(context: Context, dice: IntArray?): String {

            val result = mutableMapOf(
                Pair(1, 0),
                Pair(2, 0),
                Pair(3, 0),
                Pair(4, 0),
                Pair(5, 0),
                Pair(6, 0)
            )

            //!! -> asserting dice is not null, smart cast to non-nullable array
            for (i in 0 until dice!!.size) {
                //if exceeded the array boundaries, instead return a value of zero
                val currentCount = result.getOrElse(dice[i]) { 0 }
                result[dice[i]] = currentCount + 1
            }

            return when {
                result.containsValue(5) ->
                    context.getString(R.string.five_of_a_kind)
                result.containsValue(4) ->
                    context.getString(R.string.four_of_a_kind)
                isFullHouse(result) ->
                    context.getString(R.string.full_house)
                isStraight(dice) ->
                    context.getString(R.string.straight)
                result.containsValue(3) ->
                    context.getString(R.string.three_of_a_kind)
                is2Pairs(result.values) ->
                    context.getString(R.string.two_pairs)
                result.containsValue(2) ->
                    context.getString(R.string.pair)
                else ->
                    context.getString(R.string.nothing_special)
            }
        }

        private fun isFullHouse(result: MutableMap<Int, Int>): Boolean {
            return result.containsValue(3) && result.containsValue(2)
        }

        private fun is2Pairs(values: MutableCollection<Int>): Boolean {
            var foundPair = false
            for (value in values) {
                if (value == 2) {
                    if (foundPair)
                        return true
                    else
                        foundPair = true
                }
            }
            return false
        }

        private fun isStraight(dice: IntArray): Boolean {
            return ((dice.contains(1) || dice.contains(6)) &&
                    dice.contains(2) &&
                    dice.contains(3) &&
                    dice.contains(4) &&
                    dice.contains(5))
        }

        }
    }


