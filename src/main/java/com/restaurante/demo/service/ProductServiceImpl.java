package com.restaurante.demo.service;

import com.restaurante.demo.model.Product;
import com.restaurante.demo.repository.IClientRepository;
import com.restaurante.demo.repository.IProductRepository;
import com.restaurante.demo.utils.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    IProductRepository productRepository;
    @Override
    public Product createProduct(Product product) {
        Optional<Product> existProduct = productRepository.findByFantasyName(product.getFantasyName().toUpperCase());
        ProductValidation.productPresentValidation(existProduct, product.getFantasyName());
        ProductValidation.productTotalValidation(product);

        String upperCaseFantasyName = product.getFantasyName().toUpperCase();
        product.setFantasyName(upperCaseFantasyName);
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(UUID uuidProduct) {
        Optional<Product> product = productRepository.findByUuid(uuidProduct);
        ProductValidation.productEmptyValidation(product);
        ProductValidation.productFormatUuid(uuidProduct);

        return product.get();
    }

    @Override
    public Product updateProduct(UUID uuid, Product product) {
        Optional<Product> existingProduct = productRepository.findByUuid(uuid);
        ProductValidation.productEmptyValidation(existingProduct);
        ProductValidation.productEqualValidation(existingProduct, product);

        Optional<Product> otherProduct = productRepository.findByFantasyName(product.getFantasyName());
        ProductValidation.productFantasyNameValidation(otherProduct);

        otherProduct = productRepository.findByFantasyName(product.getFantasyName());
        ProductValidation.productFantasyNameUUIDValidation(otherProduct, uuid);

        ProductValidation.productTotalValidation(product);

        existingProduct.get().setFantasyName(product.getFantasyName());
        existingProduct.get().setCategory(product.getCategory());
        existingProduct.get().setAvailable(product.isAvailable());
        existingProduct.get().setDescription(product.getDescription());
        existingProduct.get().setPrice(product.getPrice());

        return productRepository.save(existingProduct.get());
    }

    @Override
    public Product deleteProduct(UUID uuidProduct) {
        Optional<Product> product = productRepository.findByUuid(uuidProduct);
        ProductValidation.productEmptyValidation(product);
        ProductValidation.productFormatUuid(uuidProduct);

        Product productDeleted = product.get();
        productRepository.delete(productDeleted);
        return productDeleted;
    }
}
