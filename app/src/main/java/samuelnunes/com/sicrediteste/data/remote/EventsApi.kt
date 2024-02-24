package samuelnunes.com.sicrediteste.data.remote

import com.samuelnunes.utility_tool_kit.domain.Resource
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import samuelnunes.com.sicrediteste.data.remote.dto.request.CheckinModelRequest
import samuelnunes.com.sicrediteste.data.remote.dto.response.EventsModelResponse

interface EventsApi {

    @GET("events")
    suspend fun getAllEvents(): Resource<List<EventsModelResponse>>

    @GET("events/{id}")
    suspend fun getEvent(@Path("id") eventId: String): Resource<EventsModelResponse>

    @POST("checkin")
    suspend fun eventCheckin(@Body body: CheckinModelRequest): Resource<Any>


}
