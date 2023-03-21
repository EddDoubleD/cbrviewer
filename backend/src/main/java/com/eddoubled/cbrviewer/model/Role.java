package com.eddoubled.cbrviewer.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document
public class Role {
    @Id
    private String id;

    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }
}
