package com.example.riddler.ui.view.host

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import com.example.riddler.R
import com.example.riddler.data.model.Quiz

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HostCreateLobbyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HostCreateLobbyFragment : Fragment() {

    private var quizName: String? = null
    private var quizId: Int? = null
    lateinit var createLobby: Button
    lateinit var quiz: Quiz
    lateinit var validateToast: Toast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            quizName = it.getString("quizName")
            quizId = it.getInt("quizId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var myView = inflater.inflate(R.layout.fragment_host_create_lobby, container, false)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createLobby = view.findViewById<Button>(R.id.createLobbyButton)
        val quizNameText = view.findViewById<TextView>(R.id.hostCreateQuizName)
        val lobbySize = view.findViewById<TextView>(R.id.hostCreateMaxPlayer)
        quizNameText.text = quizName

        createLobby.setOnClickListener {
            val lobbySizeText = lobbySize.text.toString()
            if (validate(lobbySizeText)) {
                val intent = Intent(activity, HostActivity::class.java)
                intent.putExtra("quizId", quizId)
                intent.putExtra("lobbySize", lobbySizeText.toInt())
                activity?.startActivity(intent)
                activity?.finish()
            } else {
                validateToast.show()
            }
        }

    }

    fun validate(lobbySize: String): Boolean {
        if (lobbySize == "") {
            validateToast = Toast.makeText(context, "Please enter a lobby size", Toast.LENGTH_SHORT)
            return false
        } else if (lobbySize.toInt() < 1 || lobbySize.toInt() > 16) {
            validateToast = Toast.makeText(context, "Lobby size must be between 1 and 16", Toast.LENGTH_SHORT)
            return false
        }
        return true
    }
}