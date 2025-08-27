package br.com.dio.storefront.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class WarehouseClientConfig {

    RestClient storefrontClient(@Value("${warehouse.base-path}") final  String basePath){
        return RestClient.create(basePath);
    }
}
