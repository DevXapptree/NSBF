package com.conduent.hcesdk.core;

import android.util.Log;
import com.conduent.hcesdk.*;
import com.conduent.hcesdk.entities.HCEResult;
import com.conduent.hcesdk.entities.HCERules;
import com.conduent.hcesdk.entities.result.EnvironmentResult;
import com.conduent.hcesdk.utils.FilterUtils;
import com.conduent.hcesdk.utils.HCEUtils;
import com.google.gson.Gson;


import java.util.ArrayList;

class CoreParser implements ICoreParser {

    private static volatile ICoreParser instance;
    private ReadCallback readCallback;
    private RetrieveRemoteOfferCallback remoteOfferCallback;

    private CoreParser() {

    }

    static ICoreParser getInstance() {
        if (instance == null) {
            synchronized (CoreParser.class) {
                if (instance == null) {
                    instance = new CoreParser();
                }
            }
        }
        return instance;
    }

    @Override
    public HCECardData parseStringToHCECardData(String cardData) {
        HCECardData hceCardData = null;
        try {
            hceCardData = new Gson().fromJson(cardData, HCECardData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return hceCardData;
    }

    @Override
    public byte[] convertHexToByte(String hexData) {
        return HCEUtils.HexStringToByteArray(hexData);
    }

    @Override
    public void startParsingHCE(HCECardData hceCardData, ReadCallback readCallback) {
        this.readCallback = readCallback;
        /*Parsing HCE card, send result as ReadCallback*/
        /*Think ReadCallback required here??*/
        /*Start Parse SFI 07*/
        parseSFI_07(hceCardData);
        /*End Parse SFI 07*/
        parseSFI_09(hceCardData);
    }

    @Override
    public void startRemoteParsingHCE(HCECardData hceCardData, RetrieveRemoteOfferCallback callback) {
        this.remoteOfferCallback = callback;

        HCECardData hceCardDataBase64 = new HCECardData();

        hceCardDataBase64.setAnswerSelectApplication(HCEUtils.hexStringToBase64String(hceCardData.getAnswerSelectApplication().replace(" ","")));
        hceCardDataBase64.setAnswerSelectFileRT(HCEUtils.hexStringToBase64String(hceCardData.getAnswerSelectFileRT().replace(" ","")));

        ArrayList<HCERecordFile> hceRecordFilesBase64 = new ArrayList<>();
        for(HCERecordFile hceRecordFile: hceCardData.getRecordFiles()){
            HCERecordFile hceRecordFileBase64 = new HCERecordFile();
            hceRecordFileBase64.setSFI(hceRecordFile.getSFI());

            ArrayList<HCERecordData> recordDataBase64 = new ArrayList<>();
            for(HCERecordData hceRecordData: hceRecordFile.getRecordData()){
                HCERecordData hceRecordDataBase64 = new HCERecordData();
                hceRecordDataBase64.setRecord(HCEUtils.hexStringToBase64String(hceRecordData.getRecord().replace(" ","")));
                recordDataBase64.add(hceRecordDataBase64);
            }

            hceRecordFileBase64.setRecordData(recordDataBase64);
            hceRecordFilesBase64.add(hceRecordFileBase64);
        }
        hceCardDataBase64.setRecordFiles(hceRecordFilesBase64);

        String json = (new Gson()).toJson(hceCardDataBase64);


    }



    private void parseSFI_07(HCECardData hceCardData) {
        HCERecordFile sfi_07_record = FilterUtils.INSTANCE.getHCERecordBySFICode(hceCardData.getRecordFiles(), SFICodeType.SFI07.code());
        if (sfi_07_record == null) {
            if (readCallback != null) {
                readCallback.onReadError(null);
            }
            return;
        }

        ArrayList<HCERecordData> hceRecordDataList = sfi_07_record.getRecordData();
        ArrayList<EnvironmentResult> environmentResults = new ArrayList<>();

        for (int i = 0; i < hceRecordDataList.size(); i++) {
            String recBinaryData = getHexToBin(hceRecordDataList.get(i).getRecord());
            getResultSFI07(recBinaryData);
        }
    }

    private void parseSFI_09(HCECardData hceCardData) {
        HCERecordFile sfi_09_record = FilterUtils.INSTANCE.getHCERecordBySFICode(hceCardData.getRecordFiles(), SFICodeType.SFI09.code());
        if (sfi_09_record == null) {
            if (readCallback != null) {
                readCallback.onReadError(null);
            }
            return;
        }

        ArrayList<HCERecordData> hceRecordDataList = sfi_09_record.getRecordData();

        for (int i = 0; i < hceRecordDataList.size(); i++) {
            String recBinaryData = getHexToBin(hceRecordDataList.get(i).getRecord());
            getResultSFI09(recBinaryData);
        }
    }

    private void getResultSFI07(String recBinaryData) {
        Log.i("NSBF record binary : ", recBinaryData);
        ArrayList<HCERules> envRules = getMappingRuleAccess().provideHCEEnvironmentRules();
        ArrayList<HCERules> holderRules = getMappingRuleAccess().provideHCEHolderRules();

        ArrayList<HCEResult> envResult = getParseResult(envRules, recBinaryData);
        ArrayList<HCEResult> hldResult = getParseResult(holderRules, recBinaryData);

        EnvironmentResult environmentResult = new EnvironmentResult();
        environmentResult.setEnvVersionNumber(FilterUtils.INSTANCE.getEnvVersion(envResult));
        environmentResult.setEnvironment(FilterUtils.INSTANCE.getEnvironment(envResult));
        environmentResult.setHolder(FilterUtils.INSTANCE.getHolder(hldResult));
    }

    private void getResultSFI09(String recBinaryData) {
        Log.i("NSBF record binary : ", recBinaryData);
        ArrayList<HCERules> contractRules = getMappingRuleAccess().provideHCEContractRules();
        ArrayList<HCEResult> contractResult = getParseResult(contractRules, recBinaryData);
    }

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
        return HCEUtils.hexStringToBinaryString(hexStr);
    }

    private IMappingRule getMappingRuleAccess() {
        return CoreProvider.getInstance().provideMappingRuleAccess();
    }
}
