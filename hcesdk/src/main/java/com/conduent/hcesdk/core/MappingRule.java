package com.conduent.hcesdk.core;

import com.conduent.hcesdk.entities.*;
import com.conduent.hcesdk.entities.valuesapi.ValuesApiResponse;
import com.conduent.hcesdk.utils.HCETag;
import com.conduent.hcesdk.utils.HCEUtils;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;

final class MappingRule implements IMappingRule {

    private MappingEnvironmentHolder mappingEnvironmentHolder;
    private MappingContracts mappingContracts;
    private MappingContractList mappingContractList;
    private MappingTransportLog mappingTransportLog;
    private ValuesApiResponse valuesApiResponse;
    private static volatile IMappingRule instance;

    private MappingRule() {

    }

    public static IMappingRule getInstance() {
        if (instance == null) {
            synchronized (MappingRule.class) {
                if (instance == null)
                    instance = new MappingRule();
            }
        }
        return instance;
    }

    @Override
    public MappingEnvironmentHolder provideHCEMappingEnvHolder() {
        if (mappingEnvironmentHolder == null) {
            try {
                InputStream is = HCEEngine.localInstance().getContext().getAssets().open("MappingEnvironmentHolder.json");
                String mapEnvHolder = HCEUtils.convertStreamToString(is);
                mappingEnvironmentHolder = new Gson().fromJson(mapEnvHolder, MappingEnvironmentHolder.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mappingEnvironmentHolder;
    }

    @Override
    public MappingContracts provideHCEMappingContract() {
        if (mappingContracts == null) {
            try {
                InputStream is = HCEEngine.localInstance().getContext().getAssets().open("MappingContract.json");
                String mapContract = HCEUtils.convertStreamToString(is);
                mappingContracts = new Gson().fromJson(mapContract, MappingContracts.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mappingContracts;
    }

    @Override
    public MappingContractList provideHCEMappingContractList() {
        if (mappingContractList == null) {
            try {
                InputStream is = HCEEngine.localInstance().getContext().getAssets().open("MappingContractsList.json");
                String mapContract = HCEUtils.convertStreamToString(is);
                mappingContractList = new Gson().fromJson(mapContract, MappingContractList.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mappingContractList;
    }

    @Override
    public MappingTransportLog provideHCEMappingTransportLog() {
        if (mappingTransportLog == null) {
            try {
                InputStream is = HCEEngine.localInstance().getContext().getAssets().open("MappingTransportLog.json");
                String mapContract = HCEUtils.convertStreamToString(is);
                mappingTransportLog = new Gson().fromJson(mapContract, MappingTransportLog.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mappingTransportLog;
    }

    @Override
    public ArrayList<HCERules> provideHCEEnvironmentRules() {
        Environment environment = provideHCEMappingEnvHolder().getMappingCard().getEnvironment();
        ArrayList<HCERules> envRules = new ArrayList<>();
        if (environment == null) {
            return envRules;
        }
        envRules.add(new HCERules(HCETag.ENV_APP_VERSION_NUMBER, environment.getEnvApplicationVersionNumber()));
        envRules.add(new HCERules(HCETag.ENV_BITMAP, environment.getEnvBitmap()));
        envRules.add(new HCERules(HCETag.ENV_NETWORK_ID, environment.getEnvNetworkId()));
        envRules.add(new HCERules(HCETag.ENV_APP_ISSUER_ID, environment.getEnvApplicationIssuerId()));
        envRules.add(new HCERules(HCETag.ENV_APP_END_DATE, environment.getEnvApplicationEndDate()));
        envRules.add(new HCERules(HCETag.ENV_AUTHENTICATOR, environment.getEnvAuthenticator()));
        envRules.add(new HCERules(HCETag.ENV_DATA_BITMAP, environment.getEnvDataBitmap()));
        envRules.add(new HCERules(HCETag.ENV_DATA_CARD_STATUS, environment.getEnvDataCardStatus()));
        return envRules;
    }

    @Override
    public ArrayList<HCERules> provideHCEHolderRules() {
        Holder holder = provideHCEMappingEnvHolder().getMappingCard().getHolder();
        ArrayList<HCERules> holderRules = new ArrayList<>();
        if (holder == null) {
            return holderRules;
        }
        holderRules.add(new HCERules(HCETag.HOLDER_BITMAP, holder.getHolderBitmap()));
        holderRules.add(new HCERules(HCETag.HOLDER_BIRTH, holder.getHolderBirth()));
        holderRules.add(new HCERules(HCETag.HOLDER_BIRTH_DATE, holder.getHolderBirthDate()));
        holderRules.add(new HCERules(HCETag.HOLDER_ID_NUMBER, holder.getHolderIdNumber()));
        holderRules.add(new HCERules(HCETag.HOLDER_PROFILES, holder.getHolderProfiles()));
        holderRules.add(new HCERules(HCETag.HOLDER_PROFILE_BITMAP1, holder.getHolderProfileBitmap1()));
        holderRules.add(new HCERules(HCETag.HOLDER_NETWORK_ID1, holder.getHolderNetworkId1()));
        holderRules.add(new HCERules(HCETag.HOLDER_PROFILE_NUMBER1, holder.getHolderProfileNumber1()));
        holderRules.add(new HCERules(HCETag.HOLDER_PROFILE_DATE1, holder.getHolderProfileDate1()));
        holderRules.add(new HCERules(HCETag.HOLDER_PROFILE_BITMAP2, holder.getHolderProfileBitmap2()));
        holderRules.add(new HCERules(HCETag.HOLDER_NETWORK_ID2, holder.getHolderNetworkId2()));
        holderRules.add(new HCERules(HCETag.HOLDER_PROFILE_NUMBER2, holder.getHolderProfileNumber2()));
        holderRules.add(new HCERules(HCETag.HOLDER_PROFILE_DATE2, holder.getHolderProfileDate2()));
        holderRules.add(new HCERules(HCETag.HOLDER_PROFILE_BITMAP3, holder.getHolderProfileBitmap3()));
        holderRules.add(new HCERules(HCETag.HOLDER_NETWORK_ID3, holder.getHolderNetworkId3()));
        holderRules.add(new HCERules(HCETag.HOLDER_PROFILE_NUMBER3, holder.getHolderProfileNumber3()));
        holderRules.add(new HCERules(HCETag.HOLDER_PROFILE_DATE3, holder.getHolderProfileDate3()));
        holderRules.add(new HCERules(HCETag.HOLDER_PROFILE_BITMAP4, holder.getHolderProfileBitmap4()));
        holderRules.add(new HCERules(HCETag.HOLDER_NETWORK_ID4, holder.getHolderNetworkId4()));
        holderRules.add(new HCERules(HCETag.HOLDER_PROFILE_NUMBER4, holder.getHolderProfileNumber4()));
        holderRules.add(new HCERules(HCETag.HOLDER_PROFILE_DATE4, holder.getHolderProfileDate4()));
        holderRules.add(new HCERules(HCETag.HOLDER_DATA, holder.getHolderData()));
        holderRules.add(new HCERules(HCETag.HOLDER_DATA_CARD_STATUS, holder.getHolderDataCardStatus()));
        holderRules.add(new HCERules(HCETag.HOLDER_DATA_TELE_REGLEMENT, holder.getHolderDataTeleReglement()));
        holderRules.add(new HCERules(HCETag.HOLDER_DATA_COMMERCIAL_ID, holder.getHolderDataCommercialID()));
        holderRules.add(new HCERules(HCETag.HOLDER_DATA_PROFILE_START_DATE1, holder.getHolderDataProfileStartDate1()));
        holderRules.add(new HCERules(HCETag.HOLDER_DATA_PROFILE_START_DATE2, holder.getHolderDataProfileStartDate2()));
        holderRules.add(new HCERules(HCETag.HOLDER_DATA_PROFILE_START_DATE3, holder.getHolderDataProfileStartDate3()));
        holderRules.add(new HCERules(HCETag.HOLDER_DATA_PROFILE_START_DATE4, holder.getHolderDataProfileStartDate4()));
        return holderRules;
    }

    @Override
    public ArrayList<HCERules> provideHCEContractRules() {
        Contract contract = provideHCEMappingContract().getMappingCard().getContracts();
        ArrayList<HCERules> contractRules = new ArrayList<>();
        if (contract == null) {
            return contractRules;
        }
        contractRules.add(new HCERules(HCETag.CONTRACT_BITMAP, contract.getContractBitmap()));
        contractRules.add(new HCERules(HCETag.CONTRACT_PROVIDER, contract.getContractProvider()));
        contractRules.add(new HCERules(HCETag.CONTRACT_TRAFFIC, contract.getContractTariff()));
        contractRules.add(new HCERules(HCETag.CONTRACT_SERIAL_NUMBER, contract.getContractSerialNumber()));
        contractRules.add(new HCERules(HCETag.CONTRACT_CUSTOMER_INFO, contract.getContractCustomerInfo()));
        contractRules.add(new HCERules(HCETag.CONTRACT_CUSTOMER_PROFILE, contract.getContractCustomerProfile()));
        contractRules.add(new HCERules(HCETag.CONTRACT_PAY_METHOD, contract.getContractPayMethod()));
        contractRules.add(new HCERules(HCETag.CONTRACT_PRICE_AMOUNT, contract.getContractPriceAmount()));
        contractRules.add(new HCERules(HCETag.CONTRACT_RESTRICTION, contract.getContractRestriction()));
        contractRules.add(new HCERules(HCETag.CONTRACT_RESTRICTION_CODE, contract.getContractRestrictCode()));
        contractRules.add(new HCERules(HCETag.CONTRACT_VALIDITY_INFO, contract.getContractValidityInfo()));
        contractRules.add(new HCERules(HCETag.CONTRACT_VALIDITY_START_DATE, contract.getContractValidityStartDate()));
        contractRules.add(new HCERules(HCETag.CONTRACT_VALIDITY_START_TIME, contract.getContractValidityStartTime()));
        contractRules.add(new HCERules(HCETag.CONTRACT_VALIDITY_END_DATE, contract.getContractValidityEndDate()));
        contractRules.add(new HCERules(HCETag.CONTRACT_VALIDITY_END_TIME, contract.getContractValidityEndTime()));
        contractRules.add(new HCERules(HCETag.CONTRACT_VALIDITY_ZONES, contract.getContractValidityZones()));
        contractRules.add(new HCERules(HCETag.CONTRACT_VALIDITY_JOURNEYS, contract.getContractValidityJourneys()));
        contractRules.add(new HCERules(HCETag.CONTRACT_JOURNEY_DATA, contract.getContractJourneyData()));
        contractRules.add(new HCERules(HCETag.CONTRACT_JOURNEY_ORIGIN, contract.getContractJourneyOrigin()));
        contractRules.add(new HCERules(HCETag.CONTRACT_JOURNEY_DESTINATION, contract.getContractJourneyDestination()));
        contractRules.add(new HCERules(HCETag.CONTRACT_JOURNEY_ROUTES_NUMBERS, contract.getContractJourneyRoutesNumbers()));
        contractRules.add(new HCERules(HCETag.CONTRACT_SALE_DATA, contract.getContractSaleData()));
        contractRules.add(new HCERules(HCETag.CONTRACT_SALE_DATE, contract.getContractSaleDate()));
        contractRules.add(new HCERules(HCETag.CONTRACT_SALE_AGENT, contract.getContractSaleAgent()));
        contractRules.add(new HCERules(HCETag.CONTRACT_SALE_DEVICE, contract.getContractSaleDevice()));
        contractRules.add(new HCERules(HCETag.CONTRACT_STATUS, contract.getContractStatus()));
        contractRules.add(new HCERules(HCETag.CONTRACT_AUTHENTICATOR, contract.getContractAuthenticator()));
        return contractRules;
    }

    @Override
    public ArrayList<HCERules> provideHCETransportLogRules() {
        TransportLog transportLog = provideHCEMappingTransportLog().getMappingCard().getTransportLog();
        ArrayList<HCERules> transportRules = new ArrayList<>();
        if (transportLog == null) {
            return transportRules;
        }
        transportRules.add(new HCERules(HCETag.EVENT_DATE_STAMP, transportLog.getEventDateStamp()));
        transportRules.add(new HCERules(HCETag.EVENT_TIME_STAMP, transportLog.getEventTimeStamp()));
        transportRules.add(new HCERules(HCETag.EVENT_BITMAP, transportLog.getEventBitmap()));
        transportRules.add(new HCERules(HCETag.EVENT_CODE, transportLog.getEventCode()));
        transportRules.add(new HCERules(HCETag.EVENT_SERVICE_PROVIDER, transportLog.getEventServiceProvider()));
        transportRules.add(new HCERules(HCETag.EVENT_NOTOK_COUNTER, transportLog.getEventNotokCounter()));
        transportRules.add(new HCERules(HCETag.EVENT_SERIAL_NUMBER, transportLog.getEventSerialNumber()));
        transportRules.add(new HCERules(HCETag.EVENT_LOCATION_ID, transportLog.getEventLocationId()));
        transportRules.add(new HCERules(HCETag.EVENT_LOCATION_GATE, transportLog.getEventLocationGate()));
        transportRules.add(new HCERules(HCETag.EVENT_DEVICE, transportLog.getEventDevice()));
        transportRules.add(new HCERules(HCETag.EVENT_ROUTING_NUMBER, transportLog.getEventRouteNumber()));
        transportRules.add(new HCERules(HCETag.EVENT_JOURNEY_RUN, transportLog.getEventJourneyRun()));
        transportRules.add(new HCERules(HCETag.EVENT_VEHICLE_ID, transportLog.getEventVehicleId()));
        transportRules.add(new HCERules(HCETag.EVENT_CONTRACT_POINTER, transportLog.getEventContractPointer()));
        transportRules.add(new HCERules(HCETag.EVENT_DATA, transportLog.getEventData()));
        transportRules.add(new HCERules(HCETag.EVENT_DATA_DATE_FIRST_STAMP, transportLog.getEventDataDateFirstStamp()));
        transportRules.add(new HCERules(HCETag.EVENT_DATA_TIME_FIRST_STAMP, transportLog.getEventDataTimeFirstStamp()));
        return transportRules;
    }

    @Override
    public ValuesApiResponse provideValuesApiResponse() {
        if (valuesApiResponse == null) {
            try {
                InputStream is = HCEEngine.localInstance().getContext().getAssets().open("values_response_api.json");
                String valuesApi = HCEUtils.convertStreamToString(is);
                valuesApiResponse = new Gson().fromJson(valuesApi, ValuesApiResponse.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return valuesApiResponse;
    }
}
