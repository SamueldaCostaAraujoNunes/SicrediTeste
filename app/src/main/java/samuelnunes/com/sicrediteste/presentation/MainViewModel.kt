package samuelnunes.com.sicrediteste.presentation

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuelnunes.utility_tool_kit.network.NetworkConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var networkConnectivityObserver: NetworkConnectivityObserver
) : ViewModel() {

    private val _networkConnectivity: MutableLiveData<Boolean> = MutableLiveData()

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
    }

}