package net.miiingle.api.repository;

import net.miiingle.api.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Page<Registration> findByFirstName(String firstName, Pageable page);
}
