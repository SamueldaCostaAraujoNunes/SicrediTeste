package samuelnunes.com.sicrediteste.presentation.screens.eventCheckin

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuelnunes.utility_tool_kit.domain.Resource
import com.samuelnunes.utility_tool_kit.events.LiveEvent
import com.samuelnunes.utility_tool_kit.events.MutableLiveEvent
import com.samuelnunes.utility_tool_kit.network.HttpStatusCode
import com.samuelnunes.utility_tool_kit.network.NetworkConnectivityObserver
import com.samuelnunes.utility_tool_kit.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import samuelnunes.com.sicrediteste.R
import samuelnunes.com.sicrediteste.data.remote.dto.request.CheckinModelRequest
import samuelnunes.com.sicrediteste.domain.repository.IEventRepository
import javax.inject.Inject


@HiltViewModel
class EventCheckinViewModel @Inject constructor(
    private var repository: IEventRepository
) : ViewModel() {

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _error: MutableLiveData<UiText> = MutableLiveData()
    private val _event: MutableLiveEvent<Boolean> = MutableLiveEvent()

    val loading: LiveData<Boolean>
        get() = _loading
    val error: LiveData<UiText>
        get() = _error
    val event: LiveEvent<Boolean>
        get() = _event

    fun eventCheckin(eventId: String, customerName: String, customerEmail: String) {
        viewModelScope.launch {
            _loading.value = true
            val requestBody = CheckinModelRequest(customerEmail, eventId, customerName)

            repository.eventCheckin(requestBody).let { result ->
                when (result) {
                    is Resource.Success -> {
                        _event.event = true
                    }
                    is Resource.Error -> {
                        _error.value = when (result.statusCode) {
                            HttpStatusCode.NOT_FOUND -> UiText.StringResource(R.string.network_error)
                            HttpStatusCode.BAD_REQUEST -> UiText.StringResource(R.string.too_many_requests_error)
                            else -> UiText.StringResource(R.string.default_error)
                        }
                        _event.event = false
                    }
                    else -> _event.event = false
                }
            }
            _loading.value = false

        }
    }

}