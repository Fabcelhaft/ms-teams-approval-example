package net.fabcelhaft.msteamsapprovalexample.dto;

import lombok.Data;

@Data
public class CloudResultDTO {
    private Long id;
    private String approvers;
    private String result;
}
