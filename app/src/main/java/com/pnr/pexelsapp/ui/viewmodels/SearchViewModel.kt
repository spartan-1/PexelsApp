package com.pnr.pexelsapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pnr.pexelsapp.model.PexelPhoto
import com.pnr.pexelsapp.restclient.webservices.PexelsWebService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SearchViewModel @Inject constructor(private val pexelsWebService: PexelsWebService) :
    ViewModel(),
    CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main

    private var pexelPhotosMutableLiveData: MutableLiveData<List<PexelPhoto>> = MutableLiveData()

    /**
     * load Pexel results based on search parameter
     *
     * @param queryString
     * @param pageCount
     * @return
     */
    fun loadData(queryString: String, pageCount: Int): MutableLiveData<List<PexelPhoto>> {

        this.launch(coroutineContext) {
            val response = pexelsWebService.searchPexel(queryString,pageCount)
            response?.let {
                pexelPhotosMutableLiveData.postValue(it.body()!!.photos)
            } ?: run {
                pexelPhotosMutableLiveData.postValue(null)
            }
        }
        return pexelPhotosMutableLiveData
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}