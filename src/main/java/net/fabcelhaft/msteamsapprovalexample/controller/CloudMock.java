package net.fabcelhaft.msteamsapprovalexample.controller;

import lombok.extern.slf4j.Slf4j;
import net.fabcelhaft.msteamsapprovalexample.dto.CloudCreationDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CloudMock {
    @PostMapping("/cloudmock")
    public void createCloudFlow(@RequestBody CloudCreationDTO cloudCreationDTO){
        log.warn("Mock-Server Called: {}", cloudCreationDTO);
    }
}
