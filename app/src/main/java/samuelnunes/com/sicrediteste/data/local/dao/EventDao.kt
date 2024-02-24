package samuelnunes.com.sicrediteste.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.samuelnunes.utility_tool_kit.database.dao.BaseDao
import kotlinx.coroutines.flow.Flow
import samuelnunes.com.sicrediteste.data.local.entitys.EventEntity

@Dao
interface EventDao : BaseDao<EventEntity> {

    @Transaction
    @Query("SELECT * FROM EventEntity")
    fun getAllEvents(): Flow<List<EventEntity>>

    @Query("SELECT * FROM EventEntity WHERE id=:eventId")
    fun getEvent(eventId: String): Flow<EventEntity>

}