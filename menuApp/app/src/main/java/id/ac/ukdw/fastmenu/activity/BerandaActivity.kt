package id.ac.ukdw.fastmenu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.search.SearchBar
import com.google.android.material.search.SearchView
import id.ac.ukdw.menuapp.R


class BerandaActivity : AppCompatActivity() {

    private  lateinit var searchView : SearchView

    private lateinit var searchBar: SearchBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_beranda)







        eventClickItem_search()

        val addTaskButton: Button = findViewById(R.id.addTaskButton)
        addTaskButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    //    private fun filter(text: String) {
//        val filteredList: ArrayList<Task> = ArrayList<Task>()
//        for (item in mExampleList) {
//            if (item.getText1().toLowerCase().contains(text.lowercase(Locale.getDefault()))) {
//                filteredList.add(item)
//            }
//        }
//        mAdapter.filterList(filteredList)
    private fun eventClickItem_search() {

        searchBar = findViewById(R.id.searchBar)
        searchView = findViewById(R.id.searchView)

        searchView.setupWithSearchBar(searchBar)
        searchView.editText.setOnEditorActionListener { _, _, _ ->
//            searchBar.text = searchView.text
//            let{
//                searchBar.text=searchView.text
//            }
//            searchBar.let{
            searchBar.setText(searchView.text.toString())


//            }
            searchView.hide()
            // Return false to let the system handle the event if the action is not "Done"
            false

        }

    }
//    }
}
//    fun searchAct() {
////        val searchBar = findViewById<SearchBar>(R.id.searchBar)
////        val searchView = findViewById<SearchView>(R.id.searchView)
////
////        // Set up search functionality
////        searchView.setupWithSearchBar(searchBar)
////
////        // Set the editor action listener for the search bar
////        searchBar.findViewById<SearchBar>(R.id.searchBar).setOnEditorActionListener { _, _, _ ->
////            searchBar.text = searchView.text
////            searchView.hide()
////
////            // Perform your search or other actions here
//////            mainViewModel.resultSearch(searchBar.text.toString())
////
////            false
//        }

////    private fun buildRecyclerView() {
////        viewModel = findViewById<View>(R.id.recyclerView)
////        viewModel.setHasFixedSize(true)
////        mLayoutManager = LinearLayoutManager(this)
////        mAdapter = ExampleAdapter(mExampleList)
////        viewModel.setLayoutManager(mLayoutManager)
////        viewModel.setAdapter(mAdapter)
////    }
//
//}

