package dev.mm.javaee.core.domain;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SortRequest;

import java.util.Map;

public interface ISearchSortDomain {
    public Map<String, String> getSort();
    public SortRequest[] getSortOtherFormat();
}
