package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.PersistConsecutiveNumberEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.constants.GenerateNumberObjective;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.impl.PersistConsecutiveNumberCallbackImpl;
import org.springframework.stereotype.Service;

@Service
public class PersistRegisterGroupNumberCallbackImpl extends PersistConsecutiveNumberCallbackImpl {
    @Override
    protected String getObjective() {
        return GenerateNumberObjective.GENERATE_NUMBER_REGISTER_GROUP;
    }

    @Override
    protected String generateNumberEntity(PersistConsecutiveNumberEntity persistConsecutiveNumberEntity) {
        String monthStr = null;
        if (persistConsecutiveNumberEntity.getMonth().toString().length() == 1){
            monthStr = "0" + persistConsecutiveNumberEntity.getMonth().toString();
        }else if (persistConsecutiveNumberEntity.getMonth().toString().length() == 2){
            monthStr = persistConsecutiveNumberEntity.getMonth().toString();
        }
        String number = persistConsecutiveNumberEntity.getNumber().toString();
        if (number.length() == 1){
            number = "000"+number;
        }else if (number.length() == 2){
            number = "00"+number;
        }else if (number.length() == 3){
            number = "0"+number;
        }
        return persistConsecutiveNumberEntity.getYear().toString().substring(2) + monthStr + number;
    }
}
