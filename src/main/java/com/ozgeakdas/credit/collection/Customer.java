package com.ozgeakdas.credit.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Document(collection = "customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {
    @Id
    private String id;
    @NotNull(message = "Identity number is not blank!")
    private String identityNumber;
    @NotBlank(message = "Name is not blank!")
    private String firstName;
    @NotBlank(message = "Last name is not blank!")
    private String lastName;
    @NotNull(message = "Salary is not blank!")
    private Integer salary;
    @NotNull(message = "Phone number is not blank!")
    @Pattern(regexp="(^$|[0-9]{11})",message = "Please enter a valid phone number!")
    private String phoneNumber;
    private LocalDate birthDate;
    private Integer creditScore;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Credit credit;
}
