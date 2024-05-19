package id.ac.ukdw.fastmenu.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ukdw.fastmenu.data.repository.MakananRepository
import id.ac.ukdw.fastmenu.view.detail.DetailMakananViewModel
import id.ac.ukdw.fastmenu.view.search.SearchMakananViewModel


class SearchMakananViewModelFactory(private val repository: MakananRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SearchMakananViewModel::class.java) -> {
                SearchMakananViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailMakananViewModel::class.java) -> {
                DetailMakananViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}