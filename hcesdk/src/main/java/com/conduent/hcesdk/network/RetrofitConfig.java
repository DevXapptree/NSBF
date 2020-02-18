package com.conduent.hcesdk.network;

import android.text.TextUtils;

public class RetrofitConfig {
    private String base_url;
    private int timeout;

    private RetrofitConfig(Builder builder) {
        this.base_url = builder.base_url;
        this.timeout = builder.timeout;
        RetrofitProvider.getInstance().setConfiguration(this);
    }

    String getBaseUrl() {
        return base_url;
    }

    int getTimeOut() {
        return timeout;
    }

    public static class Builder {
        private String base_url;
        private int timeout = 20000;

        public Builder() {

        }

        public Builder setBaseUrl(String base_url) {
            this.base_url = base_url;
            return this;
        }

        public Builder setTimeOut(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public RetrofitConfig build() {
            if (TextUtils.isEmpty(base_url)) {
                throw new IllegalArgumentException("Missing base url, URL is required and it is manditory for further process.");
            }
            return new RetrofitConfig(this);
        }

    }
}
