package com.example.riddler.ui.view.player

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.riddler.R
import com.example.riddler.ui.adapters.PlayerAdapter
import com.example.riddler.ui.view.host.HostGameFragment
import com.example.riddler.ui.viewmodel.HostViewModel
import com.example.riddler.ui.viewmodel.PlayerViewModel
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlayerLobbyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayerLobbyFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_player_lobby, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.playerLobbyList)
        val gamePin = view.findViewById<TextView>(R.id.playerLobbyGamePin)

        vm.lobbyState.observe(viewLifecycleOwner) {
            try {
                println(it.gameStarted)
                if(it.gameStarted) {
                    vm.joinGame()
                    loadGameFragment()
                } else {
                    val adapter = PlayerAdapter(it.players)
                    recyclerView.adapter = adapter
                    recyclerView.setLayoutManager(LinearLayoutManager(context));
                }
            } catch (e: Exception) {
                println("No players")
            }
        }
        vm.pin.observe(viewLifecycleOwner) {
            gamePin.text = it
        }
    }

    private fun loadGameFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.playerContainer, PlayerGameFragment())
            .commit()
    }
}