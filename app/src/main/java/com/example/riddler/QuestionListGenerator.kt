package com.example.riddler

class QuestionListGenerator {
    var questionList = ArrayList<TriviaQuestions.Question>()

    fun makeList(triviaQuestions: TriviaQuestions) : ArrayList<TriviaQuestions.Question> {
        if (!triviaQuestions.results.isNullOrEmpty()) {

            for (index in 0..triviaQuestions.results.lastIndex) {
                var category = triviaQuestions.results[index].category
                var type = triviaQuestions.results[index].type
                var difficulty= triviaQuestions.results[index].difficulty
                var question = triviaQuestions.results[index].question
                var correct_answer = triviaQuestions.results[index].correct_answer
                var incorrect_answers = triviaQuestions.results[index].incorrect_answers

                var completeQuestion = TriviaQuestions.Question(category,type,difficulty, question,
                    correct_answer, incorrect_answers)

                questionList.add(completeQuestion)
            }

        }
        return questionList
    }
}