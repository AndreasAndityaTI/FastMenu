package id.ac.ukdw.fastmenu.data.remote

import id.ac.ukdw.fastmenu.data.response.DetailMakananResponse
import id.ac.ukdw.fastmenu.data.response.ScanResponse
import id.ac.ukdw.fastmenu.data.response.MakananResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @GET("makanan")
    suspend fun getMakanan() : MakananResponse

    @GET("makanan/{id}")
    suspend fun getDetailMakanan(
        @Path("id") id: String
    ) : DetailMakananResponse

    @Multipart
    @POST("post")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part
    ): ScanResponse

}