package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Document(collection = "account_proformas")
@TypeAlias("ProformaInvoiceEntityData")
public class ProformaInvoiceEntity extends ProformaEntity implements Serializable {


}
