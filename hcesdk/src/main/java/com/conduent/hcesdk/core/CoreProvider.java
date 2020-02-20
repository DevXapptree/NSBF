package com.conduent.hcesdk.core;

class CoreProvider implements ICoreProvider {

    private static volatile ICoreProvider instance;

    private CoreProvider() {

    }

    static ICoreProvider getInstance() {
        if (instance == null) {
            synchronized (CoreProvider.class) {
                if (instance == null)
                    instance = new CoreProvider();
            }
        }
        return instance;
    }

    @Override
    public IHCECore provideHCECoreAccess() {
        return new HCECore();
    }

    @Override
    public IMappingRule provideMappingRuleAccess() {
        return MappingRule.getInstance();
    }
}
