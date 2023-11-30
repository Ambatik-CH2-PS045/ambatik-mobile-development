package com.example.ambatik.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseCart(

	@field:SerializedName("data")
	val data: List<DataItemCart?>? = null,

	@field:SerializedName("totalQty")
	val totalQty: Int? = null,

	@field:SerializedName("grandTotal")
	val grandTotal: Int? = null,

	@field:SerializedName("emptyCart")
	val emptyCart: Boolean? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class DataItemCart(

	@field:SerializedName("total_price")
	val totalPrice: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("total_qty")
	val totalQty: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url_product")
	val urlProduct: String? = null,

	@field:SerializedName("store_name")
	val storeName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable
