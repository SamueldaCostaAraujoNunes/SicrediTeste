package samuelnunes.com.sicrediteste.domain.repository

import com.samuelnunes.utility_tool_kit.domain.Resource
import kotlinx.coroutines.flow.Flow
import samuelnunes.com.sicrediteste.data.local.entitys.EventEntity

interface IEventRepository {
    fun getAllEvents(): Flow<Resource<List<EventEntity>>>
    fun getEvent(eventId: String): Flow<Resource<EventEntity>>
}
