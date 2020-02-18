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
    //private RecordFile recordFile;
    private String id;
    private int count;
    //private ArrayList<RecordFile> arrRecordFiles;
    private VersionTable versionData;
    private ValuesApiResponse valuesResponse;
    private Version version;

    public DatabaseQueryAsync(Context context, int code) {
        this.context = context;
        this.code = code;
    }

    public DatabaseQueryAsync(Context context, int code, String id) {
        this.context = context;
        this.code = code;
        this.id = id;
    }

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
                        for (DescriptionResource descResource : product.getDescription().getFr().getResources()) {
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
                        for (DescriptionResource descResource : product.getDescription().getEn().getResources()) {
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
                        for (DescriptionResource descResource : product.getDescription().getEs().getResources()) {
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
                        for (DescriptionResource descResource : product.getDescription().getDe().getResources()) {
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

                case RoomRequestCodes.GET_ALL_FILES:
                    // arrRecordFiles = new ArrayList<>();
                    // List<RecordFile> arrFiles = AppRoomDataBase.getDatabase(context).recordFileDao().getAllRecordFiles();
                    break;

                case RoomRequestCodes.INSERT_FILE:
                    // AppRoomDataBase.getDatabase(context).recordFileDao().insertAll(recordFile);
                    break;
                case RoomRequestCodes.DELETE_FILE:
                    // AppRoomDataBase.getDatabase(context).recordFileDao().delete(recordFile);
                    break;
                case RoomRequestCodes.UPDATE_FILE:
                    // AppRoomDataBase.getDatabase(context).recordFileDao().update(recordFile);
                    break;
                case RoomRequestCodes.GET_VERSION_FILE:
                    List<VersionTable> arrVersion = new ArrayList<>();
                    arrVersion = AppRoomDataBase.getDatabase(context).valuesAPIDao().getVersion();
                    List<EnvNetworkIdTable> arrEnvNet = AppRoomDataBase.getDatabase(context).valuesAPIDao().getEnvNetwork();
                    List<ContractStatusTable> arrStatus = AppRoomDataBase.getDatabase(context).valuesAPIDao().getStatus();
                    List<ContractCustomerProfileTable> arrProfile = AppRoomDataBase.getDatabase(context).valuesAPIDao().getProfile();
                    List<ContractValidityZonesTable> arrZones = AppRoomDataBase.getDatabase(context).valuesAPIDao().getZones();
                    List<ProductTable> arrProducts = AppRoomDataBase.getDatabase(context).valuesAPIDao().getProducts();
                    if (arrVersion.size() > 0) {
                        versionData = arrVersion.get(0);
                    }
                    break;
                case RoomRequestCodes.GET_OFFLINE_FILES:
                    //count = AppRoomDataBase.getDatabase(context).recordFileDao().getOfflineFileCount(false);
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
                    //onDataBaseQueryListener.onCountFetched(count);
                    break;
                default:
                    break;
            }
        }

    }
}
