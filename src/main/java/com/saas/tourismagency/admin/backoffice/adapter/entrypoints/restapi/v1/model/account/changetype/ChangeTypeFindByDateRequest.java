package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeTypeFindByDateRequest {

    @JsonProperty("date")
    private LocalDate date;

}
