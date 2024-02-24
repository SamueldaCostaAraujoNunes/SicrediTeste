package samuelnunes.com.sicrediteste.data.remote.dto.request


import com.google.gson.annotations.SerializedName

data class CheckinModelRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("eventId")
    val eventId: String,
    @SerializedName("name")
    val name: String
)