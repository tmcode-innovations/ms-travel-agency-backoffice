package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.LodgingCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "booking_register_booking_item_lodgings")
public class RegisterBookingItemLodgingEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String noCupon;
    private String noCuponPreImpress;

    private LocalDate dateFrom;
    private LocalDate dateTo;

    private LocalDateTime dateTimeInitService;

    private Integer noNight;
    private Integer noNightTcGuide;
    private Integer noNightTcLocalGuide;
    private Integer noNightGuide;
    private Integer cantCb;
    private Integer cantAb;
    private Integer cantBreakfastBuffet;
    private Integer cantDinner;
    private Integer cantLunch;
    private Integer cantBoxLunch;
    private Integer cantTcGuideBreakfastBuffet;
    private Integer cantTcGuideDinner;
    private Integer cantTcGuideLunch;
    private Integer cantTcGuideBoxLunch;
    private Integer cantTcLocalGuideBreakfastBuffet;
    private Integer cantTcLocalGuideDinner;
    private Integer cantTcLocalGuideLunch;
    private Integer cantTcLocalGuideBoxLunch;
    private Integer cantGuideBreakfastBuffet;
    private Integer cantGuideDinner;
    private Integer cantGuideLunch;
    private Integer cantGuideBoxLunch;

    private String observation;
    private String category;

    private @DocumentReference(lazy = true)
    LodgingCategoryEntity lodgingCategory;

    private Integer tcLocal;
    private Integer tc;
    private Integer guide;
    private Integer cantPaxChildren;
    private Integer cantPaxAdult;

    private @DocumentReference(lazy = true)
    RegisterBookingLodgingEntity registerBookingLodging;

    private @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;
}
