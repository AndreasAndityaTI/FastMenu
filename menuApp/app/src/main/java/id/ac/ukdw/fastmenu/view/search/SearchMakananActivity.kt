package id.ac.ukdw.fastmenu.view.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasktrackerapp.databinding.ActivitySearchMakananBinding
import id.ac.ukdw.fastmenu.data.remote.ApiConfig
import id.ac.ukdw.fastmenu.data.repository.MakananRepository
import id.ac.ukdw.fastmenu.data.response.MakananItem
import id.ac.ukdw.fastmenu.view.SearchMakananViewModelFactory

class SearchMakananActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchMakananBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchMakananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = ApiConfig.getApiService()
        val makananRepository = MakananRepository(apiService)

        val viewModelFactory = SearchMakananViewModelFactory(makananRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SearchMakananViewModel::class.java)

        val layoutManager = LinearLayoutManager(this)
        binding.rvMakanan.layoutManager = layoutManager

        binding.appBar.setNavigationOnClickListener{
            finish()
        }

        viewModel.listMakanan.observe(this) { listMakanan ->
            setMakananData(listMakanan)
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setMakananData(data: List<MakananItem>) {
        val adapter = MakananAdapter()
        adapter.submitList(data)
        binding.rvMakanan.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}