package samuelnunes.com.sicrediteste.presentation.screens.eventDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.samuelnunes.utility_tool_kit.domain.Resource
import com.samuelnunes.utility_tool_kit.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import samuelnunes.com.sicrediteste.data.local.entitys.EventEntity
import samuelnunes.com.sicrediteste.domain.repository.IEventRepository
import samuelnunes.com.sicrediteste.presentation.commons.collectLoading
import javax.inject.Inject


@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    private var repository: IEventRepository
) : ViewModel() {

    private val _loading: MutableLiveData<Int> = MutableLiveData(0)
    private val _error: MutableLiveData<UiText> = MutableLiveData()
    private val _event: MutableLiveData<EventEntity> = MutableLiveData()
    private val _networkConnectivity: MutableLiveData<Boolean> = MutableLiveData()

    val loading: LiveData<Boolean>
        get() = _loading.map { it > 0 }
    val error: LiveData<UiText>
        get() = _error
    val event: LiveData<EventEntity>
        get() = _event
    val networkConnectivity: LiveData<Boolean>
        get() = _networkConnectivity

    fun getEvent(eventId: String) {
        viewModelScope.launch {
            repository.getEvent(eventId).collectLoading(_loading) { result ->
                when (result) {
                    is Resource.Success -> {
                        _event.value = result.data!!
                    }
                    is Resource.Loading -> {}
                    is Resource.Empty -> {}
                    is Resource.Error -> {}
                }

            }

        }
    }

}