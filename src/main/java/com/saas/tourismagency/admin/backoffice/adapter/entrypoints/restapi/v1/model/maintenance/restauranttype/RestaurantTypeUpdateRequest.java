package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.restauranttype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.RestaurantTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTypeUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public RestaurantTypeDomain to() {
        RestaurantTypeDomain restaurantTypeDomain = new RestaurantTypeDomain();
        restaurantTypeDomain.setId(id);
        restaurantTypeDomain.setName(name);
        restaurantTypeDomain.setDescription(description);
        return restaurantTypeDomain;
    }

}
