package com.pnr.pexelsapp.ui.activities

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.pnr.pexelsapp.R
import com.pnr.pexelsapp.app.PexelsApp
import com.pnr.pexelsapp.model.PexelPhoto
import com.pnr.pexelsapp.ui.adapters.PhotosListAdapter
import com.pnr.pexelsapp.ui.viewmodels.SearchViewModel
import com.pnr.pexelsapp.util.vmutil.ViewModelFactory
import javax.inject.Inject

/**
 * SearchActivity class
 */
class SearchActivity : AppCompatActivity() {

    private lateinit var searchViewModel: SearchViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.copy_right_info)
    lateinit var copyRightInfo: AppCompatTextView

    @BindView(R.id.searchView)
    lateinit var searchView: SearchView

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.data_loading_progress)
    lateinit var progressBar: ProgressBar

    @BindView(R.id.text_loading_error)
    lateinit var errorMessage: AppCompatTextView

    private lateinit var adapter: PhotosListAdapter

    private var searchCompleted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)
        copyRightInfo.movementMethod = LinkMovementMethod.getInstance()
        (application as PexelsApp).getApplicationComponent().inject(this)
        searchViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
        initRecyclerView()
        initSearchView()
    }

    /**
     * function to init RecyclerView
     *
     */
    private fun initRecyclerView() {
        adapter = PhotosListAdapter(ArrayList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    /**
     * function to init SearchView
     *
     */
    private fun initSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                search(query)
                return false
            }
        })
    }

    /**
     * helper function to search
     *
     * @param query
     */
    private fun search(query: String) {
        searchCompleted = true
        showProgressView()
        searchViewModel.loadData(query, 1)
            .observe(this, pexelsPhotosDataObserver)
    }

    /**
     * Search data observer
     */
    private val pexelsPhotosDataObserver =
        androidx.lifecycle.Observer<List<PexelPhoto>> { searchResults ->
            searchResults?.let {
                adapter.updatePhotos(it)
                if (it.size > 0) {
                    showDataView()
                } else {
                    showErrorView()
                }
            } ?: run {
                showErrorView()
            }
        }

    /**
     * fuction to display Error or nodata view
     *
     */
    private fun showErrorView() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE
        errorMessage.visibility = View.VISIBLE
        copyRightInfo.visibility = View.GONE
    }

    /**
     * function to display data view
     *
     */
    private fun showDataView() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        errorMessage.visibility = View.GONE
        copyRightInfo.visibility = View.VISIBLE
    }

    /**
     * function to display loading indicator
     *
     */
    private fun showProgressView() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        errorMessage.visibility = View.GONE
        copyRightInfo.visibility = View.GONE
    }
}

