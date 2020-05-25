package net.miiingle.api;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty
    String firstName;

    @NotEmpty
    String lastName;

    @NotEmpty
    String fullName;

    @NotNull
    @Enumerated(EnumType.STRING)
    Type type;

    enum Type {
        FACEBOOK,
        EMAIL,
    }
}
