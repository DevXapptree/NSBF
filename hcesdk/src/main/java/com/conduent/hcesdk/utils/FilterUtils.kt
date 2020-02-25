package com.conduent.hcesdk.utils

import android.content.Context
import android.support.annotation.RestrictTo
import com.conduent.hcesdk.HCERecordFile
import com.conduent.hcesdk.entities.HCEResult
import com.conduent.hcesdk.entities.remoteoffer.response.Counter
import com.conduent.hcesdk.entities.result.*
import com.conduent.hcesdk.entities.valuesapi.ProductCustomData
import com.conduent.hcesdk.entities.valuesapi.ProductDescription
import com.conduent.hcesdk.room.AppRoomDataBase
import com.google.gson.Gson
import java.text.NumberFormat
import java.util.*

@RestrictTo(RestrictTo.Scope.LIBRARY)
object FilterUtils {

    fun getHCERecordBySFICode(recordFiles: ArrayList<HCERecordFile>, sfiCode: String): HCERecordFile? {
        val filteredRecords =
            recordFiles.filter { recordFile -> recordFile.sfi.contains(sfiCode, true) }

        if (filteredRecords.isNotEmpty()) {
            return filteredRecords[0]
        }
        return null
    }

    fun checkNextParse(hceRules: ArrayList<HCEResult>, bitmapId: String, bitmapPos: String): Boolean {
        val dataList = hceRules.filter { data -> data.tagId.contains(bitmapId, true) }

        if (dataList.isNotEmpty()) {
            val resObj = dataList[0]
            if (resObj.binaryValue.isNullOrEmpty()) {
                return false
            }
            val dataLength = resObj.binaryValue.length
            val bitMapPos = bitmapPos.toInt()
            if (dataLength < bitMapPos) {
                return false
            }
            val binaryLength = dataLength - 1
            val outPut = resObj.binaryValue[binaryLength - bitMapPos].toString()
            return outPut != "0"
        }

        return false
    }

    fun getEnvVersion(hceResults: ArrayList<HCEResult>): Int {
        var version = 0
        val dataList = hceResults.filter { data -> data.tag.contains(HCETag.ENV_APP_VERSION_NUMBER, true) }
        if (!dataList.isNullOrEmpty()) {
            val resObj = dataList[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                version = HCEUtils.BinaryStringToDecimal(resObj.binaryValue)
            }
        }
        return version
    }

    fun getEnvironment(hceResults: ArrayList<HCEResult>): REnvironmentData {
        val environmentData = REnvironmentData()
        val envAppIssuerIdList = hceResults.filter { data -> data.tag.contains(HCETag.ENV_APP_ISSUER_ID, true) }
        val envValidityEndDateList = hceResults.filter { data -> data.tag.contains(HCETag.ENV_APP_END_DATE, true) }
        val envAuthenticatorList = hceResults.filter { data -> data.tag.contains(HCETag.ENV_AUTHENTICATOR, true) }
        val envNetworkIDList = hceResults.filter { data -> data.tag.contains(HCETag.ENV_NETWORK_ID, true) }
        val network: String

        if (!envAppIssuerIdList.isNullOrEmpty()) {
            val resObj = envAppIssuerIdList[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                environmentData.envApplicationIssuerID =
                    HCEUtils.padLeft(HCEUtils.BinaryStringToDecimal(resObj.binaryValue), 4)
            } else {
                environmentData.envApplicationIssuerID = -1 // If no binary code then passing -1
            }
        }

        if (!envValidityEndDateList.isNullOrEmpty()) {
            val resObj = envValidityEndDateList[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                environmentData.envApplicationValidityEndDate = HCEUtils.getDate(resObj.binaryValue)
            } else {
                environmentData.envApplicationValidityEndDate = ""
            }
        }

        if (!envAuthenticatorList.isNullOrEmpty()) {
            val resObj = envAuthenticatorList[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                environmentData.envAuthenticator =
                    HCEUtils.padLeft(HCEUtils.BinaryStringToDecimal(resObj.binaryValue), 4)
            } else {
                environmentData.envAuthenticator = -1
            }
        }

        if (!envNetworkIDList.isNullOrEmpty()) {
            val resObj = envNetworkIDList[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                network = HCEUtils.BinaryStringToHexString(resObj.binaryValue)
                if (network.length > 3)
                    environmentData.envNetworkID = network.substring(network.length - 3).toInt()
                else environmentData.envNetworkID = network.toInt()
            } else {
                environmentData.envNetworkID = -1
            }
        }
        return environmentData
    }

    fun getHolder(hceResults: ArrayList<HCEResult>, context: Context): RHolder {
        val holder = RHolder()
        val holderData = RHolderData()
        val holderDataCardStatusList =
            hceResults.filter { data -> data.tag.contains(HCETag.HOLDER_DATA_CARD_STATUS, true) }
        val holderDataCommercialIdList =
            hceResults.filter { data -> data.tag.contains(HCETag.HOLDER_DATA_COMMERCIAL_ID, true) }

        if (!holderDataCardStatusList.isNullOrEmpty()) {
            val resObj = holderDataCardStatusList[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                val statusId = HCEUtils.padLeft(HCEUtils.BinaryStringToHexString(resObj.binaryValue), 4)
                val statusValue =
                    AppRoomDataBase.getDatabase(context).valuesAPIDao().getHolderDataCardStatusById(statusId)
                holderData.holderDataCardStatus = statusValue
            } else {
                holderData.holderDataCardStatus = ""
            }
        }

        if (!holderDataCommercialIdList.isNullOrEmpty()) {
            val resObj = holderDataCommercialIdList[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                val commercialId = HCEUtils.padLeft(HCEUtils.BinaryStringToHexString(resObj.binaryValue), 4)
                val commercialValue =
                    AppRoomDataBase.getDatabase(context).valuesAPIDao().getHolderDataCommercialById(commercialId)
                holderData.holderDataCommercialID = commercialValue
            } else {
                holderData.holderDataCommercialID = ""
            }
        }

        holder.holderData = holderData
        return holder
    }

    fun getContractAuthenticator(hceResults: ArrayList<HCEResult>): Int {
        var authenticator = 0
        val dataList = hceResults.filter { data -> data.tag.contains(HCETag.CONTRACT_AUTHENTICATOR, true) }
        if (!dataList.isNullOrEmpty()) {
            val resObj = dataList[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                authenticator = HCEUtils.BinaryStringToDecimal(resObj.binaryValue)
            } else {
                authenticator = -1
            }
        }
        return authenticator
    }

    fun getContractTariff(hceResults: ArrayList<HCEResult>): String {
        var tariff = ""
        val dataList = hceResults.filter { data -> data.tag.contains(HCETag.CONTRACT_TRAFFIC, true) }
        if (!dataList.isNullOrEmpty()) {
            val resObj = dataList[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                tariff = HCEUtils.padLeft(HCEUtils.BinaryStringToHexString(resObj.binaryValue), 4)
            } else {
                tariff = "-1"
            }
        }
        return tariff
    }

    fun getContractCustomData(tariffId: String, context: Context): ProductCustomData {
        var customData = ProductCustomData()
        val productId = HCEUtils.padLeft(tariffId, 4)
        val products = AppRoomDataBase.getDatabase(context).valuesAPIDao().getProductByProductId(productId)
        if (!products.isNullOrEmpty()) {
            val singleProduct = products[0]
            val dataStr = Gson().toJson(singleProduct.customData)
            customData = Gson().fromJson(dataStr, ProductCustomData::class.java)
        }
        return customData
    }

    fun getContractDescription(tariffId: String, context: Context): ProductDescription {
        var description = ProductDescription()
        val productId = HCEUtils.padLeft(tariffId, 4)
        val products = AppRoomDataBase.getDatabase(context).valuesAPIDao().getProductByProductId(productId)
        if (!products.isNullOrEmpty()) {
            val singleProduct = products[0]
            val descStr = Gson().toJson(singleProduct.description)
            description = Gson().fromJson(descStr, ProductDescription::class.java)
        }
        return description
    }

    fun getContractPayMethod(hceResults: ArrayList<HCEResult>): String {
        var payMethod = ""
        val dataList = hceResults.filter { data -> data.tag.contains(HCETag.CONTRACT_PAY_METHOD, true) }
        if (!dataList.isNullOrEmpty()) {
            val resObj = dataList[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                payMethod = HCEUtils.padLeft(HCEUtils.BinaryStringToHexString(resObj.binaryValue), 3)
            }
        }

        return payMethod
    }

    fun getContractPriceAmount(hceResults: ArrayList<HCEResult>): String {
        var amount = "0"
        val dataList = hceResults.filter { data -> data.tag.contains(HCETag.CONTRACT_PRICE_AMOUNT, true) }
        if (!dataList.isNullOrEmpty()) {
            val resObj = dataList[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                val nf = NumberFormat.getCurrencyInstance(Locale.FRANCE)
                amount = nf.format(HCEUtils.BinaryStringToDecimal(resObj.binaryValue) / 100.0)
            }
        }

        return amount
    }

    fun getContractProvider(hceResults: ArrayList<HCEResult>): Int {
        var provider = -1
        val dataList = hceResults.filter { data -> data.tag.contains(HCETag.CONTRACT_PROVIDER, true) }
        if (!dataList.isNullOrEmpty()) {
            val resObj = dataList[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                provider = HCEUtils.BinaryStringToDecimal(resObj.binaryValue)
            }
        }

        return provider
    }

    fun getContractSaleData(hceResults: ArrayList<HCEResult>): RContractSaleData {
        val saleData = RContractSaleData()
        val saleAgent = hceResults.filter { data -> data.tag.contains(HCETag.CONTRACT_SALE_AGENT, true) }
        val saleDate = hceResults.filter { data -> data.tag.contains(HCETag.CONTRACT_SALE_DATE, true) }
        val saleDevice = hceResults.filter { data -> data.tag.contains(HCETag.CONTRACT_SALE_DEVICE, true) }

        if (!saleAgent.isNullOrEmpty()) {
            val resObj = saleAgent[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                saleData.contractSaleAgent = HCEUtils.BinaryStringToDecimal(resObj.binaryValue)
            } else {
                saleData.contractSaleAgent = -1
            }
        }
        if (!saleDate.isNullOrEmpty()) {
            val resObj = saleDate[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                saleData.contractSaleDate = HCEUtils.getDate(resObj.binaryValue)
            } else {
                saleData.contractSaleDate = ""
            }
        }

        if (!saleDevice.isNullOrEmpty()) {
            val resObj = saleDevice[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                saleData.contractSaleDevice = HCEUtils.BinaryStringToDecimal(resObj.binaryValue)
            } else {
                saleData.contractSaleDevice = -1
            }
        }

        return saleData
    }

    fun getContractStatus(hceResults: ArrayList<HCEResult>, context: Context): String {
        var statusValue = ""
        val dataList = hceResults.filter { data -> data.tag.contains(HCETag.CONTRACT_STATUS, true) }
        if (!dataList.isNullOrEmpty()) {
            val resObj = dataList[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                val statusID = HCEUtils.padLeft(HCEUtils.BinaryStringToHexString(resObj.binaryValue), 2)
                try {
                    statusValue = AppRoomDataBase.getDatabase(context).valuesAPIDao().getContractStatusById(statusID)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        return statusValue
    }

    fun getContractValidityInfo(hceResults: ArrayList<HCEResult>, context: Context): RContractValidityInfo {
        val contractValidityInfo = RContractValidityInfo()
        val endDate = hceResults.filter { data -> data.tag.contains(HCETag.CONTRACT_VALIDITY_END_DATE, true) }
        val startDate = hceResults.filter { data -> data.tag.contains(HCETag.CONTRACT_VALIDITY_START_DATE, true) }
        val zones = hceResults.filter { data -> data.tag.contains(HCETag.CONTRACT_VALIDITY_ZONES, true) }

        if (!endDate.isNullOrEmpty()) {
            val resObj = endDate[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                contractValidityInfo.contractEndDate = HCEUtils.getDate(resObj.binaryValue)
            } else {
                contractValidityInfo.contractEndDate = ""
            }
        }

        if (!startDate.isNullOrEmpty()) {
            val resObj = startDate[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                contractValidityInfo.contractStartDate = HCEUtils.getDate(resObj.binaryValue)
            } else {
                contractValidityInfo.contractStartDate = ""
            }
        }

        if (!zones.isNullOrEmpty()) {
            val resObj = zones[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
//                val zoneId = HCEUtils.BinaryStringToHexString(resObj.binaryValue).subSequence(5,8).toString()
                val zoneId = HCEUtils.BinaryStringToHexString(resObj.binaryValue)
                val zoneLabel = AppRoomDataBase.getDatabase(context).valuesAPIDao().getContractZoneById(zoneId)
                contractValidityInfo.contractZones = zoneLabel
            } else {
                contractValidityInfo.contractZones = ""
            }
        }

        return contractValidityInfo
    }

    fun getTransportEvent(hceResults: ArrayList<HCEResult>): REvent {
        val event = REvent()
        val eventCode = hceResults.filter { data -> data.tag.contains(HCETag.EVENT_CODE, true) }
        val eventContractPointer = hceResults.filter { data -> data.tag.contains(HCETag.EVENT_CONTRACT_POINTER, true) }
        val eventDevice = hceResults.filter { data -> data.tag.contains(HCETag.EVENT_DEVICE, true) }
        val eventLocationId = hceResults.filter { data -> data.tag.contains(HCETag.EVENT_LOCATION_ID, true) }
        val eventRouteNumber = hceResults.filter { data -> data.tag.contains(HCETag.EVENT_ROUTING_NUMBER, true) }
        val eventServiceProvider = hceResults.filter { data -> data.tag.contains(HCETag.EVENT_SERVICE_PROVIDER, true) }

        if (!eventCode.isNullOrEmpty()) {
            val resObj = eventCode[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                event.eventCode = HCEUtils.BinaryStringToHexString(resObj.binaryValue)
            } else {
                event.eventCode = ""
            }
        }

        if (!eventContractPointer.isNullOrEmpty()) {
            val resObj = eventContractPointer[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                event.eventContractPointer = HCEUtils.BinaryStringToDecimal(resObj.binaryValue)
            } else {
                event.eventContractPointer = -1
            }
        }

        if (!eventDevice.isNullOrEmpty()) {
            val resObj = eventDevice[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                event.eventDevice = HCEUtils.BinaryStringToDecimal(resObj.binaryValue)
            } else {
                event.eventDevice = -1
            }
        }

        if (!eventLocationId.isNullOrEmpty()) {
            val resObj = eventLocationId[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                event.eventLocationID = HCEUtils.BinaryStringToDecimal(resObj.binaryValue)
            } else {
                event.eventLocationID = -1
            }
        }

        if (!eventRouteNumber.isNullOrEmpty()) {
            val resObj = eventRouteNumber[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                event.eventRouteNumber = HCEUtils.BinaryStringToDecimal(resObj.binaryValue)
            } else {
                event.eventRouteNumber = -1
            }
        }

        if (!eventServiceProvider.isNullOrEmpty()) {
            val resObj = eventServiceProvider[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                event.eventServiceProvider = HCEUtils.BinaryStringToDecimal(resObj.binaryValue)
            } else {
                event.eventServiceProvider = -1
            }
        }

        return event
    }

    fun getEventDate(hceResults: ArrayList<HCEResult>): String {
        var eventDate = ""
        val dateList = hceResults.filter { data -> data.tag.contains(HCETag.EVENT_DATE_STAMP, true) }

        if (!dateList.isNullOrEmpty()) {
            val resObj = dateList[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                eventDate = HCEUtils.getDate(resObj.binaryValue)
            }
        }

        return eventDate
    }

    fun getEventTime(hceResults: ArrayList<HCEResult>): String {
        var eventTime = ""
        val timeList = hceResults.filter { data -> data.tag.contains(HCETag.EVENT_TIME_STAMP, true) }

        if (!timeList.isNullOrEmpty()) {
            val resObj = timeList[0]
            if (!resObj.binaryValue.isNullOrEmpty()) {
                val minutesValue = HCEUtils.BinaryStringToDecimal(resObj.binaryValue)
                val hours = minutesValue / 60 //since both are ints, you get an int
                val minutes = minutesValue % 60
                eventTime = String.format("%d:%02d", hours, minutes)
            }
        }

        return eventTime
    }

    /**
     * provide Zonal Label by Zonal Id in HexString
     * @param context context from HCEEngine
     * @param zoneId Zonal Id in HexString
     *
     * @return zoneLabel as string
     */
    fun getZonalLabel(context: Context, zoneId: String ):String{
        var zoneLabel = ""
        try {
            var id = HCEUtils.stringToHexaString(zoneId)
            zoneLabel = AppRoomDataBase.getDatabase(context).valuesAPIDao().getContractZoneById(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return zoneLabel
    }

    /**
     * provide Auth required status
     * @param productHexCode Product code in HexString format
     * @param context context from HCEEngine
     *
     * @return "true" or "false" as string on base of auth User available
     */
    fun getRequiredAuth(productHexCode: String, context: Context): String{

        val products = AppRoomDataBase.getDatabase(context).valuesAPIDao().getProductByProductId(productHexCode)
        if(!products.isNullOrEmpty()){
            val authUser = products[0].authUser
            if(!authUser.isNullOrEmpty()){
                return "true"
            }
        }

        return "false"
    }

    /**
     * provide Counter value
     * @param productHexCode
     * @param context
     *
     * @return
     */
    fun getCounterValue(productHexCode: String, context: Context): Counter {
        var counter = Counter()
        return counter
    }

}