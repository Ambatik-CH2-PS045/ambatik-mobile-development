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
	val quizHistories: List<String?>? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("done")
	val done: String? = null
) : Parcelable
