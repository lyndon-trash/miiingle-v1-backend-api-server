package net.miiingle.api.rest;

import lombok.RequiredArgsConstructor;
import net.miiingle.api.Registration;
import net.miiingle.api.repository.RegistrationRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("registrations")
@RequiredArgsConstructor
public class RegistrationAPI {

    private final RegistrationRepository registrationRepository;
    private final PagedResourcesAssembler<Registration> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<Registration>> findAll(Pageable page) {
        return pagedResourcesAssembler.toModel(registrationRepository.findAll(page));
    }

    @PostMapping
    public Registration save(@RequestBody Registration registration) {
        return registrationRepository.save(registration);
    }
}
