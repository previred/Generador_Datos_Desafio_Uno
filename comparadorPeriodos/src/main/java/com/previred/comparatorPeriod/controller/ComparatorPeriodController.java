package com.previred.comparatorPeriod.controller;

import com.google.gson.Gson;
import com.previred.comparatorPeriod.Response;
import com.previred.comparatorPeriod.client.GddClient;
import com.previred.comparatorPeriod.service.ComparatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Jorge Godoy (JG)
 */
@RestController
@Slf4j
public class ComparatorPeriodController {

    @Autowired
    private GddClient gddClient;

    @Autowired
    private ComparatorService comparatorService;

    @GetMapping(value = "/comparator-period/compare", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> compare(){
        Response response = comparatorService.setMissingDate(gddClient.getPeriod());
        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

}
