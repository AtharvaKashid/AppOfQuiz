package com.example.appofquiz

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class QuizModel {
    @SerializedName("category")
    @Expose
    private var category: String? = null

    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("correctAnswer")
    @Expose
    private var correctAnswer: String? = null

    @SerializedName("incorrectAnswers")
    @Expose
    private var incorrectAnswers: List<String?>? = null

    @SerializedName("question")
    @Expose
    private var question: String? = null

    @SerializedName("tags")
    @Expose
    private var tags: List<String?>? = null

    @SerializedName("type")
    @Expose
    private var type: String? = null

    @SerializedName("difficulty")
    @Expose
    private var difficulty: String? = null

    @SerializedName("regions")
    @Expose
    private var regions: List<Any?>? = null

    @SerializedName("isNiche")
    @Expose
    private var isNiche:Boolean=false
      fun getIsNiche():Boolean
      {
          return isNiche
      }
    fun setIsNiche(isNiche:Boolean){
        this.isNiche=isNiche
    }

    fun getCategory(): String? {
        return category
    }

    fun setCategory(category: String?) {
        this.category = category
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getCorrectAnswer(): String? {
        return correctAnswer
    }

    fun setCorrectAnswer(correctAnswer: String?) {
        this.correctAnswer = correctAnswer
    }

    fun getIncorrectAnswers(): List<String?>? {
        return incorrectAnswers
    }

    fun setIncorrectAnswers(incorrectAnswers: List<String?>?) {
        this.incorrectAnswers = incorrectAnswers
    }

    fun getQuestion(): String? {
        return question
    }

    fun setQuestion(question: String?) {
        this.question = question
    }

    fun getTags(): List<String?>? {
        return tags
    }

    fun setTags(tags: List<String?>?) {
        this.tags = tags
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

    fun getRegions(): List<Any?>? {
        return regions
    }

    fun setRegions(regions: List<Any?>?) {
        this.regions = regions
    }
}


