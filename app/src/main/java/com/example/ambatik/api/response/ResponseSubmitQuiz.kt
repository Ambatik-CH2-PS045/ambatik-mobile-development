package com.example.ambatik.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseSubmitQuiz(

	@field:SerializedName("data")
	val data: List<DataItemSummary?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class DataItemSummary(

	@field:SerializedName("summary")
	val summary: Summary? = null
) : Parcelable

@Parcelize
data class Summary(

	@field:SerializedName("accumulatePoint")
	val accumulatePoint: Int? = null,

	@field:SerializedName("totalCorrect")
	val totalCorrect: Int? = null,

	@field:SerializedName("totalWrong")
	val totalWrong: Int? = null
) : Parcelable
