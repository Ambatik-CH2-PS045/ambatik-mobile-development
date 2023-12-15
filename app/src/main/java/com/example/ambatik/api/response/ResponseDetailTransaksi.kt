package com.example.ambatik.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseDetailTransaksi(

	@field:SerializedName("data")
	val data: List<DataItemDetailOrder?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class ProductsItem(

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url_product")
	val urlProduct: String? = null,

	@field:SerializedName("store_name")
	val storeName: String? = null,

	@field:SerializedName("detail_order")
	val detailOrder: DetailOrder? = null
) : Parcelable

@Parcelize
data class DataItemDetailOrder(

	@field:SerializedName("total_item")
	val totalItem: Int? = null,

	@field:SerializedName("total_price")
	val totalPrice: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("products")
	val products: List<ProductsItem?>? = null
) : Parcelable

@Parcelize
data class DetailOrder(

	@field:SerializedName("each_qty")
	val eachQty: Int? = null
) : Parcelable
