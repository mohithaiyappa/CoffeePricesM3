package tk.mohithaiyappa.coffeepricesm3.data.repo

import android.app.Application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tk.mohithaiyappa.coffeepricesm3.data.model.SpicePrices
import tk.mohithaiyappa.coffeepricesm3.data.source.remote.SpicePriceApiService
import tk.mohithaiyappa.coffeepricesm3.domain.repo.SpicePriceRepo
import javax.inject.Inject

class SpicePriceRepoImpl
    @Inject
    constructor(
        private val apiService: SpicePriceApiService,
        private val appContext: Application,
    ) : SpicePriceRepo {
        override suspend fun getAllSpicePrice(): SpicePrices {
            return withContext(Dispatchers.IO) {
                val response = apiService.getLatestPrice().execute()
                if (response.isSuccessful) {
                    response.body() ?: throw IllegalStateException("Response body is null")
                } else {
                    throw Exception("Failed to fetch spice prices: ${response.errorBody()?.string()}")
                }
            }
        }
    }
