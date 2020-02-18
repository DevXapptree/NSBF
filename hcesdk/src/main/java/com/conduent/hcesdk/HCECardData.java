package com.conduent.hcesdk;

import java.io.Serializable;
import java.util.ArrayList;

public class HCECardData implements Serializable {

    private String AnswerSelectApplication;
    private String AnswerSelectFileRT;
    private ArrayList<HCERecordFile> recordFiles;

    public HCECardData() {

    }

    public HCECardData(String AnswerSelectApplication, String AnswerSelectFileRT, ArrayList<HCERecordFile> recordFiles) {
        this.AnswerSelectApplication = AnswerSelectApplication;
        this.AnswerSelectFileRT = AnswerSelectFileRT;
        this.recordFiles = recordFiles;
    }

    public String getAnswerSelectApplication() {
        return AnswerSelectApplication;
    }

    public void setAnswerSelectApplication(String answerSelectApplication) {
        AnswerSelectApplication = answerSelectApplication;
    }

    public String getAnswerSelectFileRT() {
        return AnswerSelectFileRT;
    }

    public void setAnswerSelectFileRT(String answerSelectFileRT) {
        AnswerSelectFileRT = answerSelectFileRT;
    }

    public ArrayList<HCERecordFile> getRecordFiles() {
        return recordFiles;
    }

    public void setRecordFiles(ArrayList<HCERecordFile> recordFiles) {
        this.recordFiles = recordFiles;
    }
}
