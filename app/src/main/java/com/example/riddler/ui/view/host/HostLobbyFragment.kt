package com.example.riddler.ui.view.host

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.riddler.R
import com.example.riddler.data.repo.GameRepository
import com.example.riddler.ui.adapters.PlayerAdapter
import com.example.riddler.ui.viewmodel.HostViewModel
import com.example.riddler.ui.viewmodel.PlayerViewModel

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

    lateinit var createLobby : Button
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
        var myView = inflater.inflate(R.layout.fragment_host_lobby, container, false)
        val recyclerView = myView.findViewById<RecyclerView>(R.id.playerList)
        val gamePin = myView.findViewById<TextView>(R.id.lobbyGamePin)
        val gr = GameRepository()
        val vm = HostViewModel(gr)
        vm.callCreateLobby(123)
        vm.playerList.observe(viewLifecycleOwner) {
            val adapter = PlayerAdapter(it)
            recyclerView.adapter = adapter

        }
        vm.pin.observe(viewLifecycleOwner) {
            gamePin.text = it
        }

        return myView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HostLobbyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HostLobbyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}