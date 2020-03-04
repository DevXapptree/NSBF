package com.conduent.hcerepo.entities;

public class BufferData {
    private BufferImage buffersImage;
    private String cardImageSeqN;
    private String mediaSerialNumber;

    public BufferData() {
    }

    public BufferImage getBuffersImage() {
        return buffersImage;
    }

    public void setBuffersImage(BufferImage buffersImage) {
        this.buffersImage = buffersImage;
    }

    public String getCardImageSeqN() {
        return cardImageSeqN;
    }

    public void setCardImageSeqN(String cardImageSeqN) {
        this.cardImageSeqN = cardImageSeqN;
    }

    public String getMediaSerialNumber() {
        return mediaSerialNumber;
    }

    public void setMediaSerialNumber(String mediaSerialNumber) {
        this.mediaSerialNumber = mediaSerialNumber;
    }
}
