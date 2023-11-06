package ru.practicum.android.diploma.similarjob.presentation

import android.app.job.JobInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.similarjob.SimilarJobState
import ru.practicum.android.diploma.similarjob.presentation.api.SimilarJobInteractor

class SimilarJobViewModel(private val similarJobInteractor: SimilarJobInteractor): ViewModel() {

    private val similar = MutableLiveData<Pair<SimilarJobState,ArrayList<JobInfo>>>()

    fun getSimilarLiveData(): LiveData<Pair<SimilarJobState,ArrayList<JobInfo>>> = similar

    fun getSimilar(vacancyId: Long){
        viewModelScope.launch {
            similarJobInteractor.getSimilarJobs(vacancyId).collect{
                similar.value = it
            }
        }
    }


}