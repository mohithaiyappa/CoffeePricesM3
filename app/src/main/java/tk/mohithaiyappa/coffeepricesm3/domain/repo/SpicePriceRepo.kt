package tk.mohithaiyappa.coffeepricesm3.domain.repo

import tk.mohithaiyappa.coffeepricesm3.data.model.SpicePrices

interface SpicePriceRepo {
    suspend fun getAllSpicePrice(): SpicePrices
}
