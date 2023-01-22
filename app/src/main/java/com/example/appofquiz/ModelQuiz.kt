package com.example.appofquiz

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelQuiz {
    @SerializedName("category")
    @Expose
    private var category: String? = null
    @SerializedName("type")
    @Expose
    private var type: String? = null
    @SerializedName("difficulty")
    @Expose
    private var difficulty: String? = null
    @SerializedName("question")
    @Expose
    private var question: String? = null
    @SerializedName("correct_answer")
    @Expose
    private var correct_answer: String? = null
    @SerializedName("incorrect_answers")
    @Expose
    private var incorrect_answers: List<String?>? = null

    fun getCategory(): String? {
        return category
    }

    fun setCategory(category: String?) {
        this.category = category
    }
    fun getType(): String? {
        return type
    }

    fun setType(type: String?) {
        this.type = type
    }
    fun getDifficulty(): String? {
        return difficulty
    }

    fun setDifficulty(difficulty: String?) {
        this.difficulty = difficulty
    }

    fun getQuestion(): String? {
        return question
    }

    fun setQuestion(question: String?) {
        this.question = question
    }
    fun getCorrect_Answer(): String? {
        return correct_answer
    }

    fun setCorrect_Answer(correctAnswer: String?) {
        this.correct_answer = correctAnswer
    }
    fun getIncorrect_Answers(): List<String?>? {
        return incorrect_answers
    }

    fun setIncorrect_Answers(incorrect_answers: List<String?>?) {
        this.incorrect_answers = incorrect_answers
    }


}