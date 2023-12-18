package com.example.ambatik.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseShop(

	@field:SerializedName("data")
	val data: List<DataItemShop>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
) : Parcelable

@Parcelize
data class DataItemShop(

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("url_product")
	val urlProduct: String,

	@field:SerializedName("store_name")
	val storeName: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("product_sold")
	val productSold: Int
) : Parcelable
