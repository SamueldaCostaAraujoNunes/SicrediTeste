package samuelnunes.com.sicrediteste.data.remote.dto.response


import com.google.gson.annotations.SerializedName

data class EventsBodyModel(
    @SerializedName("date")
    val date: Long? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("latitude")
    val latitude: Double? = null,
    @SerializedName("longitude")
    val longitude: Double? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("title")
    val title: String? = null
)