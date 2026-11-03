package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.groupbookingclassification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GroupBookingClassificationDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupBookingClassificationCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("booking_classification")
    private String bookingClassification;

    public GroupBookingClassificationDomain to() {
        GroupBookingClassificationDomain groupBookingClassificationDomain = new GroupBookingClassificationDomain();
        groupBookingClassificationDomain.setName(name);
        groupBookingClassificationDomain.setDescription(description);
        groupBookingClassificationDomain.setIdentify(identify);
        groupBookingClassificationDomain.setBookingClassification(bookingClassification);
        return groupBookingClassificationDomain;
    }
}
