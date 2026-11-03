package com.saas.tourismagency.admin.backoffice.adapter.importdata.model.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObjectSecurity implements Comparable<ObjectSecurity>{

    private Integer index;
    private String name;
    private String source;
    private String app;
    private String identify;

    @Override
    public int compareTo(ObjectSecurity other) {
        return index.compareTo(this.index);
    }
}
