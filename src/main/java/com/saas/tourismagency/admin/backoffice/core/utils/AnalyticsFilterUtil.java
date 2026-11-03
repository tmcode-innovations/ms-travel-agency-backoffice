package com.saas.tourismagency.admin.backoffice.core.utils;

import com.saas.tourismagency.admin.backoffice.domain.account.enums.DocumentAccountStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ProformaStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ProformaTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ValueTypeShowSupplierEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateRegisterGroupEnum;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import java.util.function.Function;

public final class AnalyticsFilterUtil {

    private AnalyticsFilterUtil() {
    }

    public static Object refId(String value) {
        if (StringUtils.isEmpty(value)) {
            return value;
        }
        return ObjectId.isValid(value) ? new ObjectId(value) : value;
    }

    public static String stateRegisterGroup(String value) {
        return enumName(value, id -> StateRegisterGroupEnum.resolve(id).getName());
    }

    public static String documentAccountState(String value) {
        return enumName(value, id -> DocumentAccountStateEnum.resolve(id).getName());
    }

    public static String proformaState(String value) {
        return enumName(value, id -> ProformaStateEnum.resolve(id).getName());
    }

    public static String proformaType(String value) {
        return enumName(value, id -> ProformaTypeEnum.resolve(id).getName());
    }

    public static String valueTypeShowSupplier(String value) {
        return enumName(value, id -> ValueTypeShowSupplierEnum.resolve(id).getName());
    }

    private static String enumName(String value, Function<String, String> resolver) {
        if (StringUtils.isEmpty(value)) {
            return value;
        }
        try {
            return resolver.apply(value);
        } catch (Exception e) {
            return value;
        }
    }
}