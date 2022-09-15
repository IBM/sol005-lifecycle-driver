package com.ibm.nfvodriver.web.etsi;


import com.ibm.nfvodriver.service.ExternalMessagingService;
import io.swagger.annotations.ApiOperation;
import org.etsi.sol005.nsd.NsdNotification;
import org.etsi.sol005.nsd.NsdOnboardingNotification;
import org.etsi.sol005.nsd.PnfdOnboardingNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("NsdNotificationController")
@RequestMapping("/nsd/v2/notifications")
public class NsdNotificationController {

    private final static Logger logger = LoggerFactory.getLogger(NsdNotificationController.class);

    private final ExternalMessagingService externalMessagingService;

    @Autowired
    public NsdNotificationController(ExternalMessagingService externalMessagingService) {
        this.externalMessagingService = externalMessagingService;
    }

    @PostMapping
    @ApiOperation(value = "Receives a NSD or PNFD notification from a NFVO", code = 204)
    public ResponseEntity<Void> receiveNotification(@RequestBody NsdNotification notification) {
        // TODO This should be reduced to DEBUG level, but it assists in development testing to see all notification messages being received
        logger.info("Received notification:\n{}", notification);

        if (notification instanceof NsdOnboardingNotification) {
            // handle NsdOnboardingNotification type.
        }
        // Handle other different types of NSD notifications here

        if (notification instanceof PnfdOnboardingNotification) {
            // handle PnfdOnboardingNotification type.
        }
        // Handle other different types of PNFD notifications here


        return ResponseEntity.noContent().build();
    }

}
