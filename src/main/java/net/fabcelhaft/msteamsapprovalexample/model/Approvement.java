package net.fabcelhaft.msteamsapprovalexample.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.fabcelhaft.msteamsapprovalexample.dto.CreationDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Approvement {

    @Id
    @GeneratedValue
    private Long id;

    private String potentialApprovers;
    private Boolean allApproversNeeded;
    private String description;
    private String name;

    //Ergebnis
    private String result;
    private String approvers;

    public Approvement(CreationDTO creationDTO){
        name = creationDTO.getName();
        description = creationDTO.getDescription();
        potentialApprovers = creationDTO.getApprovers();
        allApproversNeeded = creationDTO.getAllApproversNeeded();
    }
}
