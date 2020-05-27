package net.miiingle.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import net.miiingle.api.Registration;
import net.miiingle.api.repository.RegistrationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository repository;

    public Registration partiallyUpdate(Long id, JsonNode changes) {
        var registration = repository.getOne(id);

        if (changes.has("firstName") && changes.get("firstName").isTextual()) {
            registration.setFirstName(changes.get("firstName").asText());
        }

        if (changes.has("lastName") && changes.get("lastName").isTextual()) {
            registration.setFirstName(changes.get("lastName").asText());
        }

        if (changes.has("fullName") && changes.get("fullName").isTextual()) {
            registration.setFirstName(changes.get("fullName").asText());
        }

        if (changes.has("type") && changes.get("type").isTextual()) {
            registration.setType(Registration.Type.valueOf(changes.get("type").asText()));
        }


        return repository.save(registration);
    }

    public Registration update(Long id, Registration updatedRegistration) {
        var registration = repository.getOne(id);

        BeanUtils.copyProperties(updatedRegistration, registration, "id");

        return repository.save(registration);
    }
}
