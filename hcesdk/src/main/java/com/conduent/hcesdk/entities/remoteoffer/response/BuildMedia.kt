package com.conduent.hcesdk.entities.remoteoffer.response

import com.google.gson.annotations.SerializedName

data class BuildMedia(
    @SerializedName("MediaInfoWebApiDetails") val mediaInfoWebApiDetails : MediaInfoWebApiDetails,
    @SerializedName("AfterSalesService") val afterSalesService : AfterSalesService,
    @SerializedName("PendingOperations") val pendingOperations : PendingOperations,
    @SerializedName("Products") val products : Products,
    @SerializedName("Version") val version : String,
    @SerializedName("Comment") val comment : String,
    @SerializedName("RessourcesActions") val ressourcesActions : String

)

data class MediaInfoWebApiDetails (

    @SerializedName("ResponseWebApi") val responseWebApi : ResponseWebApi,
    @SerializedName("MediaSerialNumber") val mediaSerialNumber : String,
    @SerializedName("MediaUnicityId") val mediaUnicityId : Int,
    @SerializedName("IsBlackListed") val isBlackListed : String,
    @SerializedName("IsLocked") val isLocked : String,
    @SerializedName("IsInvalid") val isInvalid : String,
    @SerializedName("ExpirationDate") val expirationDate : String,
    @SerializedName("HolderBirthDate") val holderBirthDate : String,
    @SerializedName("ContractNumber") val contractNumber : Int,
    @SerializedName("EndPeriodState") val endPeriodState : String,
    @SerializedName("HolderProfileDefinition") val holderProfileDefinition : List<String>,
    @SerializedName("HolderDataCardStatus") val holderDataCardStatus : String,
    @SerializedName("ContractInfo") val contractInfo : List<ContractInfo>
)

data class ResponseWebApi (

    @SerializedName("ResponseCode") val responseCode : Int,
    @SerializedName("ResponseLabel") val responseLabel : String,
    @SerializedName("InternalResponseCode") val internalResponseCode : Int,
    @SerializedName("InternalResponseLabel") val internalResponseLabel : String
)

data class ContractInfo (

    @SerializedName("Id") val id : String,
    @SerializedName("SavTemporaryCommercialSuspension") val savTemporaryCommercialSuspension : String,
    @SerializedName("SavDefinitiveCommercialSuspension") val savDefinitiveCommercialSuspension : String,
    @SerializedName("SavUnpaidRegularisation") val savUnpaidRegularisation : String,
    @SerializedName("TitleProduct") val titleProduct : String,
    @SerializedName("TitleNetwork") val titleNetwork : String,
    @SerializedName("SaleDate") val saleDate : String,
    @SerializedName("EndPeriod") val endPeriod : String,
    @SerializedName("SoldPeriod") val soldPeriod : String,
    @SerializedName("EndPeriodState") val endPeriodState : String,
    @SerializedName("Profil") val profil : String,
    @SerializedName("ValidityStartDate") val validityStartDate : String,
    @SerializedName("ValidityEndDate") val validityEndDate : String,
    @SerializedName("SlidingValidityEndDate") val slidingValidityEndDate : String,
    @SerializedName("UseValidityEndDate") val useValidityEndDate : String,
    @SerializedName("StatusValidity") val statusValidity : String,
    @SerializedName("StartValidityDateHourMinute") val startValidityDateHourMinute : String,
    @SerializedName("UserNumber") val userNumber : String,
    @SerializedName("SerialNumber") val serialNumber : String,
    @SerializedName("PriceAmount") val priceAmount : Int,
    @SerializedName("ProductKnown") val productKnown : String,
    @SerializedName("Sold") val sold : String,
    @SerializedName("SoldType") val soldType : String,
    @SerializedName("DescriptionLabel") val descriptionLabel : String,
    @SerializedName("RessourceActions") val ressourceActions : String
)

data class AfterSalesService (

    @SerializedName("MediafterSaleOperation") val mediafterSaleOperation : String,
    @SerializedName("ContractAfterSaleOperation") val contractAfterSaleOperation : String,
    @SerializedName("ResponseWebApi") val responseWebApi : ResponseWebApi
)

data class PendingOperations (

    @SerializedName("ResponseWebApi") val responseWebApi : ResponseWebApi,
    @SerializedName("PendingOperationList") val pendingOperationList : List<String>
)

data class Products (

    @SerializedName("ResponseWebApiProducts") val responseWebApiProducts : ResponseWebApiProducts,
    @SerializedName("Product") val product : List<Product>
)

data class Product (

    @SerializedName("Id") val id : Int,
    @SerializedName("Title") val title : String,
    @SerializedName("ProductCode") val productCode : Int,
    @SerializedName("ProductCodeHex") val productCodeHex : String,
    @SerializedName("TitleProduct") val titleProduct : String,
    @SerializedName("NetworkCode") val networkCode : Int,
    @SerializedName("TitleNetwork") val titleNetwork : String,
    @SerializedName("ItemCategory") val itemCategory : String,
    @SerializedName("ProductCategory") val productCategory : List<String>,
    @SerializedName("MediaType") val mediaType : String,
    @SerializedName("IsDematerializedProduct") val isDematerializedProduct : Boolean,
    @SerializedName("Amount") val amount : Int,
    @SerializedName("VatRate") val vatRate : Int,
    @SerializedName("ProductType") val productType : String,
    @SerializedName("ItemEndValidityDateInfo") val itemEndValidityDateInfo : String,
    @SerializedName("ItemStartValidityDateInfo") val itemStartValidityDateInfo : String,
    @SerializedName("ItemStartValidityDateMoreInfo") val itemStartValidityDateMoreInfo : String,
    @SerializedName("ItemAutoLoadingEndDateInfo") val itemAutoLoadingEndDateInfo : String,
    @SerializedName("ItemAutoLoadingInfo") val itemAutoLoadingInfo : String,
    @SerializedName("ItemServiceClassInfo") val itemServiceClassInfo : String,
    @SerializedName("ItemVariableGroupInfo") val itemVariableGroupInfo : String,
    @SerializedName("ItemOdTravelsInfo") val itemOdTravelsInfo : String,
    @SerializedName("ItemAdjustmentScheduledInfo") val itemAdjustmentScheduledInfo : String,
    @SerializedName("ItemThirdPartyPaymentInfo") val itemThirdPartyPaymentInfo : String,
    @SerializedName("ItemSideProductInfo") val itemSideProductInfo : String,
    @SerializedName("ItemScheduledPaymentInfo") val itemScheduledPaymentInfo : String,
    @SerializedName("ItemChargesInfo") val itemChargesInfo : String,
    @SerializedName("ItemEvidenceInfo") val itemEvidenceInfo : String,
    @SerializedName("ItemAuthorizedLinesInfo") val itemAuthorizedLinesInfo : String,
    @SerializedName("ItemAuthorizedAreasInfo") val itemAuthorizedAreasInfo : String,
    @SerializedName("ItemTravelAreaInfo") val itemTravelAreaInfo : String,
    @SerializedName("EvidenceInfo") val evidenceInfo : String,
    @SerializedName("ItemProductPackInfo") val itemProductPackInfo : String,
    @SerializedName("Address") val address : String,
    @SerializedName("ItemMediaProductInfo") val itemMediaProductInfo : String,
    @SerializedName("ItemCounterInfo") val itemCounterInfo : String,
    @SerializedName("ItemDatesZonesPricesInfo") val itemDatesZonesPricesInfo : List<ItemDatesZonesPricesInfo>,
    @SerializedName("Currency") val currency : String,
    @SerializedName("RessourceActions") val ressourceActions : String
)

data class ResponseWebApiProducts (

    @SerializedName("ResponseCode") val responseCode : Int,
    @SerializedName("ResponseLabel") val responseLabel : String,
    @SerializedName("InternalResponseCode") val internalResponseCode : Int,
    @SerializedName("InternalResponseLabel") val internalResponseLabel : String
)

data class ItemDatesZonesPricesInfo (

    @SerializedName("AvailableZonesPrices") val availableZonesPrices : List<AvailableZonesPrices>,
    @SerializedName("Dates") val dates : List<DDates>,
    @SerializedName("RessourceActions") val ressourceActions : String
)

data class Dates (

    @SerializedName("MinStartDate") val minStartDate : String,
    @SerializedName("MaxStartDate") val maxStartDate : String,
    @SerializedName("Unit") val unit : String,
    @SerializedName("Duration") val duration : Int,
    @SerializedName("Step") val step : Int
)

data class AvailableZonesPrices (

    @SerializedName("Id") val id : String,
    @SerializedName("UnitPrice") val unitPrice : Int,
    @SerializedName("VatRate") val vatRate : Int
)

