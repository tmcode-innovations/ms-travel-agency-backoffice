package dev.mm.javaee.core.domain;

import java.util.Map;

public interface ISearchFilterReportDomain {
    public Map<String, String> getFilter();
    public Map<String, String> getFilterReport();
    public Map<String, String> getSort();
}
