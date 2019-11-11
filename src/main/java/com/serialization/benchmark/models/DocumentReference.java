package com.serialization.benchmark.models;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentReference implements Serializable {
    private String docId;
    private double version;
    private String docName;
    private String docHash;
    private String docType;
}
