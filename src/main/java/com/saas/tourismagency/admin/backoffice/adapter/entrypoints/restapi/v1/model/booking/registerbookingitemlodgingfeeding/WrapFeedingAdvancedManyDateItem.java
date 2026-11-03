package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgingfeeding;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapFeedingAdvancedManyDateItem {

    @JsonProperty("typePassenger")
    String typePassenger;

    @JsonProperty("localDateTimeBreakfast")
    List<String> localDateTimeBreakfast;

    @JsonProperty("cantBreakfast")
    Integer cantBreakfast;

    @JsonProperty("localDateTimeLunch")
    List<String> localDateTimeLunch;

    @JsonProperty("cantLunch")
    Integer cantLunch;

    @JsonProperty("localDateTimeDinner")
    List<String> localDateTimeDinner;

    @JsonProperty("cantDinner")
    Integer cantDinner;

    @JsonProperty("localDateTimeBoxLunch")
    List<String> localDateTimeBoxLunch;

    @JsonProperty("cantBoxLunch")
    Integer cantBoxLunch;

}
