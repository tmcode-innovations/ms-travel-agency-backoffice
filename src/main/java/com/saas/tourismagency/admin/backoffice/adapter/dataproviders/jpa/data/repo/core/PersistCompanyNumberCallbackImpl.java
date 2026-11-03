package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.PersistConsecutiveNumberEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.constants.GenerateNumberObjective;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.impl.PersistConsecutiveNumberCallbackImpl;
import org.springframework.stereotype.Service;

@Service
public class PersistCompanyNumberCallbackImpl extends PersistConsecutiveNumberCallbackImpl {
    @Override
    protected String getObjective() {
        return GenerateNumberObjective.GENERATE_NUMBER_COMPANY;
    }

    @Override
    protected String generateNumberEntity(PersistConsecutiveNumberEntity persistConsecutiveNumberEntity) {
        String number = persistConsecutiveNumberEntity.getNumber().toString();
        if (number.length() == 1){
            number = "00"+number;
        }else if (number.length() == 2){
            number = "0"+number;
        }
        return number;
    }
}
