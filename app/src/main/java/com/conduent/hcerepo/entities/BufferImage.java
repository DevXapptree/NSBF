package com.conduent.hcerepo.entities;

import java.util.ArrayList;

public class BufferImage {

    public BufferImage() {
    }

    private String profileAID;

    public String getProfileAID() {
        return profileAID;
    }

    public void setProfileAID(String profileAID) {
        this.profileAID = profileAID;
    }

    public ArrayList<RecordFile> getRecordFiles() {
        return recordFiles;
    }

    public void setRecordFiles(ArrayList<RecordFile> recordFiles) {
        this.recordFiles = recordFiles;
    }

    private ArrayList<RecordFile> recordFiles;
}
