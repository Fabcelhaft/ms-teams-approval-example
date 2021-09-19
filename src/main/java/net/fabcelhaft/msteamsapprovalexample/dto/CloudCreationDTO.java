package net.fabcelhaft.msteamsapprovalexample.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.fabcelhaft.msteamsapprovalexample.controller.SettingsController;
import net.fabcelhaft.msteamsapprovalexample.model.Approvement;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CloudCreationDTO extends CreationDTO{
    public CloudCreationDTO(Approvement approvment){
        this.setApprovers(approvment.getPotentialApprovers());
        this.setName(approvment.getName());
        this.setDescription(approvment.getDescription());
        this.setAllApproversNeeded(approvment.getAllApproversNeeded());
        this.id=approvment.getId();
        this.url= SettingsController.baseUrl+"/approvements/" + approvment.getId();
    }

    private Long id;

    private String url;
}
