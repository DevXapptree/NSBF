package com.conduent.hcesdk.core;

import com.conduent.hcesdk.HCECardData;
import com.conduent.hcesdk.HCERecordData;
import com.conduent.hcesdk.HCERecordFile;
import com.conduent.hcesdk.ReadCallback;
import com.conduent.hcesdk.utils.HCEUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

class CoreParser implements ICoreParser {

    private static volatile ICoreParser instance;
    //FIXME Currently no usage, will remove once step2 in done.
    private ReadCallback readCallback;

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
    public void startParsingHCE(HCECardData hceCardData, ReadCallback readCallback) {
        this.readCallback = readCallback;
        /*Execute HCEParseAsync*/
        new HCEParseAsync(hceCardData, readCallback).execute();
    }

    @Override
    public HCECardData startRemoteParsingHCE(HCECardData hceCardData) {
        HCECardData hceCardDataBase64 = new HCECardData();

        hceCardDataBase64.setAnswerSelectApplication(HCEUtils.HexStringToBase64String(hceCardData.getAnswerSelectApplication().replace(" ","")));
        hceCardDataBase64.setAnswerSelectFileRT(HCEUtils.HexStringToBase64String(hceCardData.getAnswerSelectFileRT().replace(" ","")));

        ArrayList<HCERecordFile> hceRecordFilesBase64 = new ArrayList<>();
        for(HCERecordFile hceRecordFile: hceCardData.getRecordFiles()){
            HCERecordFile hceRecordFileBase64 = new HCERecordFile();
            hceRecordFileBase64.setSFI(hceRecordFile.getSFI());

            ArrayList<HCERecordData> recordDataBase64 = new ArrayList<>();
            for(HCERecordData hceRecordData: hceRecordFile.getRecordData()){
                HCERecordData hceRecordDataBase64 = new HCERecordData();
                hceRecordDataBase64.setRecord(HCEUtils.HexStringToBase64String(hceRecordData.getRecord().replace(" ","")));
                recordDataBase64.add(hceRecordDataBase64);
            }

            hceRecordFileBase64.setRecordData(recordDataBase64);
            hceRecordFilesBase64.add(hceRecordFileBase64);
        }
        hceCardDataBase64.setRecordFiles(hceRecordFilesBase64);

        String json = (new Gson()).toJson(hceCardDataBase64);

        return hceCardDataBase64;
    }

    @Override
    public long startParsingMediaSerialNumber(HCECardData hceCardData) {
        try {
            String applicationData = hceCardData.getAnswerSelectApplication().replace(" ","");
            int count = Integer.parseInt(applicationData.substring(52, 54));
            String mediaSerialNumber = applicationData.substring(54, 54+(count*2));

            return HCEUtils.HexStringToDecimal(mediaSerialNumber);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Long.parseLong(null);
        }
    }

    @Override
    public String convertHexToBinary(String hexData) {
        return HCEUtils.HexStringToBinaryString(hexData);
    }
}
