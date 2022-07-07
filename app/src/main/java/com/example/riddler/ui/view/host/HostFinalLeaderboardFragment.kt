package com.example.riddler.ui.view.host

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.riddler.R
import com.example.riddler.data.model.Player
import com.example.riddler.ui.viewmodel.HostViewModel
import com.example.riddler.ui.viewmodel.PlayerViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HostFinalLeaderboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HostFinalLeaderboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var vm: HostViewModel
    lateinit var first: TextView
    lateinit var second: TextView
    lateinit var third: TextView

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
        val view = inflater.inflate(R.layout.fragment_host_final_leaderboard, container, false)
        vm = ViewModelProvider(requireActivity()).get(HostViewModel::class.java)
        first = view.findViewById(R.id.first_place)
        second = view.findViewById(R.id.second_place)
        third = view.findViewById(R.id.third_place)

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.gameState.observe(viewLifecycleOwner) { state ->
            val topPlayers = state.players!!.sortedByDescending{it.score}
            setLeaderboard(topPlayers.take(3))
        }
    }

    fun setLeaderboard(playerList: List<Player>) {
        if (playerList.size >= 3) {
            third.text = playerList[2].playerName
        }
        if (playerList.size >= 2) {
            second.text = playerList[1].playerName
        }
        if (playerList.isNotEmpty()) {
            first.text = playerList[0].playerName
        }
    }

}