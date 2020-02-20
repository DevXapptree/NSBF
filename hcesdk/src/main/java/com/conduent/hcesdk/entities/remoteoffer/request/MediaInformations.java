package com.conduent.hcesdk.entities.remoteoffer.request;

import com.conduent.hcesdk.HCECardData;

import java.io.Serializable;

public class MediaInformations implements Serializable {

    private String MediaSerialNumber;

    private HCECardData BuffersImage;

    private int MediaUnicity;

    public String getMediaSerialNumber ()
    {
        return MediaSerialNumber;
    }

    public void setMediaSerialNumber (String MediaSerialNumber)
    {
        this.MediaSerialNumber = MediaSerialNumber;
    }

    public HCECardData getBuffersImage ()
    {
        return BuffersImage;
    }

    public void setBuffersImage (HCECardData BuffersImage)
    {
        this.BuffersImage = BuffersImage;
    }

    public int getMediaUnicity ()
    {
        return MediaUnicity;
    }

    public void setMediaUnicity (int MediaUnicity)
    {
        this.MediaUnicity = MediaUnicity;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MediaSerialNumber = "+MediaSerialNumber+", BuffersImage = "+BuffersImage+", MediaUnicity = "+MediaUnicity+"]";
    }
}
