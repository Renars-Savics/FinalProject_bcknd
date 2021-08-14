package com.company.controller;

import com.company.dto.ServerInfoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/rest/serverInfo.svc")
public class InfoController {

    @Value("${com.company.environment}")
    private String environment;

    private Date startDate;

    @PostConstruct
    private void init(){
        startDate = new Date();
    }

    @GetMapping("/info")
    public ServerInfoDTO getServerInfo(){
        ServerInfoDTO serverInfoDTO = new ServerInfoDTO();
        Date date = new Date();
        serverInfoDTO.setServerDate(date);
        serverInfoDTO.setEnvironment(environment);
        serverInfoDTO.setStartDate(startDate);
        return serverInfoDTO;
    }
}
