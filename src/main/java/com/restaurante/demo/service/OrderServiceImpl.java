package com.restaurante.demo.service;

import com.restaurante.demo.model.Client;
import com.restaurante.demo.model.Order;
import com.restaurante.demo.model.Product;
import com.restaurante.demo.repository.IClientRepository;
import com.restaurante.demo.repository.IOrderRepository;
import com.restaurante.demo.repository.IProductRepository;
import com.restaurante.demo.utils.ClientValidation;
import com.restaurante.demo.utils.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements IOrderService{

    @Autowired
    IOrderRepository orderRepository;
    @Autowired
    IClientRepository clientRepository;
    @Autowired
    IProductRepository productRepository;
    @Override
    public Order createOrder(Order order) {
        Optional<Client> client = clientRepository.findByDocument(order.getClientDocument());
        ClientValidation.clientEmptyValidation(client, order.getClientDocument());

        Optional<Product> product = productRepository.findByUuid(order.getProductUuid());
        ProductValidation.productEmptyValidation(product);

        order.setSubtotal(Double.parseDouble(product.get().getPrice()) * order.getQuantity());
        order.setTax(order.getSubtotal() * 0.19);
        order.setGrandTotal(order.getTax() + order.getSubtotal());
        order.setDelivered(false);
        order.setCreationDateTime(LocalDate.now());
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(UUID uuid, LocalDate localDate) {

        ProductValidation.productFormatUuid(uuid);
        Optional<Order> order = orderRepository.findByUuid(uuid);
        if(order.isPresent()){
            order.get().setDelivered(true);
            order.get().setDeliveredDate(localDate);
            return this.orderRepository.save(order.get());
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Order with uuid %d does not exist", uuid));
        }
    }
}
