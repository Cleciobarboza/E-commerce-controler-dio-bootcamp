package br.com.dio.storefront.service;

import br.com.dio.storefront.dto.ProductInfoDTO;
import br.com.dio.storefront.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IProductService {

    ProductEntity save(final ProductEntity entity);

    void changeActivated(final UUID id, final boolean active);

    List<ProductEntity> findActive();

    ProductInfoDTO findInfo(final UUID id);

    void purchase(final UUID id);
}
