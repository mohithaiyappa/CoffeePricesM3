package tk.mohithaiyappa.coffeepricesm3.data.source.remote

import retrofit2.Call
import retrofit2.http.GET
import tk.mohithaiyappa.coffeepricesm3.data.model.SpicePrices

interface SpicePriceApiService {
    @GET("prices/v2/latest/")
    fun getLatestPrice(): Call<SpicePrices>
}
