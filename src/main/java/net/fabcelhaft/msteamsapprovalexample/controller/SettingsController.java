package net.fabcelhaft.msteamsapprovalexample.controller;


import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettingsController {

    //Bad practice
    public static String baseUrl="https://ms-teams-approval.fabcelhaft.net";
    //Bad practice
    public static String cloudUrl="https://ms-teams-approval.fabcelhaft.net/cloudmock";

    @PutMapping("/url/base")
    public void setBaseUrl(String baseUrl) {
        SettingsController.baseUrl = baseUrl;
    }

    @PutMapping("/url/cloud")
    public void setCloudUrl(String cloudUrl) {
        SettingsController.cloudUrl = cloudUrl;
    }
}
