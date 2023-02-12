package com.ozgeakdas.credit.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Document(collection = "credit")
public class Credit {
    @Id
    private String id;
    private String result;
    private Integer limit;
    @OneToOne(mappedBy = "credit", fetch = FetchType.LAZY)
    private Customer customer;
}
