package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgingfeeding;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapFeedingItem {

    @JsonProperty("localDateTime")
    LocalDateTime localDateTime;

    @JsonProperty("cantBreakfast")
    Integer cantBreakfast;

    @JsonProperty("cantLunch")
    Integer cantLunch;

    @JsonProperty("cantDinner")
    Integer cantDinner;

    @JsonProperty("cantBoxLunch")
    Integer cantBoxLunch;

}
