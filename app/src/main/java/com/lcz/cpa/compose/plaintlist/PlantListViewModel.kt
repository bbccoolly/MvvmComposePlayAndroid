package com.lcz.cpa.compose.plaintlist

import androidx.lifecycle.*
import com.lcz.core.data.repository.SunFlowerRepository
import com.lcz.core.model.sunflower.SunflowerPhotosEntity
import com.lcz.cpa.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * desc: TODO
 *
 * create by lcz on 2023-03-30
 */
@HiltViewModel
class PlantListViewModel @Inject internal constructor(
    private val sunFlowerRepository: SunFlowerRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _plants: MutableLiveData<List<SunflowerPhotosEntity>> = MutableLiveData()
    val plants: LiveData<List<SunflowerPhotosEntity>>  = _plants


    private val _uiState = MutableLiveData<BaseListUiModel<SunflowerPhotosEntity>>()
    val uiState: LiveData<BaseListUiModel<SunflowerPhotosEntity>> = _uiState

    fun fetchSunFlowerPhotosInfo() {
        viewModelScope.launch {
            sunFlowerRepository.fetchSunFlowerPhotosInfo(
                searchKey = "古装美女",
                onStart = { emitUiState(isLoading = Event(content = true)) },
                onComplete = { emitUiState(isLoading = Event(content = false)) },
                onError = { emitUiState(toastMessage = Event(content = it.toString())) }
            ).collect {
                val entityList = it as ArrayList<SunflowerPhotosEntity>
                emitUiState(
                    listData = Event(content = entityList)
                )
                _plants.postValue(entityList)
            }
        }
    }

    init {
        viewModelScope.launch {
            sunFlowerRepository.fetchSunFlowerPhotosInfo(
                searchKey = "古装美女",
                onStart = { emitUiState(isLoading = Event(content = true)) },
                onComplete = { emitUiState(isLoading = Event(content = false)) },
                onError = { emitUiState(toastMessage = Event(content = it.toString())) }
            ).collect {
                val entityList = it as ArrayList<SunflowerPhotosEntity>
                emitUiState(
                    listData = Event(content = entityList)
                )
                _plants.postValue(entityList)
            }
        }
    }

    private fun emitUiState(
        isLoading: Event<Boolean>? = null,
        toastMessage: Event<String>? = null,
        listData: Event<ArrayList<SunflowerPhotosEntity>>? = null,
        isOver: Event<Boolean>? = null
    ) {
        val uiModel = BaseListUiModel(isLoading, toastMessage, listData, isOver)
        _uiState.postValue(uiModel)
    }

    companion object {
        private const val NO_GROW_ZONE = -1
        private const val GROW_ZONE_SAVED_STATE_KEY = "GROW_ZONE_SAVED_STATE_KEY"
    }
}

data class BaseListUiModel<T>(
    val isLoading: Event<Boolean>?,
    val toastMessage: Event<String>?,
    val listData: Event<ArrayList<T>>?,
    val isOver: Event<Boolean>?
)