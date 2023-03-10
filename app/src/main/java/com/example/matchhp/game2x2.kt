package com.example.matchhp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

class game2x2 : AppCompatActivity() {

    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<MemoryCard>

    //private lateinit var bitmapArray: ArrayList<Bitmap>
    private val bitmapArray = mutableListOf<Bitmap>()
    private var indexOfSingleSelectedCard: Int? = null
    private lateinit var button1: ImageButton
    private lateinit var button2: ImageButton
    private lateinit var button3: ImageButton
    private lateinit var button4: ImageButton
    private lateinit var scoreText: TextView
    private lateinit var timerText: TextView
    private var puan: Int = 0

    private lateinit var omMediaPlayer: MediaPlayer
    private lateinit var gfMediaPlayer: MediaPlayer
    private lateinit var tfMediaPlayer: MediaPlayer

    private val numImages = 2 // EDIT total number of images we need to download
    private val numImagesReady =
        AtomicInteger(0) // EDIT count of how many images are currently ready

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game2x2)

        omMediaPlayer = MediaPlayer.create(this, R.raw.on_match)
        gfMediaPlayer = MediaPlayer.create(this, R.raw.game_finish)
        tfMediaPlayer = MediaPlayer.create(this, R.raw.time_finish)

        timerText = findViewById(R.id.textTimer2)
        timerText.text = "Time:" + timerText


        val min = 1
        val max = 45
        val database = FirebaseDatabase.getInstance()
        var imageID1 = Random().nextInt(max - min + 1) + min
        var imageID2 = Random().nextInt(max - min + 1) + min
        if (imageID1 == imageID2) {
            if (imageID2 == 44) {
                imageID2 -= imageID2
            } else {
                imageID2 += imageID2
            }
        }
        val aDatabase = FirebaseStorage.getInstance().getReference("all/$imageID1.jpg")
        val sDatabase = FirebaseStorage.getInstance().getReference("all/$imageID2.jpg")
        scoreText = findViewById(R.id.score)
        scoreText.text = "Score:" + puan
        button1 = findViewById(R.id.imageButton1)
        button2 = findViewById(R.id.imageButton2)
        button3 = findViewById(R.id.imageButton3)
        button4 = findViewById(R.id.imageButton4)
        buttons = listOf(button1, button2, button3, button4)

// EDIT disable buttons until all images are ready
        buttons.forEach {
            it.isEnabled = false
        }

        //upload.setOnClickListener(View.OnClickListener {
        try {
            val localfile = File.createTempFile("tempfile", ".jpg")
            aDatabase.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                bitmapArray.add(bitmap)


                // EDIT add the image twice here instead of duplicating later
                bitmapArray.add(bitmap)

                // EDIT count this image as ready
                val totalImagesReady = numImagesReady.incrementAndGet()

                // EDIT once all images are ready, shuffle and enable the buttons
                if (totalImagesReady == numImages) {
                    bitmapArray.shuffle()
                    buttons.forEach { it.isEnabled = true }
                    cards = buttons.indices.map { index ->
                        MemoryCard(bitmapArray[index])
                    }
                }


            }.addOnFailureListener {
                Log.w("myapplication", "ERROR RETRIEVING IMAGE")
                Toast.makeText(this, "ERROR RETRIEVING IMAGE", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            // SUGGESTION especially if this will be implemented 8x8, you might want to try implementing this in a loop instead of duplicating code
            val localfile = File.createTempFile("tempfile1", ".jpg")
            sDatabase.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                bitmapArray.add(bitmap)

                // EDIT add the image twice here instead of duplicating later
                bitmapArray.add(bitmap)

                // EDIT count this image as ready
                val totalImagesReady = numImagesReady.incrementAndGet()

                // EDIT once all images are ready, shuffle and enable the buttons
                if (totalImagesReady == numImages) {
                    bitmapArray.shuffle()
                    buttons.forEach { it.isEnabled = true }
                    cards = buttons.indices.map { index ->
                        MemoryCard(bitmapArray[index])
                    }
                }


            }.addOnFailureListener {
                Log.w("myapplication", "ERROR RETRIEVING IMAGE")
                Toast.makeText(this, "ERROR RETRIEVING IMAGE", Toast.LENGTH_SHORT).show()
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }


// EDIT moved            ///    DUPLICATE
// EDIT refactor         bitmapArray.addAll(bitmapArray)
// EDIT moved            ///SHUFFLE
// EDIT moved            bitmapArray.shuffle()
// EDIT remove           Log.w("myapplication", bitmapArray.size.toString())
        // })

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener(View.OnClickListener {
                //UPDATE MODELS
                updateModels(index)
                //UPDATE VIEWS
                updateViews()
            })
        }

        object : CountDownTimer(12000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timerText.text = "Time: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                timerText.text = "done!"
                buttons.forEach {
                    it.isEnabled = false
                }
                Toast.makeText(this@game2x2, "Game Finished", Toast.LENGTH_LONG).show()
                tfMediaPlayer.start()
            }
        }.start()
    }

    private fun updateViews() {
        cards.forEachIndexed { index, card ->
            val button = buttons[index]
            if (card.isMatched) {
                button.alpha = 0.3f
            }
            if (card.isFaceUp) {
                button.setImageBitmap(card.identifier)
            } else {
                button.setImageResource(R.drawable.basecard)
            }
        }
    }

    private fun updateModels(position: Int) {
        val card = cards[position]
        // error check
        if (card.isFaceUp) {
            Toast.makeText(this, "Wrong Move!", Toast.LENGTH_SHORT).show()
            return
        }
        // 3 case
        // 0 card flipped ->flip selected
        // 1 card flipped -> flip selected+ check if image same
        // 2 card flipped -> restore card + flip selected
        if (indexOfSingleSelectedCard == null) {
            //0 or 2 selected
            restoreCards()
            indexOfSingleSelectedCard = position
        } else {
            //1 card selected
            checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
    }

    private fun restoreCards() {
        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }

    private fun checkForMatch(position1: Int, position2: Int) {
        if (cards[position1].identifier == cards[position2].identifier) {
            Toast.makeText(this, "Match Found!", Toast.LENGTH_SHORT).show()
            cards[position1].isMatched = true
            cards[position2].isMatched = true
            omMediaPlayer.start()
            puan += 1
            scoreText.text = "Score: " + puan
        }
    }
}