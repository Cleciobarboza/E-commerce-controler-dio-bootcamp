package br.com.dio.warehouse.mapper;

import br.com.dio.warehouse.controller.request.ProductSaveRequest;
import br.com.dio.warehouse.controller.response.ProductDetailResponse;
import br.com.dio.warehouse.controller.response.ProductSavedResponse;

import br.com.dio.warehouse.dto.ProductStorefrontSavedDTO;
import br.com.dio.warehouse.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IProductMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "stocks",ignore = true)
    ProductEntity toEntity(final ProductSaveRequest request);

    ProductSavedResponse toSalvedResponse(final ProductEntity entity);

    @Mapping(target = "active", ignore = true)
    ProductStorefrontSavedDTO toDTO(final ProductEntity entity);

    ProductDetailResponse toDetailResponse (final ProductEntity entity);

}
