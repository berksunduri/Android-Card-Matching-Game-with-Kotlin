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

class game6x6 : AppCompatActivity() {
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
    private lateinit var button17: ImageButton
    private lateinit var button18: ImageButton
    private lateinit var button19: ImageButton
    private lateinit var button20: ImageButton
    private lateinit var button21: ImageButton
    private lateinit var button22: ImageButton
    private lateinit var button23: ImageButton
    private lateinit var button24: ImageButton
    private lateinit var button25: ImageButton
    private lateinit var button26: ImageButton
    private lateinit var button27: ImageButton
    private lateinit var button28: ImageButton
    private lateinit var button29: ImageButton
    private lateinit var button30: ImageButton
    private lateinit var button31: ImageButton
    private lateinit var button32: ImageButton
    private lateinit var button33: ImageButton
    private lateinit var button34: ImageButton
    private lateinit var button35: ImageButton
    private lateinit var button36: ImageButton

    private lateinit var scoreText: TextView
    private var puan: Int = 0
    private lateinit var timerText: TextView

    private lateinit var omMediaPlayer: MediaPlayer
    private lateinit var gfMediaPlayer: MediaPlayer
    private lateinit var tfMediaPlayer: MediaPlayer

    private val numImages = 18 // EDIT total number of images we need to download
    private val numImagesReady =
        AtomicInteger(0) // EDIT count of how many images are currently ready

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game6x6)

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
        val min = 1
        val max = 45
        val database = FirebaseDatabase.getInstance()
        var imageID1 = Random().nextInt(maxGryffindor - minGryffindor + 1) + minGryffindor
        var imageID2 = Random().nextInt(maxGryffindor - minGryffindor + 1) + minGryffindor
        var imageID3 = Random().nextInt(maxGryffindor - minGryffindor + 1) + minGryffindor
        var imageID4 = Random().nextInt(maxGryffindor - minGryffindor + 1) + minGryffindor
        if (imageID1 == imageID2) {
            if (imageID2 == 11) {
                imageID2 -= 1
            } else {
                imageID2 += 1
            }
        }
        if (imageID2 == imageID3) {
            if (imageID3 == 11) {
                imageID3 -= 1
            } else {
                imageID3 += 1
            }
        }
        if (imageID3 == imageID4) {
            if (imageID4 == 11) {
                imageID4 -= 1
            } else {
                imageID4 += 1
            }
        }
        if (imageID1 == imageID3) {
            if (imageID3 == 11) {
                imageID3 -= 1
            } else {
                imageID3 += 1
            }
        }
        if (imageID1 == imageID4) {
            if (imageID4 == 11) {
                imageID4 -= 1
            } else {
                imageID2 += 1
            }
        }
        if (imageID2 == imageID4) {
            if (imageID4 == 11) {
                imageID4 -= 1
            } else {
                imageID4 += 1
            }
        }
        var imageID5 = Random().nextInt(maxHufflepuff - minHufflepuff + 1) + minHufflepuff
        var imageID6 = Random().nextInt(maxHufflepuff - minHufflepuff + 1) + minHufflepuff
        var imageID7 = Random().nextInt(maxHufflepuff - minHufflepuff + 1) + minHufflepuff
        var imageID8 = Random().nextInt(maxHufflepuff - minHufflepuff + 1) + minHufflepuff
        if (imageID5 == imageID6) {
            if (imageID6 == 22) {
                imageID6 -= 1
            } else {
                imageID6 += 1
            }
        }
        if (imageID6 == imageID7) {
            if (imageID7 == 22) {
                imageID7 -= 1
            } else {
                imageID7 += 1
            }
        }
        if (imageID7 == imageID8) {
            if (imageID8 == 22) {
                imageID8 -= 1
            } else {
                imageID8 += 1
            }
        }
        if (imageID5 == imageID7) {
            if (imageID7 == 22) {
                imageID7 -= 1
            } else {
                imageID7 += 1
            }
        }
        if (imageID6 == imageID8) {
            if (imageID8 == 22) {
                imageID8 -= 1
            } else {
                imageID8 += 1
            }
        }
        if (imageID6 == imageID8) {
            if (imageID8 == 22) {
                imageID8 -= 1
            } else {
                imageID8 += 1
            }
        }
        var imageID9 = Random().nextInt(maxRavenclaw - minRavenclaw + 1) + minRavenclaw
        var imageID10 = Random().nextInt(maxRavenclaw - minRavenclaw + 1) + minRavenclaw
        var imageID11 = Random().nextInt(maxRavenclaw - minRavenclaw + 1) + minRavenclaw
        var imageID12 = Random().nextInt(maxRavenclaw - minRavenclaw + 1) + minRavenclaw
        var imageID13 = Random().nextInt(maxRavenclaw - minRavenclaw + 1) + minRavenclaw
        if (imageID9 == imageID10) {
            if (imageID10 == 33) {
                imageID10 -= 1
            } else {
                imageID10 += 1
            }
        }
        if (imageID10 == imageID11) {
            if (imageID11 == 33) {
                imageID11 -= 1
            } else {
                imageID11 += 1
            }
        }
        if (imageID11 == imageID12) {
            if (imageID12 == 33) {
                imageID12 -= 1
            } else {
                imageID12 += 1
            }
        }
        if (imageID12 == imageID13) {
            if (imageID13 == 33) {
                imageID13 -= 1
            } else {
                imageID13 += 1
            }
        }
        if (imageID9 == imageID11) {
            if (imageID11 == 33) {
                imageID11 -= 1
            } else {
                imageID11 += 1
            }
        }
        if (imageID9 == imageID12) {
            if (imageID12 == 33) {
                imageID12 -= 1
            } else {
                imageID12 += 1
            }
        }
        if (imageID9 == imageID13) {
            if (imageID13 == 33) {
                imageID13 -= 1
            } else {
                imageID13 += 1
            }
        }
        if (imageID10 == imageID12) {
            if (imageID12 == 33) {
                imageID12 -= 1
            } else {
                imageID12 += 1
            }
        }
        if (imageID10 == imageID13) {
            if (imageID13 == 33) {
                imageID13 -= 1
            } else {
                imageID13 += 1
            }
        }
        if (imageID11 == imageID13) {
            if (imageID13 == 33) {
                imageID13 -= 1
            } else {
                imageID13 += 1
            }
        }
        var imageID14 = Random().nextInt(maxSlytherin - minSlytherin + 1) + minSlytherin
        var imageID15 = Random().nextInt(maxSlytherin - minSlytherin + 1) + minSlytherin
        var imageID16 = Random().nextInt(maxSlytherin - minSlytherin + 1) + minSlytherin
        var imageID17 = Random().nextInt(maxSlytherin - minSlytherin + 1) + minSlytherin
        var imageID18 = Random().nextInt(maxSlytherin - minSlytherin + 1) + minSlytherin
        if (imageID14 == imageID15) {
            if (imageID15 == 44) {
                imageID15 -= 1
            } else {
                imageID15 += 1
            }
        }
        if (imageID15 == imageID16) {
            if (imageID16 == 44) {
                imageID16 -= 1
            } else {
                imageID16 += 1
            }
        }
        if (imageID16 == imageID17) {
            if (imageID17 == 44) {
                imageID17 -= 1
            } else {
                imageID17 += 1
            }
        }
        if (imageID17 == imageID18) {
            if (imageID18 == 44) {
                imageID18 -= 1
            } else {
                imageID18 += 1
            }
        }
        if (imageID14 == imageID16) {
            if (imageID16 == 44) {
                imageID16 -= 1
            } else {
                imageID16 += 1
            }
        }
        if (imageID14 == imageID17) {
            if (imageID17 == 44) {
                imageID17 -= 1
            } else {
                imageID17 += 1
            }
        }
        if (imageID14 == imageID18) {
            if (imageID18 == 44) {
                imageID18 -= 1
            } else {
                imageID18 += 1
            }
        }
        if (imageID15 == imageID17) {
            if (imageID17 == 44) {
                imageID17 -= 1
            } else {
                imageID17 += 1
            }
        }
        if (imageID15 == imageID18) {
            if (imageID18 == 44) {
                imageID18 -= 1
            } else {
                imageID18 += 1
            }
        }
        if (imageID16 == imageID18) {
            if (imageID18 == 44) {
                imageID18 -= 1
            } else {
                imageID18 += 1
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
        val lDatabase = FirebaseStorage.getInstance().getReference("all/$imageID9.jpg")
        val zDatabase = FirebaseStorage.getInstance().getReference("all/$imageID10.jpg")
        val xDatabase = FirebaseStorage.getInstance().getReference("all/$imageID11.jpg")
        val cDatabase = FirebaseStorage.getInstance().getReference("all/$imageID12.jpg")
        val vDatabase = FirebaseStorage.getInstance().getReference("all/$imageID13.jpg")
        val bDatabase = FirebaseStorage.getInstance().getReference("all/$imageID14.jpg")
        val nDatabase = FirebaseStorage.getInstance().getReference("all/$imageID15.jpg")
        val mDatabase = FirebaseStorage.getInstance().getReference("all/$imageID16.jpg")
        val qDatabase = FirebaseStorage.getInstance().getReference("all/$imageID17.jpg")
        val wDatabase = FirebaseStorage.getInstance().getReference("all/$imageID18.jpg")
        scoreText = findViewById(R.id.score6)
        scoreText.text = "Score:" + puan

        timerText = findViewById(R.id.textTimer6)

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
        button17 = findViewById(R.id.imageButton25)
        button18 = findViewById(R.id.imageButton5)
        button19 = findViewById(R.id.imageButton23)
        button20 = findViewById(R.id.imageButton22)
        button21 = findViewById(R.id.imageButton21)
        button22 = findViewById(R.id.imageButton31)
        button23 = findViewById(R.id.imageButton26)
        button24 = findViewById(R.id.imageButton27)
        button25 = findViewById(R.id.imageButton28)
        button26 = findViewById(R.id.imageButton29)
        button27 = findViewById(R.id.imageButton32)
        button28 = findViewById(R.id.imageButton33)
        button29 = findViewById(R.id.imageButton34)
        button30 = findViewById(R.id.imageButton35)
        button31 = findViewById(R.id.imageButton20)
        button32 = findViewById(R.id.imageButton24)
        button33 = findViewById(R.id.imageButton30)
        button34 = findViewById(R.id.imageButton8)
        button35 = findViewById(R.id.imageButton12)
        button36 = findViewById(R.id.imageButton7)
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
            button16,
            button17,
            button18,
            button19,
            button20,
            button21,
            button22,
            button23,
            button24,
            button25,
            button26,
            button27,
            button28,
            button29,
            button30,
            button31,
            button32,
            button33,
            button34,
            button35,
            button36
        )

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
        try {
            val localfile = File.createTempFile("tempfile8", ".jpg")
            lDatabase.getFile(localfile).addOnSuccessListener {
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
            val localfile = File.createTempFile("tempfile9", ".jpg")
            zDatabase.getFile(localfile).addOnSuccessListener {
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
            val localfile = File.createTempFile("tempfile10", ".jpg")
            xDatabase.getFile(localfile).addOnSuccessListener {
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
            val localfile = File.createTempFile("tempfile11", ".jpg")
            cDatabase.getFile(localfile).addOnSuccessListener {
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
            val localfile = File.createTempFile("tempfile12", ".jpg")
            vDatabase.getFile(localfile).addOnSuccessListener {
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
            val localfile = File.createTempFile("tempfile13", ".jpg")
            bDatabase.getFile(localfile).addOnSuccessListener {
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
            val localfile = File.createTempFile("tempfile14", ".jpg")
            nDatabase.getFile(localfile).addOnSuccessListener {
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
            val localfile = File.createTempFile("tempfile15", ".jpg")
            mDatabase.getFile(localfile).addOnSuccessListener {
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
            val localfile = File.createTempFile("tempfile16", ".jpg")
            qDatabase.getFile(localfile).addOnSuccessListener {
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
            val localfile = File.createTempFile("tempfile17", ".jpg")
            wDatabase.getFile(localfile).addOnSuccessListener {
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
        object : CountDownTimer(50000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timerText.text = "Time: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                timerText.text = "done!"
                buttons.forEach {
                    it.isEnabled = false
                }
                Toast.makeText(this@game6x6, "Game Finished", Toast.LENGTH_LONG).show()
                tfMediaPlayer.start()

            }
        }.start()
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