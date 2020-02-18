package com.conduent.hcesdk.core;

import com.conduent.hcesdk.entities.HCERules;
import com.conduent.hcesdk.entities.MappingContracts;
import com.conduent.hcesdk.entities.MappingEnvironmentHolder;
import com.conduent.hcesdk.entities.valuesapi.ValuesApiResponse;

import java.util.ArrayList;

public interface IMappingRule {
    MappingEnvironmentHolder provideHCEMappingEnvHolder();
    MappingContracts provideHCEMappingContract();
    ArrayList<HCERules> provideHCEEnvironmentRules();
    ArrayList<HCERules> provideHCEHolderRules();
    ArrayList<HCERules> provideHCEContractRules();
    ValuesApiResponse provideValuesApiResponse();
}
