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
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

class game4x4 : AppCompatActivity() {
    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<MemoryCard>

    //private lateinit var bitmapArray: ArrayList<Bitmap>
    private val bitmapArray = mutableListOf<Bitmap>()
    private var indexOfSingleSelectedCard: Int? = null
    private lateinit var button1: ImageButton
    private lateinit var button2: ImageButton
    private lateinit var button3: ImageButton
    private lateinit var button4: ImageButton
    private lateinit var button5: ImageButton
    private lateinit var button6: ImageButton
    private lateinit var button7: ImageButton
    private lateinit var button8: ImageButton
    private lateinit var button9: ImageButton
    private lateinit var button10: ImageButton
    private lateinit var button11: ImageButton
    private lateinit var button12: ImageButton
    private lateinit var button13: ImageButton
    private lateinit var button14: ImageButton
    private lateinit var button15: ImageButton
    private lateinit var button16: ImageButton
    private lateinit var scoreText: TextView
    private lateinit var timerText: TextView
    private var puan: Int = 0

    private lateinit var omMediaPlayer: MediaPlayer
    private lateinit var gfMediaPlayer: MediaPlayer
    private lateinit var tfMediaPlayer: MediaPlayer

    private val numImages = 8 // total number of images we need to download
    private val numImagesReady = AtomicInteger(0) //  how many images are currently ready
    var counter: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game4x4)

        omMediaPlayer = MediaPlayer.create(this, R.raw.on_match)
        gfMediaPlayer = MediaPlayer.create(this, R.raw.game_finish)
        tfMediaPlayer = MediaPlayer.create(this, R.raw.time_finish)

        val minGryffindor = 1
        val maxGryffindor = 12
        val minHufflepuff = 12
        val maxHufflepuff = 23
        val minRavenclaw = 23
        val maxRavenclaw = 34
        val minSlytherin = 34
        val maxSlytherin = 45
        //val min = 1
        //val max = 45
        var imageID1 = Random().nextInt(maxGryffindor - minGryffindor + 1) + minGryffindor
        var imageID2 = Random().nextInt(maxGryffindor - minGryffindor + 1) + minGryffindor
        if (imageID1 == imageID2) {
            if (imageID2 == 11) {
                imageID2 -= 1
            } else {
                imageID2 += 1
            }
        }
        var imageID3 = Random().nextInt(maxHufflepuff - minHufflepuff + 1) + minHufflepuff
        var imageID4 = Random().nextInt(maxHufflepuff - minHufflepuff + 1) + minHufflepuff
        if (imageID3 == imageID4) {
            if (imageID4 == 22) {
                imageID4 -= 1
            } else {
                imageID4 += 1
            }
        }
        var imageID5 = Random().nextInt(maxRavenclaw - minRavenclaw + 1) + minRavenclaw
        var imageID6 = Random().nextInt(maxRavenclaw - minRavenclaw + 1) + minRavenclaw
        if (imageID5 == imageID6) {
            if (imageID6 == 33) {
                imageID6 -= 1
            } else {
                imageID6 += 1
            }
        }
        var imageID7 = Random().nextInt(maxSlytherin - minSlytherin + 1) + minSlytherin
        var imageID8 = Random().nextInt(maxSlytherin - minSlytherin + 1) + minSlytherin
        if (imageID7 == imageID8) {
            if (imageID8 == 44) {
                imageID8 -= 1
            } else {
                imageID8 += 1
            }
        }
        val aDatabase = FirebaseStorage.getInstance().getReference("all/$imageID1.jpg")
        val sDatabase = FirebaseStorage.getInstance().getReference("all/$imageID2.jpg")
        val dDatabase = FirebaseStorage.getInstance().getReference("all/$imageID3.jpg")
        val fDatabase = FirebaseStorage.getInstance().getReference("all/$imageID4.jpg")
        val gDatabase = FirebaseStorage.getInstance().getReference("all/$imageID5.jpg")
        val hDatabase = FirebaseStorage.getInstance().getReference("all/$imageID6.jpg")
        val jDatabase = FirebaseStorage.getInstance().getReference("all/$imageID7.jpg")
        val kDatabase = FirebaseStorage.getInstance().getReference("all/$imageID8.jpg")

        scoreText = findViewById(R.id.score4)
        scoreText.text = "Score:" + puan

        timerText = findViewById(R.id.textTimer4)
        timerText.text = "Time:" + timerText


        button1 = findViewById(R.id.imageButton1)
        button2 = findViewById(R.id.imageButton2)
        button3 = findViewById(R.id.imageButton3)
        button4 = findViewById(R.id.imageButton4)
        button5 = findViewById(R.id.imageButton)
        button6 = findViewById(R.id.imageButton6)
        button7 = findViewById(R.id.imageButton17)
        button8 = findViewById(R.id.imageButton18)
        button9 = findViewById(R.id.imageButton9)
        button10 = findViewById(R.id.imageButton10)
        button11 = findViewById(R.id.imageButton11)
        button12 = findViewById(R.id.imageButton19)
        button13 = findViewById(R.id.imageButton13)
        button14 = findViewById(R.id.imageButton14)
        button15 = findViewById(R.id.imageButton15)
        button16 = findViewById(R.id.imageButton16)
        buttons = listOf(
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9,
            button10,
            button11,
            button12,
            button13,
            button14,
            button15,
            button16
        )

        // EDIT disable buttons until all images are ready
        buttons.forEach {
            it.isEnabled = false
        }
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
        try {
            val localfile = File.createTempFile("tempfile2", ".jpg")
            dDatabase.getFile(localfile).addOnSuccessListener {
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
            val localfile = File.createTempFile("tempfile3", ".jpg")
            fDatabase.getFile(localfile).addOnSuccessListener {
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
        try {
            val localfile = File.createTempFile("tempfile4", ".jpg")
            gDatabase.getFile(localfile).addOnSuccessListener {
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
            val localfile = File.createTempFile("tempfile5", ".jpg")
            hDatabase.getFile(localfile).addOnSuccessListener {
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
        try {
            val localfile = File.createTempFile("tempfile6", ".jpg")
            jDatabase.getFile(localfile).addOnSuccessListener {
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
            val localfile = File.createTempFile("tempfile7", ".jpg")
            kDatabase.getFile(localfile).addOnSuccessListener {
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

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener(View.OnClickListener {
                //UPDATE MODELS
                updateModels(index)
                //UPDATE VIEWS
                updateViews()
            })
        }

        object : CountDownTimer(50000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timerText.text = "Time: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                timerText.text = "done!"
                buttons.forEach {
                    it.isEnabled = false
                }
                Toast.makeText(this@game4x4, "Game Finished", Toast.LENGTH_LONG).show()
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