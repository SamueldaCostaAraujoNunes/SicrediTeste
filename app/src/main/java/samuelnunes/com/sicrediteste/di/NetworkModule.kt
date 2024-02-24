package samuelnunes.com.sicrediteste.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.samuelnunes.utility_tool_kit.database.dao.buildRoomDatabase
import com.samuelnunes.utility_tool_kit.network.EnumConverter.EnumConverterFactory
import com.samuelnunes.utility_tool_kit.network.FlowAdapter.FlowCallAdapterFactory
import com.samuelnunes.utility_tool_kit.network.LiveDataAdapter.LiveDataCallAdapterFactory
import com.samuelnunes.utility_tool_kit.network.NetworkConnectivityObserver
import com.samuelnunes.utility_tool_kit.network.buildRetrofit
import com.samuelnunes.utility_tool_kit.network.client
import com.samuelnunes.utility_tool_kit.network.naturalAdapter.ResourceCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import samuelnunes.com.sicrediteste.BuildConfig.EVENTS_API_BASE_URL
import samuelnunes.com.sicrediteste.data.local.AppDatabase
import samuelnunes.com.sicrediteste.data.local.dao.EventDao
import samuelnunes.com.sicrediteste.data.remote.EventsApi
import samuelnunes.com.sicrediteste.data.repository.EventRepository
import samuelnunes.com.sicrediteste.domain.repository.IEventRepository
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providerRetrofitBuilder(): Retrofit = buildRetrofit {
        addCallAdapterFactory(ResourceCallAdapterFactory.create())
        addCallAdapterFactory(LiveDataCallAdapterFactory.create())
        addCallAdapterFactory(FlowCallAdapterFactory.create())
        addConverterFactory(EnumConverterFactory.create())
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(EVENTS_API_BASE_URL)

        client {
            readTimeout(30L, TimeUnit.SECONDS)
            connectTimeout(30L, TimeUnit.SECONDS)
        }

    }

    @Singleton
    @Provides
    fun providerEventsApiService(retrofit: Retrofit): EventsApi = retrofit.create()

    @Provides
    @Singleton
    fun database(@ApplicationContext context: Context): AppDatabase =
        buildRoomDatabase(context, AppDatabase::class.java, AppDatabase.EVENTS_TABLE_NAME) {
            fallbackToDestructiveMigration()
        }

    @Singleton
    @Provides
    fun provideEventDao(db: AppDatabase): EventDao = db.eventDao()


    @RequiresApi(Build.VERSION_CODES.N)
    @Singleton
    @Provides
    fun provideNetworkConnection(@ApplicationContext appContext: Context): NetworkConnectivityObserver =
        NetworkConnectivityObserver(appContext)

    @Singleton
    @Provides
    fun providerTheCatsRepository(
        eventApi: EventsApi,
        eventDao: EventDao
    ): IEventRepository = EventRepository(eventApi, eventDao)
}