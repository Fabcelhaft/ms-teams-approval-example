package net.fabcelhaft.msteamsapprovalexample.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.fabcelhaft.msteamsapprovalexample.dto.CloudCreationDTO;
import net.fabcelhaft.msteamsapprovalexample.dto.CloudResultDTO;
import net.fabcelhaft.msteamsapprovalexample.dto.CreationDTO;
import net.fabcelhaft.msteamsapprovalexample.model.Approvement;
import net.fabcelhaft.msteamsapprovalexample.repository.ApprovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@RestController
@Slf4j
public class ApprovementController {

    private ApprovementRepository approvementRepository;

    private RestTemplate restTemplate;

    @Autowired
    public ApprovementController(ApprovementRepository approvementRepository, RestTemplate restTemplate) {
        this.approvementRepository = approvementRepository;
        this.restTemplate = restTemplate;
    }

    @PutMapping("/approvements/result")
    public void result(@RequestBody CloudResultDTO resultDTO){
        Optional<Approvement> approvementOptional = approvementRepository.findById(resultDTO.getId());
        if (approvementOptional.isEmpty()){
            log.error("For ID {} no approvement avaiable ({})", resultDTO.getId(), resultDTO);
            return;
        }
        Approvement approvement = approvementOptional.get();
        approvement.setResult(resultDTO.getResult());
        approvement.setApprovers(resultDTO.getApprovers());
        approvementRepository.save(approvement);
    }

    @PostMapping("approvements/gui")
    public Approvement newApprovement(@RequestBody CreationDTO creationDTO) throws JsonProcessingException, MalformedURLException, URISyntaxException {
        Approvement approvement = new Approvement(creationDTO);
        approvementRepository.save(approvement);

        CloudCreationDTO cloudCreationDTO = new CloudCreationDTO(approvement);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreationDTO> entity = new HttpEntity<>(cloudCreationDTO,headers);

        ResponseEntity<String> answer = restTemplate.exchange(new URI(SettingsController.cloudUrl), HttpMethod.POST, entity, String.class);

        return approvement;
    }
}
