package net.fabcelhaft.msteamsapprovalexample.repository;

import net.fabcelhaft.msteamsapprovalexample.model.Approvement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface ApprovementRepository extends CrudRepository<Approvement, Long> {
}
