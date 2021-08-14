package com.company.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ServerInfoDTO {

    @JsonProperty("server_date")
    @JsonFormat(pattern="dd.MM.yyyy@HH:mm:ss")
    private Date serverDate;

    @JsonProperty("start_date")
    @JsonFormat(pattern="dd.MM.yyyy@HH:mm:ss")
    private Date startDate;

    @JsonProperty("environment")
    private String environment;


    public Date getServerDate() {
        return serverDate;
    }

    public void setServerDate(Date serverDate) {
        this.serverDate = serverDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
