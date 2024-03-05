package com.restaurante.demo.dto;

import com.restaurante.demo.model.Order;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String clientDocument;
    private UUID productUuid;
    private int quantity;
    private String extraInformation;

    public static Order fromDto(OrderDto dto) {
        Order order = new Order();
        order.setClientDocument(dto.getClientDocument());
        order.setProductUuid(dto.getProductUuid());
        order.setQuantity(dto.getQuantity());
        order.setExtraInformation(dto.getExtraInformation());
        order.setCreationDateTime(LocalDate.now()); // Assuming creationDateTime should be set to current date
        // other fields like subtotal, tax, grandTotal, delivered, deliveredDate are not included as they might need more information
        return order;
    }
}
