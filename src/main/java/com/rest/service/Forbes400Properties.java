package com.rest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Forbes400Properties {

    @Value("${forbes400.billionaire.api}")
    private String api;

    @Value("${forbes400.billionaire.maxrecord}")
    private int maxrecord;

    public void setApi(String api) {
        this.api = api;
    }

    public void setMaxrecord(int maxrecord) {
        this.maxrecord = maxrecord;
    }

    public String getApi() {
        return api;
    }

    public int getMaxrecord() {
        return maxrecord;
    }

    public String buildEndPoint() {
        StringBuilder sb = new StringBuilder();
        sb.append(api);
        sb.append("?limit=");
        sb.append(maxrecord);
        return sb.toString();
    }
}
