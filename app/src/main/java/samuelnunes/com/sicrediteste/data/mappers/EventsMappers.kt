package samuelnunes.com.sicrediteste.data.mappers

import samuelnunes.com.sicrediteste.data.local.entitys.EventEntity
import samuelnunes.com.sicrediteste.data.remote.dto.response.EventsModelResponse

fun EventsModelResponse.toEntity(): EventEntity = EventEntity(
    id = id ?: "",
    date = date,
    description = description,
    image = image,
    latitude = latitude,
    longitude = longitude,
    price = price,
    title = title
)