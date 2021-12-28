package com.ibm.nfvodriver.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController("AvailabilityController")
@RequestMapping("/api")
public class AvailabilityController {

    @GetMapping
    @ApiOperation(value = "Test Availability", notes = "Returns a string indicating the current health and availability of the driver")
    public ResponseEntity<String> testAvailability() {
        return ResponseEntity.ok("OK");
    }

}
