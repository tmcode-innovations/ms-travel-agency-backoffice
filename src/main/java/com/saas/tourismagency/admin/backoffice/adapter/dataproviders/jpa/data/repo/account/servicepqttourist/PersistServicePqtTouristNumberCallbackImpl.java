package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.servicepqttourist;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.PersistConsecutiveNumberEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.constants.GenerateNumberObjective;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.impl.PersistConsecutiveNumberCallbackImpl;
import org.springframework.stereotype.Service;

@Service
public class PersistServicePqtTouristNumberCallbackImpl extends PersistConsecutiveNumberCallbackImpl {
    @Override
    protected String getObjective() {
        return GenerateNumberObjective.GENERATE_NUMBER_SERVICE_PQT_TOURIST;
    }

    @Override
    protected String generateNumberEntity(PersistConsecutiveNumberEntity persistConsecutiveNumberEntity) {
        String number = persistConsecutiveNumberEntity.getNumber().toString();
        if (number.length() == 1){
            number = "0000"+number;
        }else if (number.length() == 2){
            number = "000"+number;
        }else if (number.length() == 3){
            number = "00"+number;
        }else if (number.length() == 4){
            number = "0"+number;
        }
        return number;
    }
}
