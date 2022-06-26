package com.example.riddler.data.repo

import com.example.riddler.data.model.Quiz
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreRepository() {
    val db = FirebaseFirestore.getInstance()

    //TODO use rxkotlin and validation
    fun insertQuiz(quiz: Quiz) {

        db.collection("quizzes")
            .add(quiz)
            .addOnSuccessListener {
                println("succeeded in adding ${quiz} to firestore") //change it later
            }
            .addOnFailureListener {
                println("failed in adding ${quiz} to firestore") //change it later
            }
    }

    //TODO use rxkotlin and validation
    fun getAllQuizByUser(user: String) : List<Quiz> {
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

    //TODO use rxkotlin and validation
    fun getAllQuiz() : List<Quiz> {
        var quizList : ArrayList<Quiz> = ArrayList<Quiz>()

        db.collection("quizzes")
            .whereEqualTo("id",1)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    println(document.data)
                }
            }
            .addOnFailureListener {
                println("failed in getting quizzes from ") //change it later
            }

        return quizList
    }
    fun getQuiz() {
        val docRef = db.collection("quizzes").document("101349")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                println(snapshot.data)
            } else {
            }
        }

    }
}