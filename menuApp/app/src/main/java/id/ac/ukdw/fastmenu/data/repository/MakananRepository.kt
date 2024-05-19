package id.ac.ukdw.fastmenu.data.repository

import id.ac.ukdw.fastmenu.data.remote.ApiService
import id.ac.ukdw.fastmenu.data.response.DetailMakananResponse
import id.ac.ukdw.fastmenu.data.response.MakananResponse

class MakananRepository(private val apiService : ApiService) {

    suspend fun getMakanan() : MakananResponse {
        return apiService.getMakanan()
    }

    suspend fun getDetailMakanan(id: String) : DetailMakananResponse {
        return apiService.getDetailMakanan(id)
    }

}