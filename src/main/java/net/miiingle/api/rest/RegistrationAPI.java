package net.miiingle.api.rest;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.miiingle.api.Registration;
import net.miiingle.api.repository.RegistrationRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("registrations")
@RequiredArgsConstructor
public class RegistrationAPI {

    private final RegistrationRepository repository;
    private final PagedResourcesAssembler<Registration> assembler;

    @GetMapping
    public PagedModel<EntityModel<Registration>> findAll(Pageable page) {
        var pagedModel = assembler.toModel(repository.findAll(page));
        pagedModel.add(linkTo(methodOn(RegistrationAPI.class).search(page)).withRel(IanaLinkRelations.SEARCH));

        return pagedModel;
    }

    @GetMapping("search")
    public PagedModel<EntityModel<Registration>> search(Pageable page) {
        var searchModel = assembler.toModel(repository.findAll(Pageable.unpaged()));

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
                .map(employee -> new EntityModel<>(employee,
                        linkTo(methodOn(RegistrationAPI.class).findOne(employee.getId())).withSelfRel(),
                        linkTo(methodOn(RegistrationAPI.class).findAll(Pageable.unpaged())).withRel("registrations")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
}
