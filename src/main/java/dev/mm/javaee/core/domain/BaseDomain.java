package dev.mm.javaee.core.domain;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseDomain {

    @EqualsAndHashCode.Include
    protected String id;

    protected Boolean deleted;
    protected Boolean enabled;
    protected Boolean migrated;

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected LocalDateTime deletedAt;

    protected UserBaseDomain createdBy;
    protected UserBaseDomain updatedBy;
    protected UserBaseDomain deletedBy;

    protected String identifierName;

    protected String tenant;

    @Getter(AccessLevel.NONE)
    private String createdAtStringOnlyDate;

    public String getCreatedAtStringOnlyDate(){
        return LocalDateTimeUtil.toLocalDateTimeFormatShort(this.getCreatedAt());
    }
}
