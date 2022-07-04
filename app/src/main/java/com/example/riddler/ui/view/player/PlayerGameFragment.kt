package com.example.riddler.ui.view.player

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.riddler.R
import com.example.riddler.ui.viewmodel.PlayerViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PlayerGameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val TIME_LIMIT = 30
    private val vm: PlayerViewModel by activityViewModels()
    private lateinit var answer1: TextView
    private lateinit var answer2: TextView
    private lateinit var answer3: TextView
    private lateinit var answer4: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val question = view.findViewById<TextView>(R.id.playerQuestions)
        answer1 = view.findViewById<TextView>(R.id.playerAnswer1Text)
        answer2 = view.findViewById<TextView>(R.id.playerAnswer2Text)
        answer3 = view.findViewById<TextView>(R.id.playerAnswer3Text)
        answer4 = view.findViewById<TextView>(R.id.playerAnswer4Text)
        val timerText = view.findViewById<TextView>(R.id.timer)
        disableAnswerClick()

        vm.gameState.observe(viewLifecycleOwner) {
            question.text = it.question?.question
            answer1.text = it.question?.firstAnswer
            answer2.text = it.question?.secondAnswer
            answer3.text = it.question?.thirdAnswer
            answer4.text = it.question?.fourthAnswer
            enableAnswerClick()
        }
        val timerT = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerText.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                timerText.text = "0"
            }
        }.start()

        answer1.setOnClickListener {
            disableAnswerClick()
            vm.submitAnswer(answer1.text.toString()) { isCorrect -> moveToResult(isCorrect) }
        }
        answer2.setOnClickListener {
            disableAnswerClick()
            vm.submitAnswer(answer2.text.toString()) { isCorrect -> moveToResult(isCorrect) }
        }
        answer3.setOnClickListener {
            disableAnswerClick()
            vm.submitAnswer(answer3.text.toString()) { isCorrect -> moveToResult(isCorrect) }
        }
        answer4.setOnClickListener {
            disableAnswerClick()
            vm.submitAnswer(answer4.text.toString()) { isCorrect -> moveToResult(isCorrect) }
        }
    }

    fun moveToResult(isCorrect: Boolean) {
        if (isCorrect) {
            parentFragmentManager.beginTransaction()
                .replace(R.id.playerContainer, PlayerCorrectFragment())
                .commit()
        } else {
            parentFragmentManager.beginTransaction()
                .replace(R.id.playerContainer, PlayerIncorrectFragment())
                .commit()
        }
    }

    fun disableAnswerClick() {
        answer1.isClickable = false
        answer2.isClickable = false
        answer3.isClickable = false
        answer3.isClickable = false
    }

    fun enableAnswerClick() {
        answer1.isClickable = true
        answer2.isClickable = true
        answer3.isClickable = true
        answer4.isClickable = true
    }


}