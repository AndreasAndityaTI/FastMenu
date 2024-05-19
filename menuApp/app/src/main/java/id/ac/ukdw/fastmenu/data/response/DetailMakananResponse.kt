package id.ac.ukdw.fastmenu.data.response

import com.google.gson.annotations.SerializedName

data class DetailMakananResponse(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("image-url")
	val imageUrl: String,

	@field:SerializedName("Kisaran Harga")
	val kisaranHarga: String,

	@field:SerializedName("informasi")
	val informasi: String
)
