package org.study.SpringStarter.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.JoinColumn;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @jakarta.validation.constraints.Size(min = 8, message = "Password should be at least 6 characters long")
    private String password;

    @NotEmpty(message = "First name should not be empty")
    private String firstname;

    @NotEmpty(message = "last name should not be empty")
    private String lastname;

    private String gender;

    private int age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_of_birth;

    private String photo;

    private String role;

    private String token;

    private LocalDateTime password_reset_token_expiry;

    @OneToMany(mappedBy = "account")
    private List<Post> posts;

    @ManyToMany
    @JoinTable(name = "account_authority",

            joinColumns = { @JoinColumn(name = "account_id", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "authority_id", referencedColumnName = "id") })
    private Set<Authority> authorities = new HashSet<>();
}
