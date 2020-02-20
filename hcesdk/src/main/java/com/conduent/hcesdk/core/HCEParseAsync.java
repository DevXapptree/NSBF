package com.conduent.hcesdk.core;

import android.os.AsyncTask;
import android.util.Log;
import com.conduent.hcesdk.*;
import com.conduent.hcesdk.entities.HCEResult;
import com.conduent.hcesdk.entities.HCERules;
import com.conduent.hcesdk.entities.result.ContractResult;
import com.conduent.hcesdk.entities.result.EnvironmentResult;
import com.conduent.hcesdk.entities.result.HCECardResult;
import com.conduent.hcesdk.entities.result.TransportLogResult;
import com.conduent.hcesdk.utils.FilterUtils;
import com.conduent.hcesdk.utils.HCEUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HCEParseAsync extends AsyncTask<Void, String, HCECardResult> {
    private HCECardData hceCardData;
    private ReadCallback readCallback;

    HCEParseAsync(HCECardData hceCardData, ReadCallback readCallback) {
        this.hceCardData = hceCardData;
        this.readCallback = readCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected HCECardResult doInBackground(Void... params) {
        try {
            HCECardResult hceCardResult = new HCECardResult();
            ArrayList<EnvironmentResult> environmentResultArrayList = parseSFI_07(hceCardData);
            ArrayList<ContractResult> contractResultArrayList = parseSFI_09(hceCardData);
            ArrayList<TransportLogResult> transportLogArrayList = parseSFI_08(hceCardData);
            hceCardResult.setContracts(contractResultArrayList);
            hceCardResult.setEnvironments(environmentResultArrayList);
            hceCardResult.setEvents(transportLogArrayList);
            return hceCardResult;
        } catch (Exception e) {
            if (readCallback != null) {
                readCallback.onError(new Failure());
            }
            return null;
        }
    }

    @Override
    protected void onPostExecute(HCECardResult result) {
        super.onPostExecute(result);
        Log.i("NSBF", new Gson().toJson(result));
        if (result == null)
            readCallback.onError(new Failure());
        else
            readCallback.onEnded(new Gson().toJson(result));
    }

    /*Parsing SFI07*/
    private ArrayList<EnvironmentResult> parseSFI_07(HCECardData hceCardData) {
        HCERecordFile sfi_07_record = FilterUtils.INSTANCE.getHCERecordBySFICode(hceCardData.getRecordFiles(), SFICodeType.SFI07.code());
        if (sfi_07_record == null) {
            if (readCallback != null) {
                readCallback.onError(null);
            }
            return null;
        }

        ArrayList<HCERecordData> hceRecordDataList = sfi_07_record.getRecordData();
        ArrayList<EnvironmentResult> environmentResults = new ArrayList<>();

        for (int i = 0; i < hceRecordDataList.size(); i++) {
            String recBinaryData = getHexToBin(hceRecordDataList.get(i).getRecord());
            EnvironmentResult envResult = getResultSFI07(recBinaryData);
            environmentResults.add(envResult);
        }

        return environmentResults;
    }

    /*Parsing SFI09*/
    private ArrayList<ContractResult> parseSFI_09(HCECardData hceCardData) {
        HCERecordFile sfi_09_record = FilterUtils.INSTANCE.getHCERecordBySFICode(hceCardData.getRecordFiles(), SFICodeType.SFI09.code());
        if (sfi_09_record == null) {
            if (readCallback != null) {
                readCallback.onError(null);
            }
            return null;
        }

        ArrayList<HCERecordData> hceRecordDataList = sfi_09_record.getRecordData();
        ArrayList<ContractResult> contractResultsList = new ArrayList<>();

        for (int i = 0; i < hceRecordDataList.size(); i++) {
            String recBinaryData = getHexToBin(hceRecordDataList.get(i).getRecord());
            ContractResult contractResult = getResultSFI09(recBinaryData);
            contractResultsList.add(contractResult);
        }

        return contractResultsList;
    }

    /*Parsing SFI08*/
    private ArrayList<TransportLogResult> parseSFI_08(HCECardData hceCardData) {
        HCERecordFile sfi_08_record = FilterUtils.INSTANCE.getHCERecordBySFICode(hceCardData.getRecordFiles(), SFICodeType.SFI08.code());
        if (sfi_08_record == null) {
            if (readCallback != null) {
                readCallback.onError(null);
            }
            return null;
        }

        ArrayList<HCERecordData> hceRecordDataList = sfi_08_record.getRecordData();
        ArrayList<TransportLogResult> transportLogResults = new ArrayList<>();

        for (int i = 0; i < hceRecordDataList.size(); i++) {
            String recBinaryData = getHexToBin(hceRecordDataList.get(i).getRecord());
            TransportLogResult transportLogResult = getResultSFI08(recBinaryData);
            transportLogResults.add(transportLogResult);
        }

        return transportLogResults;
    }

    /*Getting result SFI07*/
    private EnvironmentResult getResultSFI07(String recBinaryData) {
        Log.i("NSBF record binary : ", recBinaryData);
        ArrayList<HCERules> envRules = getMappingRuleAccess().provideHCEEnvironmentRules();
        ArrayList<HCERules> holderRules = getMappingRuleAccess().provideHCEHolderRules();

        ArrayList<HCEResult> envResult = getParseResult(envRules, recBinaryData);
        ArrayList<HCEResult> hldResult = getParseResult(holderRules, recBinaryData);

        EnvironmentResult environmentResult = new EnvironmentResult();
        environmentResult.setEnvVersionNumber(FilterUtils.INSTANCE.getEnvVersion(envResult));
        environmentResult.setEnvironment(FilterUtils.INSTANCE.getEnvironment(envResult));
        environmentResult.setHolder(FilterUtils.INSTANCE.getHolder(hldResult, HCEEngine.localInstance().getContext()));
        return environmentResult;
    }


    /*Getting result for SFI08*/
    private TransportLogResult getResultSFI08(String recBinaryData) {
        Log.i("NSBF record binary : ", recBinaryData);
        ArrayList<HCERules> transportLogRules = getMappingRuleAccess().provideHCETransportLogRules();
        ArrayList<HCEResult> contResult = getParseResult(transportLogRules, recBinaryData);

        TransportLogResult transportLogResult = new TransportLogResult();
        transportLogResult.setEvent(FilterUtils.INSTANCE.getTransportEvent(contResult));
        transportLogResult.setEventDate(FilterUtils.INSTANCE.getEventDate(contResult));
        transportLogResult.setEventTime(FilterUtils.INSTANCE.getEventTime(contResult));

        return transportLogResult;
    }

    /*Getting result for SFI09*/
    private ContractResult getResultSFI09(String recBinaryData) {
        Log.i("NSBF record binary : ", recBinaryData);
        ArrayList<HCERules> contractRules = getMappingRuleAccess().provideHCEContractRules();
        ArrayList<HCEResult> contResult = getParseResult(contractRules, recBinaryData);

        String tariffId = FilterUtils.INSTANCE.getContractTariff(contResult);
        ContractResult contractResult = new ContractResult();
        contractResult.setContractAuthenticator(FilterUtils.INSTANCE.getContractAuthenticator(contResult));
        contractResult.setContractCustomData(FilterUtils.INSTANCE.getContractCustomData(tariffId, HCEEngine.localInstance().getContext()));
        contractResult.setContractDescriptions(FilterUtils.INSTANCE.getContractDescription(tariffId, HCEEngine.localInstance().getContext()));
        contractResult.setContractPayMethod(FilterUtils.INSTANCE.getContractPayMethod(contResult));
        contractResult.setContractPriceAmount(FilterUtils.INSTANCE.getContractPriceAmount(contResult));
        contractResult.setContractProvider(FilterUtils.INSTANCE.getContractProvider(contResult));
        contractResult.setContractSaleData(FilterUtils.INSTANCE.getContractSaleData(contResult));
        contractResult.setContractSaleData(FilterUtils.INSTANCE.getContractSaleData(contResult));
        contractResult.setContractStatus(FilterUtils.INSTANCE.getContractStatus(contResult, HCEEngine.localInstance().getContext()));
        contractResult.setContractTariff(Integer.parseInt(tariffId));
        contractResult.setContractValidityInfo(FilterUtils.INSTANCE.getContractValidityInfo(contResult, HCEEngine.localInstance().getContext()));
        //contractResult.setCounter();
        //contractResult.setPriority(FilterUtils.INSTANCE.getContractValidityInfo(contResult));
        //contractResult.setRecordNumber(FilterUtils.INSTANCE.getContractValidityInfo(contResult, HCEEngine.localInstance().getContext()));

        return contractResult;
    }

    /*Common Parse Result*/
    private ArrayList<HCEResult> getParseResult(ArrayList<HCERules> hceRules, String recBinaryData) {
        int startPos = 0;
        int endPos;
        ArrayList<HCEResult> hceResult = new ArrayList<>();

        for (HCERules ruleObj : hceRules) {
            boolean nextParse = true;

            if (ruleObj.getRule().getBitmapUsed().equalsIgnoreCase("true")) {
                nextParse = FilterUtils.INSTANCE.checkNextParse(hceResult, ruleObj.getRule().getBitmapId(), ruleObj.getRule().getBitmapPos());
            }

            if (nextParse) {
                endPos = startPos + Integer.parseInt(ruleObj.getRule().getDataLength());
                String binaryData = HCEUtils.getCharByLength(startPos, endPos, recBinaryData);
                hceResult.add(new HCEResult(ruleObj.getTagName(), ruleObj.getRule().getDataId(), binaryData));
                startPos = endPos;
                Log.i("NSBF result : ", ruleObj.getTagName() + " - " + binaryData);
            } else {
                hceResult.add(new HCEResult(ruleObj.getTagName(), ruleObj.getRule().getDataId(), null));
                Log.i("NSBF result : ", ruleObj.getTagName() + " - " + "");
            }
        }

        return hceResult;
    }

    private String getHexToBin(String hexStr) {
        return HCEUtils.HexStringToBinaryString(hexStr);
    }

    private IMappingRule getMappingRuleAccess() {
        return CoreProvider.getInstance().provideMappingRuleAccess();
    }
}