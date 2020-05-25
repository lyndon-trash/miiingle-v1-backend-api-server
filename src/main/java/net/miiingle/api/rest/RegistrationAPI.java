package net.miiingle.api.rest;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.miiingle.api.Registration;
import net.miiingle.api.repository.RegistrationRepository;
import net.miiingle.api.service.RegistrationService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("registrations")
@RequiredArgsConstructor
public class RegistrationAPI {

    private final RegistrationRepository repository;
    private final RegistrationService service;
    private final PagedResourcesAssembler<Registration> assembler;

    @GetMapping
    public PagedModel<EntityModel<Registration>> findAll(Pageable page) {
        var pagedModel = assembler.toModel(repository.findAll(page));
        pagedModel.add(linkTo(methodOn(RegistrationAPI.class).search(page)).withRel(IanaLinkRelations.SEARCH));

        return pagedModel;
    }

    @GetMapping("search")
    public PagedModel<EntityModel<Registration>> search(Pageable page) {
        var searchModel = assembler.toModel(repository.findAll(page));

        searchModel.add(linkTo(methodOn(RegistrationAPI.class).findByFirstName(null, null)).withRel("byFirstName"));

        return searchModel;
    }

    @GetMapping("search/byFirstName")
    public PagedModel<EntityModel<Registration>> findByFirstName(@RequestParam(value = "firstName", required = false) String firstName, Pageable page) {
        return assembler.toModel(repository.findByFirstName(firstName, page));
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<Registration>> findOne(@PathVariable long id) {
        return repository.findById(id)
                .map(this::toEntityModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private EntityModel<Registration> toEntityModel(Registration registration) {
        var clazz = RegistrationAPI.class;
        var id = registration.getId();

        var findOneLink = linkTo(methodOn(RegistrationAPI.class).findOne(id)).withSelfRel()
                .andAffordance(afford(methodOn(clazz).partiallyUpdate(id, null)))
                .andAffordance(afford(methodOn(clazz).update(id, null)));

        return new EntityModel<>(registration,
                findOneLink,
                linkTo(methodOn(RegistrationAPI.class).findAll(Pageable.unpaged())).withRel("registrations"));
    }

    @SneakyThrows
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EntityModel<Registration>> save(@RequestBody Registration registration) {
            var savedRegistration = repository.save(registration);
            var linkToFindOne = linkTo(methodOn(RegistrationAPI.class).findOne(savedRegistration.getId())).withSelfRel();
            var employeeResource = new EntityModel<>(savedRegistration, linkToFindOne);

            return ResponseEntity
                    .created(new URI(employeeResource.getRequiredLink(IanaLinkRelations.SELF).getHref()))
                    .body(employeeResource);
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Registration>> update(@PathVariable Long id, @RequestBody Registration registration) {
        return ResponseEntity.ok(toEntityModel(service.update(id, registration)));
    }

    @PatchMapping("{id}")
    public ResponseEntity<EntityModel<Registration>> partiallyUpdate(@PathVariable Long id, @RequestBody JsonNode changes) {
        return ResponseEntity.ok(toEntityModel(service.partiallyUpdate(id, changes)));
    }
}
