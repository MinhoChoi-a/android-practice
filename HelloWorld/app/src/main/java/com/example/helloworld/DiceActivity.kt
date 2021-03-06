package com.example.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.example.helloworld.viewModel.DiceViewModel
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_dice.*

import kotlinx.android.synthetic.main.content_dice.*


class DiceActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_dice)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_settings)

        viewModel = ViewModelProviders.of(this)
            .get(DiceViewModel::class.java)

        viewModel.headline.observe(this, androidx.lifecycle.Observer {
            headline.text = it
        })

        viewModel.dice.observe(this, androidx.lifecycle.Observer {
            updateDisplay(it)
        })

        lifecycle.addObserver(MyLifeCycleObserver())

        val configChange = savedInstanceState?.getBoolean(CONFIG_CHANGE)
            ?: false
        if(configChange.not()) viewModel.rollDice()

        /**
        headlineText = savedInstanceState?.getString(HEADLINE_TEXT)
            ?: getString(R.string.welcome)
        dice = savedInstanceState?.getIntArray(DICE_COLLECTION)
            ?: intArrayOf(1,2,3,4,5)
        */

        //setContentView((result_image))
        //diceImage = findViewById(R.id.result_image)
        //diceImage2 = findViewById(R.id.result_image2)

        //val rollButton: Button = findViewById(R.id.roll_button)


        //after we different main launcher
        //rollButton.setOnClickListener { viewModel.rollDice() }
        //headline.text = getString(R.string.welcome)
        //updateDisplay()



        //for(imageView in imageViews) {
        //    imageView.setImageResource(R.drawable.dice_6)
        //}

        /**
        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            //Toast.makeText(this, "Replace with your own action",
            //Toast.LENGTH_LONG).show()
        }
        */
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

    //another way to use onSaveInstanceState, but don't need it the viewModel
    //override fun onSaveInstanceState(outState: Bundle) {
      //  outState?.putBoolean(CONFIG_CHANGE, true)
        //super.onSaveInstanceState(outState)
    //}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_idce, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            R.id.action_share -> shareResult()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareResult(): Boolean {

        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,
                "I rolled the dice: ${viewModel.headline.value}")
            type = "text/plain"
        }
        startActivity(intent)
        return true
    }


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


