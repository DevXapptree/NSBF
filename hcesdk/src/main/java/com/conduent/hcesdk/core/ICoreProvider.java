package com.conduent.hcesdk.core;

public interface ICoreProvider {
    IHCECore provideHCECoreAccess();

    IMappingRule provideMappingRuleAccess();

    IHCENetwork provideHCENeworkAccess();
}
