package samuelnunes.com.sicrediteste.presentation.screens.eventFeed

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.samuelnunes.utility_tool_kit.domain.Resource
import com.samuelnunes.utility_tool_kit.network.NetworkConnectivityObserver
import com.samuelnunes.utility_tool_kit.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import samuelnunes.com.sicrediteste.data.local.entitys.EventEntity
import samuelnunes.com.sicrediteste.domain.repository.IEventRepository
import javax.inject.Inject


@HiltViewModel
class EventFeedViewModel @Inject constructor(
    private var repository: IEventRepository,
    private var networkConnectivityObserver: NetworkConnectivityObserver
) : ViewModel() {

    private val _loading: MutableLiveData<Int> = MutableLiveData(0)
    private val _error: MutableLiveData<UiText> = MutableLiveData()
    private val _events: MutableLiveData<List<EventEntity>> = MutableLiveData()
    private val _networkConnectivity: MutableLiveData<Boolean> = MutableLiveData()

    val loading: LiveData<Boolean>
        get() = _loading.map { it > 0 }
    val error: LiveData<UiText>
        get() = _error
    val events: LiveData<List<EventEntity>>
        get() = _events
    val networkConnectivity: LiveData<Boolean>
        get() = _networkConnectivity

    init {
        viewModelScope.launch {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                networkConnectivityObserver.observe().collect { status ->
                    val hasConnection = status.hasConnection()
                    _networkConnectivity.value = hasConnection
                }
            } else {
                _networkConnectivity.value = true
            }
        }
        fetchEvents()
    }

    private suspend fun <T : Resource<*>> Flow<T>.collectLoading(collector: FlowCollector<T>) = collect { value ->
        if (value is Resource.Loading<*>) {
            _loading.value = _loading.value?.plus(1)
        } else {
            _loading.value = _loading.value?.minus(1)
        }
        collector.emit(value)
    }

    fun fetchEvents() {
        viewModelScope.launch {
            repository.getAllEvents().collectLoading { result ->
                when (result) {
                    is Resource.Success -> {
                        _events.value = result.data!!
                    }
                    is Resource.Loading -> {}
                    is Resource.Empty -> {}
                    is Resource.Error -> {}
                }

            }

        }
    }

}