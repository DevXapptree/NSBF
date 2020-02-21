package com.conduent.hcesdk.core;

import android.os.AsyncTask;

import com.conduent.hcesdk.RetrieveRemoteOfferCallback;
import com.conduent.hcesdk.entities.remoteoffer.response.AvailableZonesPrices;
import com.conduent.hcesdk.entities.remoteoffer.response.BuildMedia;
import com.conduent.hcesdk.entities.remoteoffer.response.DDates;
import com.conduent.hcesdk.entities.remoteoffer.response.DatesZonesPrices;
import com.conduent.hcesdk.entities.remoteoffer.response.Product;
import com.conduent.hcesdk.entities.remoteoffer.response.RemoteResponse;
import com.conduent.hcesdk.entities.remoteoffer.response.ZonesPrices;
import com.conduent.hcesdk.utils.FilterUtils;

import java.util.ArrayList;

public class RetrieveRemoteOfferAsync extends AsyncTask<Void, String, ArrayList<RemoteResponse>> {
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
    protected ArrayList<RemoteResponse> doInBackground(Void... voids) {
//        RemoteResponse remoteResponse = new RemoteResponse();
//        remoteResponse.setArticleID();
        ArrayList<RemoteResponse> remoteResponses = parseMediaData(mediaData);

        return remoteResponses;
    }

    @Override
    protected void onPostExecute(ArrayList<RemoteResponse> remoteResponse) {
        super.onPostExecute(remoteResponse);
        remoteOfferCallback.onEnded();


    }

    /**
     * Precess media data or preparing response
     * @param mediaData media data after processing card data
     * @return ArrayList<RemoteResponse>
     */
    private ArrayList<RemoteResponse> parseMediaData(BuildMedia mediaData) {

        ArrayList<RemoteResponse> remoteResponses = new ArrayList<>();

        for(Product mProduct: mediaData.getProducts().getProduct()){
            RemoteResponse remoteResponse = new RemoteResponse();
            remoteResponse.setArticleID(mProduct.getId());
            remoteResponse.setProductID(mProduct.getProductCode());
            remoteResponse.setTitleOffer(mProduct.getTitleProduct());
            remoteResponse.setUnitPrice(mProduct.getAmount());
            remoteResponse.setVatRate(mProduct.getVatRate());
            remoteResponse.setNetworkID(mProduct.getNetworkCode());
            remoteResponse.setItemCategory(mProduct.getItemCategory());

            ArrayList<DatesZonesPrices> pricesArrayList = new ArrayList<>();


            if (mProduct.getItemDatesZonesPricesInfo() != null) {
                DatesZonesPrices datesZonesPrices = new DatesZonesPrices();

                ArrayList<ZonesPrices> zPrices = new ArrayList<>();
                for (AvailableZonesPrices availableZonesPrices : mProduct.getItemDatesZonesPricesInfo().get(0).getAvailableZonesPrices()) {
                    ZonesPrices zonesPrices = new ZonesPrices();
                    zonesPrices.setUnitPrice(availableZonesPrices.getUnitPrice());
                    zonesPrices.setVatRate(availableZonesPrices.getVatRate());
                    zonesPrices.setZoneID(availableZonesPrices.getId());
                    zonesPrices.setZoneLabel(FilterUtils.INSTANCE.getZonalLabel(HCEEngine.localInstance().getContext(), availableZonesPrices.getId()));
                    zPrices.add(zonesPrices);
                }
                datesZonesPrices.setZonesPrices(zPrices);


                ArrayList<DDates> dates = new ArrayList<>();
                for (DDates dDates : mProduct.getItemDatesZonesPricesInfo().get(0).getDates()) {
                    DDates mDates = new DDates();
                    mDates.setDuration(dDates.getDuration());
                    mDates.setStep(dDates.getStep());
                    mDates.setMaxStartDate(dDates.getMaxStartDate());
                    mDates.setMinStartDate(dDates.getMinStartDate());
                    mDates.setUnit(dDates.getUnit());
                    dates.add(mDates);
                }
                datesZonesPrices.setDates(dates);

                pricesArrayList.add(datesZonesPrices);

            } else {
                pricesArrayList.add(null);
            }


            remoteResponse.setDatesZonesPrices(pricesArrayList);
            remoteResponse.setRequiresAuth(FilterUtils.INSTANCE.getRequiredAuth(mProduct.getProductCodeHex(), HCEEngine.localInstance().getContext()));
            remoteResponse.setCounter(FilterUtils.INSTANCE.getCounterValue(mProduct.getProductCodeHex(), HCEEngine.localInstance().getContext()));
            remoteResponse.setCustomData(FilterUtils.INSTANCE.getContractCustomData(mProduct.getProductCodeHex(), HCEEngine.localInstance().getContext()));

            remoteResponses.add(remoteResponse);
        }

        return remoteResponses;
    }
}
