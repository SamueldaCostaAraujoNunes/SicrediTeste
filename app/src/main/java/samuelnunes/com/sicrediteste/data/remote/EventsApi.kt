package samuelnunes.com.sicrediteste.data.remote

import com.samuelnunes.utility_tool_kit.domain.Resource
import retrofit2.http.GET
import retrofit2.http.Query
import samuelnunes.com.sicrediteste.data.remote.dto.response.EventsBodyModel

interface EventsApi {

    @GET("events")
    suspend fun getAllEvents(): Resource<List<EventsBodyModel>>

}
