package com.conduent.hcesdk.core;

import android.os.AsyncTask;

import com.conduent.hcesdk.RetrieveRemoteOfferCallback;
import com.conduent.hcesdk.entities.remoteoffer.response.BuildMedia;
import com.conduent.hcesdk.entities.remoteoffer.response.Product;
import com.conduent.hcesdk.entities.remoteoffer.response.RemoteResponse;

import java.util.ArrayList;

public class RetrieveRemoteOfferAsync extends AsyncTask<Void, String, RemoteResponse> {
    private BuildMedia mediaData;
    private RetrieveRemoteOfferCallback remoteOfferCallback;

    RetrieveRemoteOfferAsync(BuildMedia mediaData, RetrieveRemoteOfferCallback remoteOfferCallback){
        this.mediaData = mediaData;
        this.remoteOfferCallback = remoteOfferCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected RemoteResponse doInBackground(Void... voids) {
//        RemoteResponse remoteResponse = new RemoteResponse();
//        remoteResponse.setArticleID();
        ArrayList<RemoteResponse> remoteResponses = parseMediaData(mediaData);

        return null;
    }

    @Override
    protected void onPostExecute(RemoteResponse remoteResponse) {
        super.onPostExecute(remoteResponse);
    }

    private ArrayList<RemoteResponse> parseMediaData(BuildMedia mediaData) {

        ArrayList<RemoteResponse> remoteResponses = new ArrayList<>();

        for(Product mProduct: mediaData.getProducts().getProduct()){
            RemoteResponse remoteResponse = new RemoteResponse();
            remoteResponse.setArticleID(mProduct.getId());
            remoteResponse.setProductID(mProduct.getProductCode());
            remoteResponse.setTitleOffer(mProduct.getTitleProduct());
            remoteResponse.setUnitPrice(mProduct.getAmount());
            remoteResponse.setVatRate(mProduct.getVatRate());

            remoteResponses.add(remoteResponse);
        }


        return remoteResponses;
    }
}
