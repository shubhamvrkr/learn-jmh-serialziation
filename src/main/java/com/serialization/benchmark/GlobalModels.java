package com.serialization.benchmark;

import com.serialization.benchmark.models.DocumentReference;
import com.serialization.benchmark.models.LineItem;
import com.serialization.benchmark.models.PurchaseOrder;
import com.serialization.benchmark.models.protobuf.DocumentReferenceProtos;
import com.serialization.benchmark.models.protobuf.LineItemProtos;
import com.serialization.benchmark.models.protobuf.PurchaseOrderProtos;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GlobalModels {

    static PurchaseOrder purchaseOrder;
    static com.serialization.benchmark.models.thrifmodels.PurchaseOrder thriftPurchaseOrder;
    static PurchaseOrderProtos.PurchaseOrder protobufPurchaseOrder;

    public static PurchaseOrder createTestModel() {
        if (purchaseOrder != null) {
            return purchaseOrder;
        } else {

            DocumentReference documentReference = new DocumentReference();
            documentReference.setDocId("PO1011");
            documentReference.setDocName("Purchase Order");
            documentReference.setDocType("png");
            documentReference.setVersion(1.0);
            documentReference.setDocHash("112e476505aab51b05aeb2246c02a11df03e1187e886f7c55d4e9935c290ade");

            List<LineItem> lineItems = new ArrayList<>();
            LineItem l1 = new LineItem("P10001", "styleCD", "subStyleCD", "1289630237", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10,
                    "unitOfMeasurement", 100.0, 10.0, "volumeUOM", 150.0,
                    "weightUOM", Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond(), "CREATED");
            LineItem l2 = new LineItem("P10001", "styleCD", "subStyleCD", "1289630237", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10,
                    "unitOfMeasurement", 100.0, 10.0, "volumeUOM", 150.0,
                    "weightUOM", Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond(), "CREATED");
            LineItem l3 = new LineItem("P10001", "styleCD", "subStyleCD", "1289630237", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10,
                    "unitOfMeasurement", 100.0, 10.0, "volumeUOM", 150.0,
                    "weightUOM", Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond(), "CREATED");
            LineItem l4 = new LineItem("P10001", "styleCD", "subStyleCD", "1289630237", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10,
                    "unitOfMeasurement", 100.0, 10.0, "volumeUOM", 150.0,
                    "weightUOM", Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond(), "CREATED");
            LineItem l5 = new LineItem("P10001", "styleCD", "subStyleCD", "1289630237", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10,
                    "unitOfMeasurement", 100.0, 10.0, "volumeUOM", 150.0,
                    "weightUOM", Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond(), "CREATED");
            LineItem l6 = new LineItem("P10001", "styleCD", "subStyleCD", "1289630237", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10,
                    "unitOfMeasurement", 100.0, 10.0, "volumeUOM", 150.0,
                    "weightUOM", Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond(), "CREATED");
            lineItems.add(l1);
            lineItems.add(l2);
            lineItems.add(l3);
            lineItems.add(l4);
            lineItems.add(l5);
            lineItems.add(l6);

            purchaseOrder = new PurchaseOrder();
            purchaseOrder.setId("PO11101");
            purchaseOrder.setPoDate(Instant.now().getEpochSecond());
            purchaseOrder.setPoRequistionedBy("Sample Company Private Limited");
            purchaseOrder.setPoRecieveDate(Instant.now().plus(1, ChronoUnit.DAYS).getEpochSecond());
            purchaseOrder.setUpdatedOn(Instant.now().plus(1, ChronoUnit.DAYS).getEpochSecond());
            purchaseOrder.setUpdatedBy("ABC Company Private Limited");
            purchaseOrder.setStatus("APPROVED");
            purchaseOrder.setSellerDLTId("CN=Dave, OU=JavaSoft, O=Sun Microsystems, C=US");
            purchaseOrder.setSellerNumber("023297402384");
            purchaseOrder.setSellerName("ABC Company Private Limited");
            purchaseOrder.setSellerAddress("204 Colins Street, Melbourne 3000, Australia");
            purchaseOrder.setSellerTaxId("XXX-XXX-XXXXXX");
            purchaseOrder.setBuyerDLTId("CN=Dave, OU=JavaSoft, O=Sun Microsystems, C=US");
            purchaseOrder.setBuyerName("Sample Company Private Limited");
            purchaseOrder.setBuyerTaxId("XXX-XXX-XXXXXX");
            purchaseOrder.setBillingAddress("1234 High Street, Glen Wavereley, Victoria 3456");
            purchaseOrder.setShipToAddress("1234 High Street, Glen Wavereley, Victoria 3456");
            purchaseOrder.setPoAmount(100023908);
            purchaseOrder.setPoExpDate(Instant.now().plus(20, ChronoUnit.DAYS).getEpochSecond());
            purchaseOrder.setDocumentMetadata(documentReference);
            purchaseOrder.setPoLineItems(lineItems);
            return purchaseOrder;

        }
    }

    public static com.serialization.benchmark.models.thrifmodels.PurchaseOrder createThriftTestModel() {

        if (thriftPurchaseOrder != null) {
            return thriftPurchaseOrder;
        } else {


            com.serialization.benchmark.models.thrifmodels.DocumentReference documentReference = new com.serialization.benchmark.models.thrifmodels.DocumentReference();

            documentReference.setDocId("PO1011");
            documentReference.setDocName("Purchase Order");
            documentReference.setDocType("png");
            documentReference.setVersion(1.0);
            documentReference.setDocHash("112e476505aab51b05aeb2246c02a11df03e1187e886f7c55d4e9935c290ade");

            List<com.serialization.benchmark.models.thrifmodels.LineItem> lineItems = new ArrayList<>();
            com.serialization.benchmark.models.thrifmodels.LineItem l1 =
                    new com.serialization.benchmark.models.thrifmodels.LineItem("P10001", "styleCD", "subStyleCD",
                            "1289630237", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10,
                            "unitOfMeasurement", 100.0, 10.0, "volumeUOM", 150.0,
                            "weightUOM", Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond(), "CREATED");

            com.serialization.benchmark.models.thrifmodels.LineItem l2 = new com.serialization.benchmark.models.thrifmodels.LineItem("P10001", "styleCD", "subStyleCD", "1289630237", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10,
                    "unitOfMeasurement", 100.0, 10.0, "volumeUOM", 150.0,
                    "weightUOM", Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond(), "CREATED");
            com.serialization.benchmark.models.thrifmodels.LineItem l3 = new com.serialization.benchmark.models.thrifmodels.LineItem("P10001", "styleCD", "subStyleCD", "1289630237", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10,
                    "unitOfMeasurement", 100.0, 10.0, "volumeUOM", 150.0,
                    "weightUOM", Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond(), "CREATED");
            com.serialization.benchmark.models.thrifmodels.LineItem l4 = new com.serialization.benchmark.models.thrifmodels.LineItem("P10001", "styleCD", "subStyleCD", "1289630237", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10,
                    "unitOfMeasurement", 100.0, 10.0, "volumeUOM", 150.0,
                    "weightUOM", Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond(), "CREATED");
            com.serialization.benchmark.models.thrifmodels.LineItem l5 = new com.serialization.benchmark.models.thrifmodels.LineItem("P10001", "styleCD", "subStyleCD", "1289630237", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10,
                    "unitOfMeasurement", 100.0, 10.0, "volumeUOM", 150.0,
                    "weightUOM", Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond(), "CREATED");
            com.serialization.benchmark.models.thrifmodels.LineItem l6 = new com.serialization.benchmark.models.thrifmodels.LineItem("P10001", "styleCD", "subStyleCD", "1289630237", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10,
                    "unitOfMeasurement", 100.0, 10.0, "volumeUOM", 150.0,
                    "weightUOM", Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond(), "CREATED");
            lineItems.add(l1);
            lineItems.add(l2);
            lineItems.add(l3);
            lineItems.add(l4);
            lineItems.add(l5);
            lineItems.add(l6);

            thriftPurchaseOrder = new com.serialization.benchmark.models.thrifmodels.PurchaseOrder();
            thriftPurchaseOrder.set_id("PO11101");
            thriftPurchaseOrder.setPoDate(Instant.now().getEpochSecond());
            thriftPurchaseOrder.setPoRequestionedBy("Sample Company Private Limited");
            thriftPurchaseOrder.setUpdatedOn(Instant.now().plus(1, ChronoUnit.DAYS).getEpochSecond());
            thriftPurchaseOrder.setUpdatedBy("ABC Company Private Limited");
            thriftPurchaseOrder.setStatus("APPROVED");
            thriftPurchaseOrder.setSellerDLTId("CN=Dave, OU=JavaSoft, O=Sun Microsystems, C=US");
            thriftPurchaseOrder.setSellerNumber("023297402384");
            thriftPurchaseOrder.setSellerName("ABC Company Private Limited");
            thriftPurchaseOrder.setSellerAddress("204 Colins Street, Melbourne 3000, Australia");
            thriftPurchaseOrder.setSellerTaxId("XXX-XXX-XXXXXX");
            thriftPurchaseOrder.setBuyerDLTId("CN=Dave, OU=JavaSoft, O=Sun Microsystems, C=US");
            thriftPurchaseOrder.setBuyerName("Sample Company Private Limited");
            thriftPurchaseOrder.setBuyerTaxId("XXX-XXX-XXXXXX");
            thriftPurchaseOrder.setBillingAddress("1234 High Street, Glen Wavereley, Victoria 3456");
            thriftPurchaseOrder.setShipToAddress("1234 High Street, Glen Wavereley, Victoria 3456");
            thriftPurchaseOrder.setPoAmount(100023908);
            thriftPurchaseOrder.setPoExpDate(Instant.now().plus(20, ChronoUnit.DAYS).getEpochSecond());
            thriftPurchaseOrder.setDocumentMetadata(documentReference);
            thriftPurchaseOrder.setPoLineItems(lineItems);
            return thriftPurchaseOrder;
        }

    }

    public static PurchaseOrderProtos.PurchaseOrder createProtobufTestModel() {

        if (protobufPurchaseOrder != null) {
            return protobufPurchaseOrder;
        } else {

            DocumentReferenceProtos.DocumentReference documentReference =
                    DocumentReferenceProtos.DocumentReference.newBuilder()
                            .setDocId("PO1011")
                            .setDocName("Purchase Order")
                            .setDocType("png")
                            .setVersion(1.0)
                            .setDocHash("112e476505aab51b05aeb2246c02a11df03e1187e886f7c55d4e9935c290ade")
                            .build();

            LineItemProtos.LineItem l1 =
                    LineItemProtos.LineItem.newBuilder()
                            .setPoLineSeqNBR("P10001")
                            .setStyleCD("styleCD")
                            .setSubStyleCD("subStyleCD")
                            .setSku("1289630237")
                            .setDescription("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
                            .setQuantity(10)
                            .setUnitOfMeasurement("unitOfMeasurement")
                            .setPrice(100.0)
                            .setVolume(10.0)
                            .setVolumeUOM("volumeUOM")
                            .setWeight(150.0)
                            .setWeightUOM("weightUOM")
                            .setDueDate(Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond())
                            .setStatus("CREATED").build();

            LineItemProtos.LineItem l2 =
                    LineItemProtos.LineItem.newBuilder()
                            .setPoLineSeqNBR("P10001")
                            .setStyleCD("styleCD")
                            .setSubStyleCD("subStyleCD")
                            .setSku("1289630237")
                            .setDescription("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
                            .setQuantity(10)
                            .setUnitOfMeasurement("unitOfMeasurement")
                            .setPrice(100.0)
                            .setVolume(10.0)
                            .setVolumeUOM("volumeUOM")
                            .setWeight(150.0)
                            .setWeightUOM("weightUOM")
                            .setDueDate(Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond())
                            .setStatus("CREATED").build();

            LineItemProtos.LineItem l3 =
                    LineItemProtos.LineItem.newBuilder()
                            .setPoLineSeqNBR("P10001")
                            .setStyleCD("styleCD")
                            .setSubStyleCD("subStyleCD")
                            .setSku("1289630237")
                            .setDescription("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
                            .setQuantity(10)
                            .setUnitOfMeasurement("unitOfMeasurement")
                            .setPrice(100.0)
                            .setVolume(10.0)
                            .setVolumeUOM("volumeUOM")
                            .setWeight(150.0)
                            .setWeightUOM("weightUOM")
                            .setDueDate(Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond())
                            .setStatus("CREATED").build();

            LineItemProtos.LineItem l4 =
                    LineItemProtos.LineItem.newBuilder()
                            .setPoLineSeqNBR("P10001")
                            .setStyleCD("styleCD")
                            .setSubStyleCD("subStyleCD")
                            .setSku("1289630237")
                            .setDescription("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
                            .setQuantity(10)
                            .setUnitOfMeasurement("unitOfMeasurement")
                            .setPrice(100.0)
                            .setVolume(10.0)
                            .setVolumeUOM("volumeUOM")
                            .setWeight(150.0)
                            .setWeightUOM("weightUOM")
                            .setDueDate(Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond())
                            .setStatus("CREATED").build();

            LineItemProtos.LineItem l5 =
                    LineItemProtos.LineItem.newBuilder()
                            .setPoLineSeqNBR("P10001")
                            .setStyleCD("styleCD")
                            .setSubStyleCD("subStyleCD")
                            .setSku("1289630237")
                            .setDescription("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
                            .setQuantity(10)
                            .setUnitOfMeasurement("unitOfMeasurement")
                            .setPrice(100.0)
                            .setVolume(10.0)
                            .setVolumeUOM("volumeUOM")
                            .setWeight(150.0)
                            .setWeightUOM("weightUOM")
                            .setDueDate(Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond())
                            .setStatus("CREATED").build();

            LineItemProtos.LineItem l6 =
                    LineItemProtos.LineItem.newBuilder()
                            .setPoLineSeqNBR("P10001")
                            .setStyleCD("styleCD")
                            .setSubStyleCD("subStyleCD")
                            .setSku("1289630237")
                            .setDescription("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
                            .setQuantity(10)
                            .setUnitOfMeasurement("unitOfMeasurement")
                            .setPrice(100.0)
                            .setVolume(10.0)
                            .setVolumeUOM("volumeUOM")
                            .setWeight(150.0)
                            .setWeightUOM("weightUOM")
                            .setDueDate(Instant.now().plus(10, ChronoUnit.DAYS).getEpochSecond())
                            .setStatus("CREATED").build();

            List<LineItemProtos.LineItem> lineItems = new ArrayList<>();
            lineItems.add(l1);
            lineItems.add(l2);
            lineItems.add(l3);
            lineItems.add(l4);
            lineItems.add(l5);
            lineItems.add(l6);


            PurchaseOrderProtos.PurchaseOrder purchaseOrder = PurchaseOrderProtos.PurchaseOrder.newBuilder()
                    .setId("PO11101")
                    .setPoDate(Instant.now().getEpochSecond())
                    .setPoRequestionedBy("Sample Company Private Limited")
                    .setUpdatedOn(Instant.now().plus(1, ChronoUnit.DAYS).getEpochSecond())
                    .setUpdatedBy("ABC Company Private Limited")
                    .setStatus("APPROVED")
                    .setSellerDLTId("CN=Dave, OU=JavaSoft, O=Sun Microsystems, C=US")
                    .setSellerNumber("023297402384")
                    .setSellerName("ABC Company Private Limited")
                    .setSellerAddress("204 Colins Street, Melbourne 3000, Australia")
                    .setSellerTaxId("XXX-XXX-XXXXXX")
                    .setBuyerDLTId("CN=Dave, OU=JavaSoft, O=Sun Microsystems, C=US")
                    .setBuyerName("Sample Company Private Limited")
                    .setBuyerTaxId("XXX-XXX-XXXXXX")
                    .setBillingAddress("1234 High Street, Glen Wavereley, Victoria 3456")
                    .setShipToAddress("1234 High Street, Glen Wavereley, Victoria 3456")
                    .setPoAmount(100023908)
                    .setPoExpDate(Instant.now().plus(20, ChronoUnit.DAYS).getEpochSecond())
                    .setDocumentMetadata(documentReference)
                    .addAllPoLineItems(lineItems)
                    .build();
            return purchaseOrder;

        }
    }

}
