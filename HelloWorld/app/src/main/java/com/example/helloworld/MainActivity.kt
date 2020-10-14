package com.example.helloworld

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.helloworld.util.DiceHelper
import com.example.helloworld.viewModel.DiceViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: DiceViewModel

    //private lateinit var dice: IntArray
    //private lateinit var headlineText: String

    //lazy instantiation: initialize array when I first reference it in the rest of my code
    private val imageViews by lazy {
        arrayOf<ImageView>(result_image, result_image2, result_image3, result_image4, result_image5) }
    //var diceImage: ImageView? = null
    //private lateinit var diceImage: ImageView
    //private lateinit var diceImage2: ImageView

    private val headline by lazy {
        findViewById<TextView>(R.id.headline)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)
            .get(DiceViewModel::class.java)

        viewModel.headline.observe(this, androidx.lifecycle.Observer {
            headline.text = it
        })

        viewModel.dice.observe(this, androidx.lifecycle.Observer {
            updateDisplay(it)
        })

        /**
        headlineText = savedInstanceState?.getString(HEADLINE_TEXT)
            ?: getString(R.string.welcome)
        dice = savedInstanceState?.getIntArray(DICE_COLLECTION)
            ?: intArrayOf(1,2,3,4,5)
        */

        //setContentView((result_image))
        //diceImage = findViewById(R.id.result_image)
        //diceImage2 = findViewById(R.id.result_image2)

        val rollButton: Button = findViewById(R.id.roll_button)

        rollButton.setOnClickListener { viewModel.rollDice() }
        //headline.text = getString(R.string.welcome)
        //updateDisplay()

        lifecycle.addObserver(MyLifeCycleObserver())

        //for(imageView in imageViews) {
        //    imageView.setImageResource(R.drawable.dice_6)
        //}

        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            //Toast.makeText(this, "Replace with your own action",
            //Toast.LENGTH_LONG).show()
        }

        /**
        val countButton: Button = findViewById((R.id.add_button))
        countButton.setOnClickListener{ countUp() } */
    }

    /**
    private fun clickHandler() {
        dice = DiceHelper.rollDice()
        headlineText = DiceHelper.evaluateDice(this, dice)
        updateDisplay()
    }
    */

    private fun updateDisplay(dice: IntArray) {
        for (i in 0 until imageViews.size) {
            val drawableId = when (dice[i]) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                6 -> R.drawable.dice_6
                else -> R.drawable.dice_6
            }
            imageViews[i].setImageResource(drawableId)
        }
        //headline.text = headlineText
    }

    /**
    no longer need it since we have a viewModel
    override fun onSaveInstanceState(outState: Bundle) {
        outState?.putString(HEADLINE_TEXT, headlineText)
        outState?.putIntArray(DICE_COLLECTION, dice)
        super.onSaveInstanceState(outState)
    }
    */

    private fun rollDice() {

        var randomInt = (1..6).random()
        var randomInt2 = (1..6).random()

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

        //diceImage.setImageResource(drawableResource)
        //diceImage2.setImageResource(drawableResource2)

        //   Toast.makeText(this, "button clicked",
        //      Toast.LENGTH_SHORT).show()
    }
    /**
    private fun countUp() {
        val resultText: TextView = findViewById(R.id.result_image)
        val text = resultText.text

        // If text is the default "Hello World!" set that text to 1.
        if (text != "Hello World!"){
            // Otherwise, increment the number up to 6.
            // The text value in resultText.text is an instance of the CharSequence class;
            // it needs to be converted to a String object before it can be converted to an int.
            var resultInt = resultText.text.toString().toInt()

            if (resultInt < 6) {
                resultInt++
                resultText.text = resultInt.toString()
            }
        }
        else {
            resultText.text = "Hello World!"
        }
    }
    */
}


