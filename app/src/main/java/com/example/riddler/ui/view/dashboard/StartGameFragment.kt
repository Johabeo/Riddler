package com.example.riddler.ui.view.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.riddler.R
import com.example.riddler.ui.view.MainActivity
import com.example.riddler.ui.view.host.HostActivity
import com.example.riddler.ui.view.player.PlayerActivity
import com.example.riddler.ui.view.player.PlayerJoinLobbyFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StartGameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartGameFragment : Fragment() {

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
        var myView = inflater.inflate(R.layout.fragment_start_game, container, false)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var hostButton = view.findViewById<Button>(R.id.dash_hostGameButton)
        hostButton.setOnClickListener {
            val intent = Intent(activity, HostActivity::class.java)
            startActivity(intent)
        }

        var joinButton = view.findViewById<Button>(R.id.dash_joinGameButton)
        joinButton.setOnClickListener {
            val intent = Intent(activity, PlayerJoinLobbyFragment::class.java)
            startActivity(intent)
        }
    }
}