package com.conduent.hcesdk.core;

import com.conduent.hcesdk.entities.*;
import com.conduent.hcesdk.entities.valuesapi.ValuesApiResponse;

import java.util.ArrayList;

public interface IMappingRule {
    MappingEnvironmentHolder provideHCEMappingEnvHolder();
    MappingContracts provideHCEMappingContract();
    MappingContractList provideHCEMappingContractList();
    MappingTransportLog provideHCEMappingTransportLog();
    ArrayList<HCERules> provideHCEEnvironmentRules();
    ArrayList<HCERules> provideHCEHolderRules();
    ArrayList<HCERules> provideHCEContractRules();
    ArrayList<HCERules> provideHCETransportLogRules();
    ValuesApiResponse provideValuesApiResponse();
}
