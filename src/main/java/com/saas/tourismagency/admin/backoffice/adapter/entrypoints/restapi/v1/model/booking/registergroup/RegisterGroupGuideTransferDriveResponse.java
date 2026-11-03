package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterGroupGuideTransferDriveResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("registerGroupIds")
    private List<String> registerGroupIds;

    public static RegisterGroupGuideTransferDriveResponse from(String id, List<String> registerGroupIds) {
        return new RegisterGroupGuideTransferDriveResponse(id, registerGroupIds);
    }
}
