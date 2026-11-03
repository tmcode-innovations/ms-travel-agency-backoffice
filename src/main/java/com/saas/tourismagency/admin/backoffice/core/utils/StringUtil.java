package com.saas.tourismagency.admin.backoffice.core.utils;

import org.springframework.stereotype.Service;

@Service
public class StringUtil {
    public boolean isEmpty(String text){
        return text == null || text.trim().isEmpty() || text.trim().isBlank();
    }
}
