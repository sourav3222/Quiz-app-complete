package com.example.quiz

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import com.example.quiz.databinding.ActivityPlayBinding


class PlayActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayBinding
    var updateQuestion = 1

    val quizList = listOf<Quiz>(
        Quiz("victory day of Bangladesh", "15 september", "13 october", "16 December","18 january","16 December"),
        Quiz("How many minutes are in a full work", "12,211 minutes", "10,210 minutes", "10,311 minutes","10,080 minutes","10,080 minutes"),
        Quiz("Aureolin is shape of what a color?","Green","Yellow","Red","White","Yellow"),
        Quiz("What software company is headquatered in redmoned, whasington?","mycrosoft","google","facebook","jaba","mycrosoft"),
        Quiz("How many dots appear on a pair of dice?","32","31","52","42","42"),
        Quiz("What is acrophobia a fear of?","Height","Weight","age","name","Height"),
        Quiz(" What phone company produced the 3310? ","  Nokia","ViVo","MIUI","Readme","Nokia")



    )
    var index = 0
    var hasFinished = false
    var skip = -1
    var correct = 0
    var wrong = 0


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

            initQuestion()

            binding.nextBtn.setOnClickListener {


                showNextQuestion()

            }


        }




    private fun initQuestion() {
        val quizQuestion = quizList[index]

        binding.apply {

            questionTv.text = quizQuestion.question
            option1Btn.text = quizQuestion.option1
            option2Btn.text = quizQuestion.option2
            option3Btn.text = quizQuestion.option3
            option4Btn.text = quizQuestion.option4

        }


    }

    private fun showNextQuestion() {
        checkAnswer()


        binding.apply {

            if (updateQuestion < quizList.size) {
                updateQuestion++
                initQuestion()
            } else if (index <= quizList.size - 1) {
                index++
            } else {
                hasFinished = true
            }
            radioGroup.clearCheck()

        }


    }





    private fun checkAnswer() {
        binding.apply {

            if (radioGroup.checkedRadioButtonId == -1) {

                skip++
            } else {
                val checkButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
                val checkAnswer = checkButton.text.toString()

                if (checkAnswer == quizList[index].answer) {

                    correct++
                    showAlertDialouge("Correct Answer")

                } else {
                    wrong++
                    showAlertDialouge("Wrong Answer")
                }


            }

            if (index <= quizList.size - 1) {
                index++
            } else {
                showAlertDialouge("Finished")
            }


        }

    }

    fun showAlertDialouge(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(message)
        builder.setPositiveButton("ok", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {


                if (message == "Finished") {
                    val intent = Intent(this@PlayActivity, ResultActivity::class.java)
                    intent.putExtra("skip", skip)
                    intent.putExtra("correct", correct)
                    intent.putExtra("wrong", wrong)



                    startActivity(intent)

                }

            }


        })

        var alertDialog = builder.create()
        alertDialog.show()


    }

}
