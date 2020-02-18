package com.conduent.hcesdk.network;

public class ServiceGenerator {
    private Apiservice apiInterface;
    private static volatile ServiceGenerator instance;

    private ServiceGenerator() {
    }

    public static ServiceGenerator Instance() {
        if (instance == null) {
            synchronized (ServiceGenerator.class) {
                if (instance == null) {
                    instance = new ServiceGenerator();
                }
            }
        }

        return instance;
    }

    public Apiservice getService() {
        if (this.apiInterface == null) {
            this.apiInterface = RetrofitHelper.getClient().create(Apiservice.class);
        }
        return this.apiInterface;
    }
}
