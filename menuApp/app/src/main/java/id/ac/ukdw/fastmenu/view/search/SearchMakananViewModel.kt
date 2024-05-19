package id.ac.ukdw.fastmenu.view.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import id.ac.ukdw.fastmenu.data.repository.MakananRepository
import id.ac.ukdw.fastmenu.data.response.MakananItem
import kotlinx.coroutines.launch

class SearchMakananViewModel(private val repository: MakananRepository) : ViewModel() {

    private val _listMakanan = MutableLiveData<List<MakananItem>>()
    val listMakanan: LiveData<List<MakananItem>> = _listMakanan


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getMakanan()
    }

    private fun getMakanan() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val makanan = repository.getMakanan().makanan
                _listMakanan.postValue(makanan)
            } catch (e: Exception) {
                Log.e("Error", e.message ?: "Unknown error occurred")
                if (e.message == "timeout"){

                }
            } finally {
                _isLoading.value = false
            }
        }
    }

}