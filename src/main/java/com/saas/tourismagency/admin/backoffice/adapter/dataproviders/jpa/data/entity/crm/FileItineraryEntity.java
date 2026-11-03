package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "crm_file_itineraries")
public class FileItineraryEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String fileName;
    private String extension;
    private String path;

    private @DocumentReference(lazy = true)
    QuotationEntity quotation;
}
