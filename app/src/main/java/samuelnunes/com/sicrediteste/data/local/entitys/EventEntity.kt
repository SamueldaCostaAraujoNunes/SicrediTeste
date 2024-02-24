package samuelnunes.com.sicrediteste.data.local.entitys


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventEntity(
    @PrimaryKey
    val id: String,
    val date: Long? = null,
    val description: String? = null,
    val image: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val price: Double? = null,
    val title: String? = null
)