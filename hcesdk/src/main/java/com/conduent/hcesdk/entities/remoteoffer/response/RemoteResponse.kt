package com.conduent.hcesdk.entities.remoteoffer.response

import com.google.gson.annotations.SerializedName

data class RemoteResponse(
    @SerializedName("ArticleID") val articleID : Int,
    @SerializedName("Counter") val counter : Counter,
    @SerializedName("DatesZonesPrices") val datesZonesPrices : List<DatesZonesPrices>,
    @SerializedName("ItemCategory") val itemCategory : String,
    @SerializedName("NetworkID") val networkID : Int,
    @SerializedName("ProductID") val productID : Int,
    @SerializedName("TitleOffer") val titleOffer : String,
    @SerializedName("UnitPrice") val unitPrice : Int,
    @SerializedName("VatRate") val vatRate : Int,
    @SerializedName("CustomData") val customData : CustomData,
    @SerializedName("RequiresAuth") val requiresAuth : String

)

data class Counter (

    @SerializedName("MaxBuyValue") val maxBuyValue : Int,
    @SerializedName("MinBuyValue") val minBuyValue : Int,
    @SerializedName("StepBuyValue") val stepBuyValue : Int
)

data class CustomData (

    @SerializedName("CustomData1") val customData1 : String,
    @SerializedName("CustomData2") val customData2 : String,
    @SerializedName("CustomData3") val customData3 : String,
    @SerializedName("CustomData4") val customData4 : String
)

data class DatesZonesPrices (

    @SerializedName("Dates") val dates : List<DDates>,
    @SerializedName("ZonesPrices") val zonesPrices : List<ZonesPrices>
)

data class DDates (

    @SerializedName("Duration") val duration : Int,
    @SerializedName("MaxStartDate") val maxStartDate : String,
    @SerializedName("MinStartDate") val minStartDate : String,
    @SerializedName("Step") val step : Int,
    @SerializedName("Unit") val unit : String
)

data class ZonesPrices (

    @SerializedName("UnitPrice") val unitPrice : Int,
    @SerializedName("VatRate") val vatRate : Int,
    @SerializedName("ZoneID") val zoneID : String,
    @SerializedName("ZoneLabel") val zoneLabel : String
)