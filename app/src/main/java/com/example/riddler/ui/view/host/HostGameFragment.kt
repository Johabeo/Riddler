package com.example.riddler.ui.view.host

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.riddler.R
import com.example.riddler.ui.viewmodel.HostViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HostGameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_host_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val question = view.findViewById<TextView>(R.id.hostQuestions)
        val playersSubmitted = view.findViewById<TextView>(R.id.playersSubmitted)
        val next = view.findViewById<Button>(R.id.hostGameNext)
        val vm = ViewModelProvider(requireActivity()).get(HostViewModel::class.java)
        vm.gameState.observe(viewLifecycleOwner) {
            question.text = it.question?.question
            playersSubmitted.text = "${it.numAnswered.toString()}/${it.players!!.size}"

        }

        next.setOnClickListener {
            next.visibility = View.GONE
            vm.moveNext { gameFinished -> displayLeaderboard(gameFinished) }
            next.visibility = View.VISIBLE
        }
    }

    fun displayLeaderboard(gameFinished: Boolean) {
        if (gameFinished) {
            parentFragmentManager.beginTransaction()
                .replace(R.id.hostContainer, HostFinalLeaderboardFragment())
                .commit()
        } else {
            parentFragmentManager.beginTransaction()
                .replace(R.id.hostContainer, HostLeaderboardFragment())
                .commit()
        }
    }

}