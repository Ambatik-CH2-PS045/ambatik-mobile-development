package com.example.ambatik.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseListQuiz(

	@field:SerializedName("data")
	val data: List<DataItemQuiz?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class DataItemQuiz(

	@field:SerializedName("quiz_histories")
	val quizHistories: List<QuizHistoriesItem?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable

@Parcelize
data class QuizHistoriesItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("quizId")
	val quizId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("done")
	val done: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("point")
	val point: Int? = null
) : Parcelable
