package com.example.riddler.ui.view.host

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.riddler.R
import com.example.riddler.ui.adapters.LeaderboardAdapter
import com.example.riddler.ui.adapters.PlayerAdapter
import com.example.riddler.ui.viewmodel.HostViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HostLeaderboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HostLeaderboardFragment : Fragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_host_leaderboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val next = view.findViewById<Button>(R.id.hostLeaderboardNext)
        val recyclerView = view.findViewById<RecyclerView>(R.id.hostLeaderboard)
        val vm = ViewModelProvider(requireActivity()).get(HostViewModel::class.java)

        vm.gameState.observe(viewLifecycleOwner) { state ->
            val topPlayers = state.players!!.sortedByDescending{it.score}
            val adapter = LeaderboardAdapter(topPlayers.take(5))
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context);
        }

        next.setOnClickListener {
            next.visibility = View.GONE
            vm.callNextQuestion { next() }
        }
    }

    fun next() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.hostContainer, HostGameFragment())
            .commit()
    }
}