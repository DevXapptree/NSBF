package com.conduent.hcesdk.utils

import android.support.annotation.RestrictTo
import com.conduent.hcesdk.HCERecordFile
import com.conduent.hcesdk.entities.HCEResult
import com.conduent.hcesdk.entities.result.EnvironmentData
import com.conduent.hcesdk.entities.result.Holder
import com.conduent.hcesdk.entities.result.HolderData

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

    fun getEnvVersion(hceResults: ArrayList<HCEResult>):Int{
        var version:Int = 0
        val dataList = hceResults.filter { data -> data.tag.contains(HCETag.ENV_APP_VERSION_NUMBER, true) }
        if (!dataList.isNullOrEmpty()) {
            val resObj = dataList[0]
            version = HCEUtils.binaryStringToDecimal(resObj.binaryValue)
        }
        return version;
    }

    fun getEnvironment(hceResults: ArrayList<HCEResult>):EnvironmentData{
        val environmentData = EnvironmentData()
        val envAppIssuerIdList = hceResults.filter { data -> data.tag.contains(HCETag.ENV_APP_ISSUER_ID, true) }
        val envValidityEndDateList = hceResults.filter { data -> data.tag.contains(HCETag.ENV_APP_ISSUER_ID, true) }
        val envAuthenticatorList = hceResults.filter { data -> data.tag.contains(HCETag.ENV_AUTHENTICATOR, true) }
        val envNetworkIDList = hceResults.filter { data -> data.tag.contains(HCETag.ENV_NETWORK_ID, true) }

        if (!envAppIssuerIdList.isNullOrEmpty()) {
            val resObj = envAppIssuerIdList[0]
            environmentData.envApplicationIssuerID= HCEUtils.binaryStringToDecimal(resObj.binaryValue)
        }

        if (!envValidityEndDateList.isNullOrEmpty()) {
            val resObj = envValidityEndDateList[0]
            environmentData.envApplicationValidityEndDate= HCEUtils.getEnvApplicationValidityEndDate(resObj.binaryValue)
        }

        if (!envAuthenticatorList.isNullOrEmpty()) {
            val resObj = envAuthenticatorList[0]
            environmentData.envAuthenticator= HCEUtils.binaryStringToDecimal(resObj.binaryValue)
        }

        if (!envNetworkIDList.isNullOrEmpty()) {
            val resObj = envNetworkIDList[0]
            val network = HCEUtils.binaryToHexString(resObj.binaryValue)
            if(network.length>3)
            environmentData.envNetworkID= network.substring(network.length - 3).toInt()
        }
        return environmentData;
    }

    fun getHolder(hceResults: ArrayList<HCEResult>):Holder{
        val holder = Holder()
        val holderData = HolderData()
        val holderDataCardStatusList = hceResults.filter { data -> data.tag.contains(HCETag.HOLDER_DATA_CARD_STATUS, true) }
        val holderDataCommercialIdList = hceResults.filter { data -> data.tag.contains(HCETag.HOLDER_DATA_COMMERCIAL_ID, true) }

        if (!holderDataCardStatusList.isNullOrEmpty()) {
            val resObj = holderDataCardStatusList[0]
            if(!resObj.binaryValue.isNullOrEmpty()){
              //  var cardStatus
            }
        }

        if (!holderDataCommercialIdList.isNullOrEmpty()) {
            val resObj = holderDataCommercialIdList[0]
            if(!resObj.binaryValue.isNullOrEmpty()){

            }
        }
        holder.holderData= holderData
        return holder;
    }
}