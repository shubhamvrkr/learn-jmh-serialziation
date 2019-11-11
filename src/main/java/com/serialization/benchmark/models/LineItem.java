package com.serialization.benchmark.models;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LineItem  implements Serializable {

    private String poLineSeqNBR;
    private String styleCD;
    private String subStyleCD;
    private String sku;
    private String description;
    private int quantity;
    private String unitOfMeasurement;
    private Double price;
    private Double volume;
    private String volumeUOM;
    private Double weight;
    private String weightUOM;
    private Long dueDate;
    private String status;

}
