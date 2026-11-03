package dev.mm.javaee.core.domain;

import java.util.Map;

public interface ISearchPageDomain {
    public int getPage();
    public int getSize();
    public Map<String, String> getSort();
}
