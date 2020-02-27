package com.conduent.hcesdk.network;

import okhttp3.OkHttpClient;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

final class RetrofitProvider {
    private static RetrofitProvider mInstance;
    private RetrofitConfig configuration;

    static RetrofitProvider getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitProvider();
        }
        return mInstance;
    }

    void setConfiguration(RetrofitConfig configuration) {
        this.configuration = configuration;
    }

    RetrofitConfig getConfiguration() {
        return configuration;
    }


    /*OkHTTP Client*/
    OkHttpClient getOkHttpClient() {

        return new OkHttpClient.Builder()
                .connectTimeout(configuration.getTimeOut(), TimeUnit.MILLISECONDS)
                .writeTimeout(configuration.getTimeOut(), TimeUnit.MILLISECONDS)
                .readTimeout(configuration.getTimeOut(), TimeUnit.MILLISECONDS)
                .build();
    }

    OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(configuration.getTimeOut(), TimeUnit.MILLISECONDS);
            builder.writeTimeout(configuration.getTimeOut(), TimeUnit.MILLISECONDS);
            builder.readTimeout(configuration.getTimeOut(), TimeUnit.MILLISECONDS);
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    OkHttpClient getUnSafeClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
//
//            int cacheSize = 10 * 1024 * 1024; // 10 MB
//            Cache cache = new Cache(context.getCacheDir(), cacheSize);
//            builder.cache(cache);
            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
