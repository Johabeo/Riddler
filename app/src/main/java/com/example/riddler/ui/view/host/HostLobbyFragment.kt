package com.example.riddler.ui.view.host

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.riddler.R
import com.example.riddler.data.model.Lobby
import com.example.riddler.data.model.LobbyPlayers
import com.example.riddler.data.repo.GameRepository
import com.example.riddler.ui.adapters.PlayerAdapter
import com.example.riddler.ui.viewmodel.HostViewModel
import com.example.riddler.ui.viewmodel.PlayerViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HostLobbyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HostLobbyFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var createLobby: Button
    lateinit var startGame: Button

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
        return inflater.inflate(R.layout.fragment_host_lobby, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.playerList)
        val gamePin = view.findViewById<TextView>(R.id.lobbyGamePin)
        startGame = view.findViewById(R.id.lobbyStartGame)
        val numPlayers = view.findViewById<TextView>(R.id.num_players_in_lobby)
        val vm = ViewModelProvider(requireActivity()).get(HostViewModel::class.java)
        vm.callCreateLobby()

        vm.lobbyState.observe(viewLifecycleOwner) {
            try {
                numPlayers.text = "${it.players.size}/${it.size}"
                val adapter = PlayerAdapter(it.players)
                recyclerView.adapter = adapter
                recyclerView.setLayoutManager(LinearLayoutManager(context))
            } catch (e: Exception) {
                Timber.d(e)
            }

        }

        startGame.setOnClickListener {
            startGame.visibility = View.GONE
            vm.startGame{ loadSuccess -> loadGameFragment(loadSuccess) }
        }

        vm.pin.observe(viewLifecycleOwner) {
            gamePin.text = it
        }
    }

    private fun loadGameFragment(loadSuccess: Boolean) {
        if (loadSuccess) {
            parentFragmentManager.beginTransaction()
                .replace(R.id.hostContainer, HostGameFragment())
                .commit()
        } else {
            startGame.visibility = View.VISIBLE
        }
    }

}