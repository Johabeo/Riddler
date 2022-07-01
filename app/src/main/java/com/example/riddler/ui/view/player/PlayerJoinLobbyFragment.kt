package com.example.riddler.ui.view.player

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.riddler.R
import com.example.riddler.data.repo.GameRepository
import com.example.riddler.ui.view.host.HostActivity
import com.example.riddler.ui.viewmodel.HostViewModel
import com.example.riddler.ui.viewmodel.PlayerViewModel
import com.google.android.material.textfield.TextInputLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PlayerJoinLobbyFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_player_join_lobby, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val joinLobbyButton = view.findViewById<Button>(R.id.joinGameButton)
        val gamePinText = view.findViewById<TextView>(R.id.gamePinText)
        val vm = ViewModelProvider(requireActivity()).get(PlayerViewModel::class.java)
        val inputPin : TextInputLayout = view.findViewById(R.id.game_pin_layout)


//        gamePinText.setOnFocusChangeListener { _, focused ->
//            val password = gamePinText.text.toString()
//            if(!focused) {
//                if (password.isEmpty()) {
//                    inputPin.helperText = "*Game Pin Required"
//                } else {
//                    inputPin.helperText = ""
//                }
//            }

        joinLobbyButton.setOnClickListener {
            vm.callJoinLobby(gamePinText.text.toString()) { gameId -> joinLobby(gameId) }

        }

//        joinLobbyButton.setOnClickListener {
//            vm.callJoinLobby(gamePinText.text.toString()) { gameId -> joinLobby(gameId) }
//        }
    }

    fun joinLobby(gameId: String) {
        val intent = Intent(getActivity(), PlayerActivity::class.java)
        intent.putExtra("pin", gameId)
        activity?.startActivity(intent)
        activity?.finish()
    }

}