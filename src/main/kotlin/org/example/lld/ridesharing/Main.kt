package org.example.lld.ridesharing


import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId

data class Driver(val id: String, val name: String, val email: String)

data class Rider(val id: String, val name: String, val email: String)

data class Ride(
    val id: String,
    val driver: Driver,
    val rider: Rider,
    val rideStartAt: Instant = Instant.now(),
    val distanceInKm: Int,
    val demand: Double
) {
    var finalPrice: Double = 0.0
}

interface PricingStrategy {
    fun calculateOptimalPrice(ride: Ride, basePricePerKm: Double): Double
}

class DynamicPricingStrategy(
    private val distanceMultiplierFunction: (Int) -> Double = { if (it < 5) 1.5 else 1.0 },
    private val timeOfDayMultipliers: (Instant) -> Double = ::timeOfDay,
    private val demandMultipliers: Double = 1.1
) : PricingStrategy {

    override fun calculateOptimalPrice(ride: Ride, basePricePerKm: Double): Double {
        var finalPrice = basePricePerKm * ride.distanceInKm
        finalPrice *= distanceMultiplierFunction(ride.distanceInKm)
        finalPrice *= timeOfDayMultipliers(ride.rideStartAt)
        finalPrice *= demandMultipliers * ride.demand
        return finalPrice
    }

    companion object {
        private fun timeOfDay(timeOfDay: Instant): Double {
            return when (timeOfDay.atZone(ZoneId.of("UTC")).toLocalTime()) {
                in LocalTime.of(8, 30)..LocalTime.of(9, 30) -> 1.5
                in LocalTime.of(16, 30)..LocalTime.of(17, 30) -> 1.6
                else -> 1.0
            }
        }
    }
}

fun main() {
    val rider = Rider("1", "Vivek", "vivek@kothari.in")
    val driver = Driver("2", "Anup", "anup@jalota.in")
    var ride =
        Ride(
            "1",
            driver,
            rider,
            distanceInKm = 2,
            demand = 1.0,
            rideStartAt = Instant.parse("2025-02-17T09:00:00Z")
        )
    val pricingStrategy = DynamicPricingStrategy(demandMultipliers = 1.2)
    ride.finalPrice = pricingStrategy.calculateOptimalPrice(ride, 12.0)
    println(ride.finalPrice)

    ride = ride.copy(demand = 2.0)
    ride.finalPrice = pricingStrategy.calculateOptimalPrice(ride, 12.0)
    println(ride.finalPrice)
}
