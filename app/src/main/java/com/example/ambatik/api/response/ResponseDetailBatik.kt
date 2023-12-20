package com.example.ambatik.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseDetailBatik(

	@field:SerializedName("data")
	val data: DataDetailBatik? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("products")
	val products: List<ProductsItemDetailBatik?>? = null
) : Parcelable

@Parcelize
data class ProductsItemDetailBatik(

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("url_product")
	val urlProduct: String? = null,

	@field:SerializedName("store_name")
	val storeName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("product_sold")
	val productSold: Int? = null
) : Parcelable

@Parcelize
data class DataDetailBatik(

	@field:SerializedName("meaning")
	val meaning: String? = null,

	@field:SerializedName("origin")
	val origin: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url_batik")
	val urlBatik: String? = null,

	@field:SerializedName("making_process")
	val makingProcess: String? = null,

	@field:SerializedName("usage_purpose")
	val usagePurpose: String? = null
) : Parcelable
