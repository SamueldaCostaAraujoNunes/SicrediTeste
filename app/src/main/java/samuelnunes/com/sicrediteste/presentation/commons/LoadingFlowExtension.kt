package samuelnunes.com.sicrediteste.presentation.commons

import androidx.lifecycle.MutableLiveData
import com.samuelnunes.utility_tool_kit.domain.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector


suspend fun <T : Resource<*>> Flow<T>.collectLoading(mutableLiveData: MutableLiveData<Int>, collector: FlowCollector<T>) = collect { value ->
    if (value is Resource.Loading<*>) {
        mutableLiveData.value = mutableLiveData.value?.plus(1)
    } else {
        mutableLiveData.value = mutableLiveData.value?.minus(1)
    }
    collector.emit(value)
}