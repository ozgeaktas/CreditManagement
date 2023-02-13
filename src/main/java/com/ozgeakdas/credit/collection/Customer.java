package com.ozgeakdas.credit.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
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
@Document(collection = "customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {
    @Id
    private String id;
    private String identityNumber;
    private String firstName;
    private String lastName;
    private Integer salary;
    private String phoneNumber;
    private LocalDate birthDate;
    private Integer creditScore;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Credit credit;
}
