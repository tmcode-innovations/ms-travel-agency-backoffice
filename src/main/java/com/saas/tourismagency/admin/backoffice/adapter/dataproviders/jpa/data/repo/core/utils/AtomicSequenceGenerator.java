package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Service
public class AtomicSequenceGenerator implements SequenceGenerator {

    protected int value;

    @Override
    public int getNext() {
        return value + 1;
    }

}
