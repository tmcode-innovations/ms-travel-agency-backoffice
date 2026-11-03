package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformacreditnote;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.PersistConsecutiveNumberEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.constants.GenerateNumberObjective;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.impl.PersistConsecutiveNumberCallbackImpl;
import org.springframework.stereotype.Service;

@Service
public class PersistProfCreditNoteNumberCallbackImpl extends PersistConsecutiveNumberCallbackImpl {
    @Override
    protected String getObjective() {
        return GenerateNumberObjective.GENERATE_NUMBER_PROFORMA_NOTE_CRED;
    }

    @Override
    protected String generateNumberEntity(PersistConsecutiveNumberEntity persistConsecutiveNumberEntity) {
        String yearStr = null;
        if (persistConsecutiveNumberEntity.getYear() != null){
            yearStr = persistConsecutiveNumberEntity.getYear().toString();
        }
        String number = persistConsecutiveNumberEntity.getNumber().toString();
        if (number.length() == 1){
            number = "00000"+number;
        }else if (number.length() == 2){
            number = "0000"+number;
        }else if (number.length() == 3){
            number = "000"+number;
        }else if (number.length() == 4){
            number = "00"+number;
        }else if (number.length() == 5){
            number = "0"+number;
        }
        return yearStr + "-" + number;
    }
}
