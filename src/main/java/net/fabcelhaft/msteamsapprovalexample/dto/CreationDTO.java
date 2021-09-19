package net.fabcelhaft.msteamsapprovalexample.dto;

import lombok.Data;

@Data
public class CreationDTO {
    private String name;

    private String description;

    //Semicolon separated
    private String approvers;
    
    private Boolean allApproversNeeded;
}
