package com.example.riddler.ui.view.player

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val answer1 = view.findViewById<TextView>(R.id.playerAnswer1Text)
        val answer2 = view.findViewById<TextView>(R.id.playerAnswer2Text)
        val answer3 = view.findViewById<TextView>(R.id.playerAnswer3Text)
        val answer4 = view.findViewById<TextView>(R.id.playerAnswer4Text)
        val timerText = view.findViewById<TextView>(R.id.timer)
        vm.gameState.observe(viewLifecycleOwner) {
            question.text = it.question?.question
            answer1.text = it.question?.firstAnswer
            answer2.text = it.question?.secondAnswer
            answer3.text = it.question?.thirdAnswer
            answer4.text = it.question?.fourthAnswer
        }
        val timerT = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerText.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                timerText.text = "0"
            }
        }.start()
    }

}