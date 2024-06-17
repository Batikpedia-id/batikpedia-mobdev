package com.dicoding.batikpedia.api.response

data class Batik(
	val description: String,
	val id: Int,
	val image: String?,
	val name: String
)

data class Store(
	val address: String,
	val id: Int,
	val instagram: String,
	val name: String,
	val phone: String?,
	val tiktok: String,
	val tokopedia: String
)

data class Data(
	val batik: Batik,
	val stores: List<Store>
)

data class ApiResponse(
	val data: Data,
	val message: String,
	val predicted_probabilities: Double,
	val success: Boolean
)
