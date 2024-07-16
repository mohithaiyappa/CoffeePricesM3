package tk.mohithaiyappa.coffeepricesm3.di

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import tk.mohithaiyappa.coffeepricesm3.data.repo.SpicePriceRepoImpl
import tk.mohithaiyappa.coffeepricesm3.data.source.remote.SpicePriceApiService
import tk.mohithaiyappa.coffeepricesm3.domain.repo.SpicePriceRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesSpiceDataApiService(): SpicePriceApiService {
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl("https://cropmarketprices-production.up.railway.app/")
            .addConverterFactory(
                Json.asConverterFactory(contentType),
            )
            .build()
            .create(SpicePriceApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesSpicePriceRepo(
        apiService: SpicePriceApiService,
        appContext: Application,
    ): SpicePriceRepo {
        return SpicePriceRepoImpl(apiService, appContext)
    }
}
