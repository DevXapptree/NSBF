package com.conduent.hcesdk.room;

import android.content.Context;
import android.os.AsyncTask;
import com.conduent.hcesdk.entities.valuesapi.*;
import com.conduent.hcesdk.room.tables.*;

import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryAsync extends AsyncTask<Void, String, Boolean> {

    private OnDataBaseQueryListener onDataBaseQueryListener;
    private Context context;
    private int code;
    private VersionTable versionData;
    private ValuesApiResponse valuesResponse;

    public DatabaseQueryAsync(Context context, int code, OnDataBaseQueryListener onDataBaseQueryListener) {
        this.context = context;
        this.code = code;
        this.onDataBaseQueryListener = onDataBaseQueryListener;
    }

    public DatabaseQueryAsync(Context context, int code, ValuesApiResponse valuesResponse) {
        this.context = context;
        this.code = code;
        this.valuesResponse = valuesResponse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            switch (code) {
                case RoomRequestCodes.INSERT_VALUES_API_FILE:
                    /*Insert Version*/
                    VersionTable versionTable = new VersionTable();
                    versionTable.setVersionId(valuesResponse.getVersion().getId());
                    versionTable.setComment(valuesResponse.getVersion().getComment());
                    AppRoomDataBase.getDatabase(context).valuesAPIDao().insertReplaceVersion(versionTable);

                    /*Environment Network*/
                    EnvNetworkIdTable envNetworkIdTable = new EnvNetworkIdTable();
                    DataValue country = new DataValue();
                    country.setId(valuesResponse.getContentEnvironment().getEnvNetworkId().getCountry().getId());
                    country.setValue(valuesResponse.getContentEnvironment().getEnvNetworkId().getCountry().getValue());
                    envNetworkIdTable.setCountry(country);

                    DataValue network = new DataValue();
                    network.setId(valuesResponse.getContentEnvironment().getEnvNetworkId().getNetwork().getId());
                    network.setValue(valuesResponse.getContentEnvironment().getEnvNetworkId().getNetwork().getValue());
                    envNetworkIdTable.setNetwork(network);

                    AppRoomDataBase.getDatabase(context).valuesAPIDao().insertReplaceEnvNetwork(envNetworkIdTable);

                    /*Environment IssuerId*/
                    List<EnvApplicationIssuerIdTable> envApplicationIssuerIdList = new ArrayList<>();
                    for (Value issuer : valuesResponse.getContentEnvironment().getEnvApplicationIssuerId()) {
                        EnvApplicationIssuerIdTable envApplicationIssuerIdTable = new EnvApplicationIssuerIdTable();
                        DataValue dataValue = new DataValue();
                        dataValue.setId(issuer.getId());
                        dataValue.setValue(issuer.getValue());
                        envApplicationIssuerIdTable.setIssuer(dataValue);
                        envApplicationIssuerIdList.add(envApplicationIssuerIdTable);
                    }
                    AppRoomDataBase.getDatabase(context).valuesAPIDao().insertReplaceEnvAppIssuerIdBatch(envApplicationIssuerIdList);

                    /*Holder DataCard Status*/
                    List<HolderDataCardStatusTable> holderDataCardStatusTableList = new ArrayList<>();
                    for (Value holderDataCard : valuesResponse.getContentEnvironment().getHolderData().getHolderDataCardStatus()) {
                        HolderDataCardStatusTable holderDataCardStatusTable = new HolderDataCardStatusTable();
                        DataValue dataValue = new DataValue();
                        dataValue.setId(holderDataCard.getId());
                        dataValue.setValue(holderDataCard.getValue());
                        holderDataCardStatusTable.setCardStatus(dataValue);
                        holderDataCardStatusTableList.add(holderDataCardStatusTable);
                    }
                    AppRoomDataBase.getDatabase(context).valuesAPIDao().insertReplaceHolderCardStatusBatch(holderDataCardStatusTableList);

                    /*Holder Data Commercial*/
                    List<HolderDataCommercialTable> holderDataCommercialTableList = new ArrayList<>();
                    for (Value holderCommercial : valuesResponse.getContentEnvironment().getHolderData().getHolderDataCommercialId()) {
                        HolderDataCommercialTable holderDataCommercialTable = new HolderDataCommercialTable();
                        DataValue dataValue = new DataValue();
                        dataValue.setId(holderCommercial.getId());
                        dataValue.setValue(holderCommercial.getValue());
                        holderDataCommercialTable.setCommercial(dataValue);
                        holderDataCommercialTableList.add(holderDataCommercialTable);
                    }
                    AppRoomDataBase.getDatabase(context).valuesAPIDao().insertReplaceHolderDataCommercialIdBatch(holderDataCommercialTableList);

                    /*Holder Profile*/
                    List<HolderProfileNumberTable> holderProfileNumberTableList = new ArrayList<>();
                    for (Value holderProfile : valuesResponse.getContentEnvironment().getHolderProfileNumber()) {
                        HolderProfileNumberTable holderProfileNumberTable = new HolderProfileNumberTable();
                        DataValue dataValue = new DataValue();
                        dataValue.setId(holderProfile.getId());
                        dataValue.setValue(holderProfile.getValue());
                        holderProfileNumberTable.setProfile(dataValue);
                        holderProfileNumberTableList.add(holderProfileNumberTable);
                    }
                    AppRoomDataBase.getDatabase(context).valuesAPIDao().insertReplaceHolderProfileNumberBatch(holderProfileNumberTableList);

                    /*Insert Contract Status*/
                    List<ContractStatusTable> contractStatusList = new ArrayList<>();
                    for (Value contract : valuesResponse.getContractData().getContractStatus()) {
                        ContractStatusTable contractStatusTable = new ContractStatusTable();
                        DataValue contractStatus = new DataValue();
                        contractStatus.setId(contract.getId());
                        contractStatus.setValue(contract.getValue());
                        contractStatusTable.setStatus(contractStatus);
                        contractStatusList.add(contractStatusTable);
                    }
                    AppRoomDataBase.getDatabase(context).valuesAPIDao().insertContractStatusBatch(contractStatusList);

                    /*Insert Customer Profiles*/
                    List<ContractCustomerProfileTable> contraCustomerProfileList = new ArrayList<>();
                    for (Value profile : valuesResponse.getContractData().getContractCustomerProfile()) {
                        ContractCustomerProfileTable contractCustomerProfileTable = new ContractCustomerProfileTable();
                        DataValue contractProfile = new DataValue();
                        contractProfile.setId(profile.getId());
                        contractProfile.setValue(profile.getValue());
                        contractCustomerProfileTable.setData(contractProfile);
                        contraCustomerProfileList.add(contractCustomerProfileTable);
                    }
                    AppRoomDataBase.getDatabase(context).valuesAPIDao().insertContractCustomerProfileBatch(contraCustomerProfileList);

                    /*Insert Contract Validity Zones*/
                    List<ContractValidityZonesTable> contractValidityZoneList = new ArrayList<>();
                    for (Zone zone : valuesResponse.getContractData().getContractValidityZones()) {
                        ContractValidityZonesTable contractValidityZonesTable = new ContractValidityZonesTable();
                        ContractZone contractZone = new ContractZone();
                        contractZone.setId(zone.getId());
                        contractZone.setZoneLabel(zone.getZoneLabel());
                        contractValidityZonesTable.setContractZone(contractZone);
                        contractValidityZoneList.add(contractValidityZonesTable);
                    }
                    AppRoomDataBase.getDatabase(context).valuesAPIDao().insertContractValidityZoneBatch(contractValidityZoneList);

                    /*Insert Product*/
                    List<ProductTable> productList = new ArrayList<>();
                    for (Product product : valuesResponse.getContractData().getProducts()) {
                        ProductTable productTable = new ProductTable();
                        /*ProductId*/
                        productTable.setProductId(product.getProductId());
                        /*CustomData*/
                        CustomData customData = new CustomData();
                        customData.setCustomData1(product.getCustomData().getCustomData1());
                        customData.setCustomData2(product.getCustomData().getCustomData2());
                        customData.setCustomData3(product.getCustomData().getCustomData3());
                        customData.setCustomData4(product.getCustomData().getCustomData4());
                        productTable.setCustomData(customData);

                        /*Auth User/CommercialId*/
                        ArrayList<AuthUser> authList = new ArrayList<>();
                        for (CommercialId auth : product.getAuthUser()) {
                            AuthUser authUser = new AuthUser();
                            authUser.setHolderDataCommercialId(auth.getHolderDataCommercialId());
                            authList.add(authUser);
                        }
                        productTable.setAuthUser(authList);

                        /*Description*/
                        /*Desc FR*/
                        DescriptionLanguage fr = new DescriptionLanguage();
                        fr.setTitle(product.getDescription().getFr().getTitle());
                        fr.setShortDesc(product.getDescription().getFr().getShortDesc());
                        fr.setLongDesc(product.getDescription().getFr().getLongDesc());

                        ArrayList<DescriptionLangResource> descFrResourceList = new ArrayList<>();
                        for (ProductDescriptionResource descResource : product.getDescription().getFr().getResources()) {
                            DescriptionLangResource descriptionLangResource = new DescriptionLangResource();
                            descriptionLangResource.setTag(descResource.getTag());
                            descriptionLangResource.setValue(descResource.getValue());
                            descFrResourceList.add(descriptionLangResource);
                        }
                        fr.setResources(descFrResourceList);

                        /*Desc EN*/
                        DescriptionLanguage en = new DescriptionLanguage();
                        en.setTitle(product.getDescription().getEn().getTitle());
                        en.setShortDesc(product.getDescription().getEn().getShortDesc());
                        en.setLongDesc(product.getDescription().getEn().getLongDesc());

                        ArrayList<DescriptionLangResource> descEnResourceList = new ArrayList<>();
                        for (ProductDescriptionResource descResource : product.getDescription().getEn().getResources()) {
                            DescriptionLangResource descriptionLangResource = new DescriptionLangResource();
                            descriptionLangResource.setTag(descResource.getTag());
                            descriptionLangResource.setValue(descResource.getValue());
                            descEnResourceList.add(descriptionLangResource);
                        }
                        en.setResources(descEnResourceList);

                        /*Desc ES*/
                        DescriptionLanguage es = new DescriptionLanguage();
                        es.setTitle(product.getDescription().getEs().getTitle());
                        es.setShortDesc(product.getDescription().getEs().getShortDesc());
                        es.setLongDesc(product.getDescription().getEs().getLongDesc());

                        ArrayList<DescriptionLangResource> descEsResourceList = new ArrayList<>();
                        for (ProductDescriptionResource descResource : product.getDescription().getEs().getResources()) {
                            DescriptionLangResource descriptionLangResource = new DescriptionLangResource();
                            descriptionLangResource.setTag(descResource.getTag());
                            descriptionLangResource.setValue(descResource.getValue());
                            descEsResourceList.add(descriptionLangResource);
                        }
                        es.setResources(descEsResourceList);

                        /*Desc DE*/
                        DescriptionLanguage de = new DescriptionLanguage();
                        de.setTitle(product.getDescription().getDe().getTitle());
                        de.setShortDesc(product.getDescription().getDe().getShortDesc());
                        de.setLongDesc(product.getDescription().getDe().getLongDesc());

                        ArrayList<DescriptionLangResource> descDeResourceList = new ArrayList<>();
                        for (ProductDescriptionResource descResource : product.getDescription().getDe().getResources()) {
                            DescriptionLangResource descriptionLangResource = new DescriptionLangResource();
                            descriptionLangResource.setTag(descResource.getTag());
                            descriptionLangResource.setValue(descResource.getValue());
                            descDeResourceList.add(descriptionLangResource);
                        }
                        de.setResources(descDeResourceList);

                        Description description = new Description();
                        description.setFr(fr);
                        description.setEn(en);
                        description.setEs(es);
                        description.setDe(de);

                        productTable.setDescription(description);
                        productList.add(productTable);

                    }
                    AppRoomDataBase.getDatabase(context).valuesAPIDao().insertProductsBatch(productList);
                    break;
                case RoomRequestCodes.GET_VERSION_FILE:
                    List<VersionTable> arrVersion = new ArrayList<>();
                    arrVersion = AppRoomDataBase.getDatabase(context).valuesAPIDao().getVersion();
                    List<EnvNetworkIdTable> arrEnvNet = AppRoomDataBase.getDatabase(context).valuesAPIDao().getEnvNetwork();
                    List<EnvApplicationIssuerIdTable> arrEnvIssuerId = AppRoomDataBase.getDatabase(context).valuesAPIDao().getEnvIssuer();
                    List<HolderDataCardStatusTable> arrHolderCardStatus = AppRoomDataBase.getDatabase(context).valuesAPIDao().getHolderCardStatus();
                    List<HolderDataCommercialTable> arrHolderCommercial = AppRoomDataBase.getDatabase(context).valuesAPIDao().getHolderCommercial();
                    List<HolderProfileNumberTable> arrHolderProfile = AppRoomDataBase.getDatabase(context).valuesAPIDao().getHolderProfile();
                    List<ContractStatusTable> arrStatus = AppRoomDataBase.getDatabase(context).valuesAPIDao().getStatus();
                    List<ContractCustomerProfileTable> arrProfile = AppRoomDataBase.getDatabase(context).valuesAPIDao().getProfile();
                    List<ContractValidityZonesTable> arrZones = AppRoomDataBase.getDatabase(context).valuesAPIDao().getZones();
                    List<ProductTable> arrProducts = AppRoomDataBase.getDatabase(context).valuesAPIDao().getProducts();
                    if (arrVersion.size() > 0) {
                        versionData = arrVersion.get(0);
                    }
                    break;
                case RoomRequestCodes.GET_OFFLINE_FILES:
                    break;

                default:
                    break;

            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (result) {
            switch (code) {
                case RoomRequestCodes.GET_VERSION_FILE:
                    onDataBaseQueryListener.onDataFetched(versionData);
                    break;
                case RoomRequestCodes.GET_OFFLINE_FILES:
                    break;
                default:
                    break;
            }
        }

    }
}
