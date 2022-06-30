package com.example.riddler.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.riddler.R
import com.example.riddler.TriviaQuizActivity
import com.example.riddler.data.model.Quiz
import com.example.riddler.ui.adapters.DashboardQuizListAdapter
import com.example.riddler.ui.view.host.HostCreateLobbyFragment
import com.example.riddler.ui.view.player.PlayerIncorrectFragment
import com.example.riddler.ui.viewmodel.DiscoverViewModel
import com.example.riddler.ui.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DiscoverFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DiscoverFragment : Fragment() {

    @Inject
    lateinit var vm : DiscoverViewModel
    private var param1: String? = null
    private var param2: String? = null
    var quizList = ArrayList<Quiz>()
    lateinit var adapter : DashboardQuizListAdapter
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
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val welcomeTextView = view.findViewById<TextView>(R.id.disc_welcomeTextView)
        val userImage = view.findViewById<ImageView>(R.id.disc_userImage)

        val recyclerView = view.findViewById<RecyclerView>(R.id.disc_recyclerview)
        adapter = DashboardQuizListAdapter(quizList, onQuizItemClick)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        vm.getQuiz()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    updateAdapter(it)

                }
            )
    }

    val onQuizItemClick = fun(index : Int) {
        var bundle = Bundle().apply {
            putInt("quizId", quizList.get(index).id)
            putString("quizName", quizList.get(index).name)
        }
        var frag = HostCreateLobbyFragment().apply {
            arguments = bundle
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, frag)
            .commit()

    }

    fun updateAdapter(qList: List<Quiz>) {
        quizList.clear()
        quizList.addAll(qList)
        adapter.notifyDataSetChanged()
    }
}
