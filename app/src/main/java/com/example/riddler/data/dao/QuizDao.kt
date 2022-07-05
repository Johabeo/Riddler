package com.example.riddler.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.riddler.data.model.Questions
import com.example.riddler.data.model.Quiz
import io.reactivex.rxjava3.core.Observable

@Dao
interface QuizDao {

//    @Query("select * from Quiz where id = (select quizId from FavoriteQuiz where userId = :user)")
//    fun getFavoriteQuizzes(user: Int): Observable<List<Quiz>>

    @Query("select * from Quiz where owner = :user")
    fun getMyQuizzes(user: Int): Observable<List<Quiz>>

    @Query("select * from Quiz limit :limit offset :offset")
    fun getQuizzes(limit: Int, offset: Int): Observable<List<Quiz>>

    @Query("select * from Questions where quizId = :quizId")
    fun getQuizQuestions(quizId: Int): List<Questions>
    @Insert
    fun insertQuiz(quiz: Quiz): Long

//    @Insert
//    fun insertFavoriteQuiz(favoriteQuiz: FavoriteQuiz)

    @Insert
    fun insertQuestions(questions: Questions)

    @Query("select * from Quiz " +
            "join quiz_fts on Quiz.id = quiz_fts.id" +
            " where quiz_fts match :searchQuery")
    fun search(searchQuery: String) : Observable<List<Quiz>>
}