package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.restauranttype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.RestaurantTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTypeCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public RestaurantTypeDomain to() {
        RestaurantTypeDomain restaurantTypeDomain = new RestaurantTypeDomain();
        restaurantTypeDomain.setName(name);
        restaurantTypeDomain.setDescription(description);
        return restaurantTypeDomain;
    }
}
