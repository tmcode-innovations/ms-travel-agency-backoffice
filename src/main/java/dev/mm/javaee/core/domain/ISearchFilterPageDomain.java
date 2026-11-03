package dev.mm.javaee.core.domain;

import java.util.Map;

public interface ISearchFilterPageDomain {
    public int getPage();
    public int getSize();
    public Map<String, String> getFilter();
    public Map<String, String> getSort();
}
