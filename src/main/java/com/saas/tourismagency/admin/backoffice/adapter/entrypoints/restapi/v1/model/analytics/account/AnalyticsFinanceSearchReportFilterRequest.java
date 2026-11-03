package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.domain.ISearchFilterReportDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticsFinanceSearchReportFilterRequest implements ISearchFilterReportDomain {

    @JsonProperty("page")
    private int page;

    @JsonProperty("size")
    private int size;

    @JsonProperty("sort")
    Map<String, String> sort;

    @JsonProperty("filter")
    Map<String, String> filter;

    @JsonProperty("filterReport")
    Map<String, String> filterReport;

}
