package id.ac.ukdw.fastmenu.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ac.ukdw.fastmenu.data.repository.MakananRepository
import id.ac.ukdw.fastmenu.data.response.DetailMakananResponse
import kotlinx.coroutines.launch

class DetailMakananViewModel (private val repository: MakananRepository) : ViewModel() {

    private val _detailMakanan = MutableLiveData<DetailMakananResponse>()
    val detailMakanan: LiveData<DetailMakananResponse> =  MutableLiveData()

    fun getDetailMakanan (id: String){
        viewModelScope.launch {
            val detailStory = repository.getDetailMakanan(id)
            _detailMakanan.postValue(detailStory)
        }
    }
}