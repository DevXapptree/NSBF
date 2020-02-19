package com.conduent.hcesdk.entities.remoteoffer.request;

import java.io.Serializable;

public class ContextWebApi implements Serializable {

    private String CultureName;

    private boolean CadBufferReadOffLine;

    private String SalesMode;

    private String EquipmentReference;

    private String AccessMode;

    private String SessionId;

    private String AccessType;

    public ContextWebApi() {
        this.CultureName = "fr-FR";
        this.CadBufferReadOffLine = true;
        this.SalesMode = "CAD";
        this.EquipmentReference = "10TW-ViaNavigo00001";
        this.AccessMode = "ConnectedMedia";
        this.SessionId = "devhce";
        this.AccessType = "Mobile";
    }

    public String getCultureName() {
        return CultureName;
    }

    public void setCultureName(String CultureName) {
        this.CultureName = CultureName;
    }

    public boolean getCadBufferReadOffLine() {
        return CadBufferReadOffLine;
    }

    public void setCadBufferReadOffLine(boolean CadBufferReadOffLine) {
        this.CadBufferReadOffLine = CadBufferReadOffLine;
    }

    public String getSalesMode() {
        return SalesMode;
    }

    public void setSalesMode(String SalesMode) {
        this.SalesMode = SalesMode;
    }

    public String getEquipmentReference() {
        return EquipmentReference;
    }

    public void setEquipmentReference(String EquipmentReference) {
        this.EquipmentReference = EquipmentReference;
    }

    public String getAccessMode() {
        return AccessMode;
    }

    public void setAccessMode(String AccessMode) {
        this.AccessMode = AccessMode;
    }

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String SessionId) {
        this.SessionId = SessionId;
    }

    public String getAccessType() {
        return AccessType;
    }

    public void setAccessType(String AccessType) {
        this.AccessType = AccessType;
    }

    @Override
    public String toString() {
        return "ClassPojo [CultureName = " + CultureName + ", CadBufferReadOffLine = " + CadBufferReadOffLine + ", SalesMode = " + SalesMode + ", EquipmentReference = " + EquipmentReference + ", AccessMode = " + AccessMode + ", SessionId = " + SessionId + ", AccessType = " + AccessType + "]";
    }
}
