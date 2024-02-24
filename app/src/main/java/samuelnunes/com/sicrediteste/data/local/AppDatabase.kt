package samuelnunes.com.sicrediteste.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import samuelnunes.com.sicrediteste.data.local.dao.EventDao
import samuelnunes.com.sicrediteste.data.local.entitys.EventEntity

@Database(entities = [EventEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
    companion object {
        const val EVENTS_TABLE_NAME = "EventsTable"
    }

}