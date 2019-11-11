package com.serialization.benchmark.models;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
/**
 * POJO class to hold purchase order details.
 * Used during testing the serialization and deserialization process
 */
public class PurchaseOrder  implements Serializable {

    private String id;
    private Long poDate;
    private String poRequistionedBy;
    private Long poRecieveDate;
    private Long updatedOn;
    private String updatedBy;
    private String status;
    private String sellerDLTId;
    private String sellerNumber;
    private String sellerName;
    private String sellerAddress;
    private String sellerTaxId;
    private String buyerDLTId;
    private String buyerName;
    private String buyerTaxId;
    private String billingAddress;
    private String shipToAddress;
    private double poAmount;
    private DocumentReference documentMetadata;
    private Long poExpDate;
    private List<LineItem> poLineItems;
}
