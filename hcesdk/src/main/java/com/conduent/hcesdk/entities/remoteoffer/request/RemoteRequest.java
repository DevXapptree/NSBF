package com.conduent.hcesdk.entities.remoteoffer.request;

import java.io.Serializable;

public class RemoteRequest implements Serializable {

    private MediaInformations MediaInformations;

    private FiltersMediaManager FiltersMediaManager;

    private ContextWebApi ContextWebApi;

    public MediaInformations getMediaInformations ()
    {
        return MediaInformations;
    }

    public void setMediaInformations (MediaInformations MediaInformations)
    {
        this.MediaInformations = MediaInformations;
    }

    public FiltersMediaManager getFiltersMediaManager ()
    {
        return FiltersMediaManager;
    }

    public void setFiltersMediaManager (FiltersMediaManager FiltersMediaManager)
    {
        this.FiltersMediaManager = FiltersMediaManager;
    }

    public ContextWebApi getContextWebApi ()
    {
        return ContextWebApi;
    }

    public void setContextWebApi (ContextWebApi ContextWebApi)
    {
        this.ContextWebApi = ContextWebApi;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MediaInformations = "+MediaInformations+", FiltersMediaManager = "+FiltersMediaManager+", ContextWebApi = "+ContextWebApi+"]";
    }
}
