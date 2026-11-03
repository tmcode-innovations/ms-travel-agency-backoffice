package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core;

public interface PersistConsecutiveNumberCallback {

    public String generateNumber();

    public String generateNumber(String prefix);

    public String generateNumber(String prefix, Boolean withoutSpacePrefix);

    public String generateNumber(Integer year);

    public String generateNumber(Integer year, Integer month);

    public String generateNumber(Integer year, Integer month, Integer day);

    public String generateNumber(Integer year, String prefix);

    public String generateNumber(Integer year, String prefix, Boolean withoutSpacePrefix);

    public String generateNumber(Integer year, Integer month, String prefix);

    public String generateNumber(Integer year, Integer month, String prefix, Boolean withoutSpacePrefix);

    public String generateNumber(Integer year, Integer month, Integer day, String prefix);

    public String generateNumber(Integer year, Integer month, Integer day, String prefix, Boolean withoutSpacePrefix);
}
