package com.example.riddler.ui.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.TypedArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.riddler.OnboardActivity
import com.example.riddler.R
import com.example.riddler.TriviaQuizActivity
import com.example.riddler.Util
import com.example.riddler.data.model.Avatars
import com.example.riddler.data.model.Quiz
import com.example.riddler.ui.adapters.DashboardQuizListAdapter
import com.example.riddler.ui.view.host.HostCreateLobbyFragment
import com.example.riddler.ui.view.player.PlayerIncorrectFragment
import com.example.riddler.ui.view.settings.SettingsActivity
import com.example.riddler.ui.viewmodel.DiscoverViewModel
import com.example.riddler.ui.viewmodel.QuizViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class DiscoverFragment : Fragment() {

    @Inject
    lateinit var vm : DiscoverViewModel
    var quizList = ArrayList<Quiz>()
    lateinit var adapter : DashboardQuizListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        val prefs = activity?.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val pwdHash = prefs?.getString("pwdHash", "pwd hash not found")
        println(pwdHash)

        val welcomeTextView = view.findViewById<TextView>(R.id.disc_welcomeTextView)
        val userImage = view.findViewById<ImageView>(R.id.disc_userImage)
        val searchQuiz = view.findViewById<SearchView>(R.id.search_quiz)
        val openSettingsButton = view.findViewById<FloatingActionButton>(R.id.disc_openSettingsButton)
        val signOutButton = view.findViewById<FloatingActionButton>(R.id.disc_signOutButton)

        openSettingsButton.setOnClickListener {
            openSettings()
        }

        signOutButton.setOnClickListener {
            signOut()
        }

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

        searchQuiz.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    vm.getQuiz()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                            onNext = {
                                updateAdapter(it)

                            }
                        )
                    return false
                }
                vm.searchQuiz(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onNext = {
                            updateAdapter(it)
                        }
                    )
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    vm.getQuiz()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                            onNext = {
                                updateAdapter(it)

                            }
                        )
                    return false
                }
                vm.searchQuiz(newText)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onNext = {
                            updateAdapter(it)
                        }
                    )
                return true
            }

        })
        vm.userProfile.observe(requireActivity()) {
            val str = "${resources.getString(R.string.welcome_user)}, $it!"
            userImage.setImageResource(Avatars.avatarsList.get(it.profilePic))
            welcomeTextView.setText(str)
        }

        vm.fetchUserProfileInfo()

        //todo: fetch user's quizzes in case email changes
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

    fun openSettings(){
        val intent = Intent(requireContext(), SettingsActivity::class.java)
        //startActivity(intent)
        startForResult.launch(intent)
    }

    fun signOut(){
        vm.firebaseRepository.auth.signOut()
        val intent = Intent(requireContext(), OnboardActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            try {
                val prefs = activity?.getSharedPreferences("prefs", Context.MODE_PRIVATE)
                val userLocale = prefs?.getString("locale", "en")
                val locale = Locale(userLocale!!)
                //this will apply the locale and recreate the activity
                (activity as MainActivity).updateElements(locale)
            } catch (ex: Exception) {
                Timber.log(6, ex)
            }
        }
    }
}
