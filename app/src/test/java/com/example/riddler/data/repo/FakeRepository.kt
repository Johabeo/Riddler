package com.example.riddler.data.repo

import com.example.riddler.data.model.Quiz

/*
* Repository is hard to test because it has two complicated dependencies
* Local datasource(Room) and  the database  for the test repo
* running the risks of having a flaky test due to fails in the intermingling of
* network code and local code
* flaky tests result in inconsistent test results
*
*
* to avoid the above issues, we are creating test doubles using 'Fake' and 'Mock'
*
* 'Fake' has a 'working' implementation of the class. It is suitable for the
* test but not for production
*
* 'Mock' is a test double that tracs which of its methods are called and passes
* or fails depending on whether the methods were called correctly
*
*
* */

class FakeRepository:MainRepo {
    override fun insertQuiz(quiz: Quiz) {
        db.collection("quizzes")
            .add(quiz)
            .addOnSuccessListener {
                println("succeeded in adding ${quiz} to firestore") //change it later
            }
            .addOnFailureListener {
                println("failed in adding ${quiz} to firestore") //change it later
            }
    }

    override fun getAllQuizByUser(user: String): List<Quiz> {
        var quizList : ArrayList<Quiz> = ArrayList<Quiz>()

        db.collection("quizzes")
            .whereEqualTo("owner", user)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val quiz = document.toObject(Quiz::class.java)
                    quizList.add(quiz)
                }
            }
            .addOnFailureListener {
                println("failed in getting quizzes from ${user}") //change it later
            }

        return quizList
    }
}