package samuelnunes.com.sicrediteste.data.repository

import com.samuelnunes.utility_tool_kit.database.dao.insertOrUpdate
import com.samuelnunes.utility_tool_kit.domain.Resource
import com.samuelnunes.utility_tool_kit.network.BaseRepository
import kotlinx.coroutines.flow.Flow
import samuelnunes.com.sicrediteste.data.local.dao.EventDao
import samuelnunes.com.sicrediteste.data.local.entitys.EventEntity
import samuelnunes.com.sicrediteste.data.mappers.toEntity
import samuelnunes.com.sicrediteste.data.remote.EventsApi
import samuelnunes.com.sicrediteste.domain.repository.IEventRepository
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val api: EventsApi,
    private val dao: EventDao,
) : BaseRepository(), IEventRepository {

    override fun getAllEvents(): Flow<Resource<List<EventEntity>>> =
        networkBoundResource(
            { dao.getAllEvents() },
            { api.getAllEvents() },
            { dao.insertOrUpdate(it) },
            { result -> result.map { it.toEntity() } }
        )

    override fun getEvent(eventId: String): Flow<Resource<EventEntity>> =
        networkBoundResource(
            { dao.getEvent(eventId) },
            { api.getEvent(eventId) },
            { dao.insertOrUpdate(it) },
            { it.toEntity() }
        )

}