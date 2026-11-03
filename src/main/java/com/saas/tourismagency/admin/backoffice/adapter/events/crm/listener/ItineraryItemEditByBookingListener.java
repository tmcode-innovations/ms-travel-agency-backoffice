package com.saas.tourismagency.admin.backoffice.adapter.events.crm.listener;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itinerarydate.ItineraryDateCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itineraryitem.ItineraryItemCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.events.booking.event.ItineraryItemEditEvent;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ItineraryItemEditByBookingListener implements ApplicationListener<ItineraryItemEditEvent> {

    @Autowired
    private ItineraryDateCrmRepo itineraryDateCrmRepo;

    @Autowired
    private ItineraryItemCrmRepo itineraryItemCrmRepo;

    @Async("threadPoolTaskExecutor")
    @Override
    public void onApplicationEvent(ItineraryItemEditEvent event) {

        ItineraryItemDomain itineraryItemDomainDestination = itineraryItemCrmRepo.findById(event.getItinerary().getIdItineraryItemQuotation());
        if (itineraryItemDomainDestination != null){
            itineraryItemDomainDestination.setName(event.getItinerary().getName());
            itineraryItemDomainDestination.setDescription(event.getItinerary().getDescription());
            itineraryItemDomainDestination.setIdentify(event.getItinerary().getIdentify());
            itineraryItemDomainDestination.setServiceGroup(event.getItinerary().getServiceGroup());
            itineraryItemDomainDestination.setObservation(event.getItinerary().getObservation());
            itineraryItemDomainDestination.setItineraryDateTime(event.getItinerary().getItineraryDateTime());
            itineraryItemDomainDestination.setPaxTotal(event.getItinerary().getPaxTotal());
            itineraryItemDomainDestination.setPrice(event.getItinerary().getPrice());
            itineraryItemDomainDestination.setPaxAdultTotal(event.getItinerary().getPaxAdultTotal());
            itineraryItemDomainDestination.setPriceAdult(event.getItinerary().getPriceAdult());
            itineraryItemDomainDestination.setPaxChildrenTotal(event.getItinerary().getPaxChildrenTotal());
            itineraryItemDomainDestination.setPriceChildren(event.getItinerary().getPriceChildren());
            itineraryItemDomainDestination.setPaxTcTotal(event.getItinerary().getPaxTcTotal());
            itineraryItemDomainDestination.setPriceTc(event.getItinerary().getPriceTc());
            itineraryItemDomainDestination.setPaxTcLocalTotal(event.getItinerary().getPaxTcLocalTotal());
            itineraryItemDomainDestination.setPriceTcLocal(event.getItinerary().getPriceTcLocal());
            itineraryItemDomainDestination.setPaxGuideTotal(event.getItinerary().getPaxGuideTotal());
            itineraryItemDomainDestination.setPriceGuide(event.getItinerary().getPriceGuide());

            ItineraryDateDomain itineraryDateDomain = itineraryDateCrmRepo.getByQuotationAndItemItinerary(event.getItinerary().getRegisterGroup().getQuotation().getId(), event.getItinerary().getItineraryDate().getItemItinerary());
            if (itineraryDateDomain != null){
                itineraryItemDomainDestination.setItineraryDate(itineraryDateDomain);
            }

            itineraryItemDomainDestination.setIndex(event.getItinerary().getIndex());
            itineraryItemDomainDestination.setQuotation(event.getItinerary().getRegisterGroup().getQuotation());
            itineraryItemDomainDestination.setSupplierType(event.getItinerary().getSupplierType());
            itineraryItemDomainDestination.setSupplier(event.getItinerary().getSupplier());
            itineraryItemDomainDestination.setService(event.getItinerary().getService());
            itineraryItemDomainDestination.setDepartmentRegion(event.getItinerary().getDepartmentRegion());
            itineraryItemDomainDestination.setTour(event.getItinerary().getTour());

            // Supplier Type Lodging

            itineraryItemDomainDestination.setLodgingCantSwb(event.getItinerary().getLodgingCantSwb());
            itineraryItemDomainDestination.setLodgingCantMat(event.getItinerary().getLodgingCantMat());
            itineraryItemDomainDestination.setLodgingCantTwin(event.getItinerary().getLodgingCantTwin());
            itineraryItemDomainDestination.setLodgingCantTrp(event.getItinerary().getLodgingCantTrp());

            itineraryItemDomainDestination.setLodgingCantBreakfast(event.getItinerary().getLodgingCantBreakfast());
            itineraryItemDomainDestination.setLodgingCantLunch(event.getItinerary().getLodgingCantLunch());
            itineraryItemDomainDestination.setLodgingCantDinner(event.getItinerary().getLodgingCantDinner());

            itineraryItemDomainDestination.setLodgingCantNight(event.getItinerary().getLodgingCantNight());

            itineraryItemDomainDestination.setLodgingCantBreakfastTcGuide(event.getItinerary().getLodgingCantBreakfastTcGuide());
            itineraryItemDomainDestination.setLodgingCantLunchTcGuide(event.getItinerary().getLodgingCantLunchTcGuide());
            itineraryItemDomainDestination.setLodgingCantDinnerTcGuide(event.getItinerary().getLodgingCantDinnerTcGuide());

            itineraryItemDomainDestination.setLodgingCantSwbTcGuide(event.getItinerary().getLodgingCantSwbTcGuide());
            itineraryItemDomainDestination.setLodgingCantTwinTcGuide(event.getItinerary().getLodgingCantTwinTcGuide());

            itineraryItemDomainDestination.setLodgingTcCantNight(event.getItinerary().getLodgingTcCantNight());

            itineraryItemDomainDestination.setLodgingCantBreakfastTcLocalGuide(event.getItinerary().getLodgingCantBreakfastTcLocalGuide());
            itineraryItemDomainDestination.setLodgingCantLunchTcLocalGuide(event.getItinerary().getLodgingCantLunchTcLocalGuide());
            itineraryItemDomainDestination.setLodgingCantDinnerTcLocalGuide(event.getItinerary().getLodgingCantDinnerTcLocalGuide());

            itineraryItemDomainDestination.setLodgingCantSwbTcLocalGuide(event.getItinerary().getLodgingCantSwbTcLocalGuide());
            itineraryItemDomainDestination.setLodgingCantTwinTcLocalGuide(event.getItinerary().getLodgingCantTwinTcLocalGuide());

            itineraryItemDomainDestination.setLodgingTcLocalCantNight(event.getItinerary().getLodgingTcLocalCantNight());

            itineraryItemDomainDestination.setLodgingForOperator(event.getItinerary().getLodgingForOperator());

            // Guide
            // itineraryItemDomainDestination.setLodgingCantBreakfastGuide(0);
            // itineraryItemDomainDestination.setLodgingCantLunchGuide(0);
            // itineraryItemDomainDestination.setLodgingCantDinnerGuide(0);

            // itineraryItemDomainDestination.setLodgingCantSwbGuide(0);
            // itineraryItemDomainDestination.setLodgingCantTwinGuide(0);

            // itineraryItemDomainDestination.setLodgingGuideCantNight(0);

            // Supplier Type Lodge

            itineraryItemDomainDestination.setLodgeCantSwb(event.getItinerary().getLodgeCantSwb());
            itineraryItemDomainDestination.setLodgeCantMat(event.getItinerary().getLodgeCantMat());
            itineraryItemDomainDestination.setLodgeCantTwin(event.getItinerary().getLodgeCantTwin());
            itineraryItemDomainDestination.setLodgeCantTrp(event.getItinerary().getLodgeCantTrp());

            itineraryItemDomainDestination.setLodgeCantBreakfast(event.getItinerary().getLodgeCantBreakfast());
            itineraryItemDomainDestination.setLodgeCantLunch(event.getItinerary().getLodgeCantLunch());
            itineraryItemDomainDestination.setLodgeCantDinner(event.getItinerary().getLodgeCantDinner());

            itineraryItemDomainDestination.setLodgeCantNight(event.getItinerary().getLodgeCantNight());

            itineraryItemDomainDestination.setLodgeCantBreakfastTcGuide(event.getItinerary().getLodgeCantBreakfastTcGuide());
            itineraryItemDomainDestination.setLodgeCantLunchTcGuide(event.getItinerary().getLodgeCantLunchTcGuide());
            itineraryItemDomainDestination.setLodgeCantDinnerTcGuide(event.getItinerary().getLodgeCantDinnerTcGuide());

            itineraryItemDomainDestination.setLodgeCantSwbTcGuide(event.getItinerary().getLodgeCantSwbTcGuide());
            itineraryItemDomainDestination.setLodgeCantTwinTcGuide(event.getItinerary().getLodgeCantTwinTcGuide());

            itineraryItemDomainDestination.setLodgeTcCantNight(event.getItinerary().getLodgeTcCantNight());

            itineraryItemDomainDestination.setLodgeCantBreakfastTcLocalGuide(event.getItinerary().getLodgeCantBreakfastTcLocalGuide());
            itineraryItemDomainDestination.setLodgeCantLunchTcLocalGuide(event.getItinerary().getLodgeCantLunchTcLocalGuide());
            itineraryItemDomainDestination.setLodgeCantDinnerTcLocalGuide(event.getItinerary().getLodgeCantDinnerTcLocalGuide());

            itineraryItemDomainDestination.setLodgeCantSwbTcLocalGuide(event.getItinerary().getLodgeCantSwbTcLocalGuide());
            itineraryItemDomainDestination.setLodgeCantTwinTcLocalGuide(event.getItinerary().getLodgeCantTwinTcLocalGuide());

            itineraryItemDomainDestination.setLodgeTcLocalCantNight(event.getItinerary().getLodgeTcLocalCantNight());

            // Guide
            // itineraryItemDomainDestination.setLodgeCantBreakfastGuide(0);
            // itineraryItemDomainDestination.setLodgeCantLunchGuide(0);
            // itineraryItemDomainDestination.setLodgeCantDinnerGuide(0);

            // itineraryItemDomainDestination.setLodgeCantSwbGuide(0);
            // itineraryItemDomainDestination.setLodgeCantTwinGuide(0);

            // itineraryItemDomainDestination.setLodgeGuideCantNight(0);

            // Supplier Type Restaurant

            itineraryItemDomainDestination.setRestaurantCantBreakfast(event.getItinerary().getRestaurantCantBreakfast());
            itineraryItemDomainDestination.setRestaurantCantLunch(event.getItinerary().getRestaurantCantLunch());
            itineraryItemDomainDestination.setRestaurantCantDinner(event.getItinerary().getRestaurantCantDinner());
            itineraryItemDomainDestination.setRestaurantCantBoxLunch(event.getItinerary().getRestaurantCantBoxLunch());

            itineraryItemDomainDestination.setRestaurantCantBreakfastTcGuide(event.getItinerary().getRestaurantCantBreakfastTcGuide());
            itineraryItemDomainDestination.setRestaurantCantLunchTcGuide(event.getItinerary().getRestaurantCantLunchTcGuide());
            itineraryItemDomainDestination.setRestaurantCantDinnerTcGuide(event.getItinerary().getRestaurantCantDinnerTcGuide());
            itineraryItemDomainDestination.setRestaurantCantBoxLunchTcGuide(event.getItinerary().getRestaurantCantBoxLunchTcGuide());

            itineraryItemDomainDestination.setRestaurantCantBreakfastTcLocalGuide(event.getItinerary().getRestaurantCantBreakfastTcLocalGuide());
            itineraryItemDomainDestination.setRestaurantCantLunchTcLocalGuide(event.getItinerary().getRestaurantCantLunchTcLocalGuide());
            itineraryItemDomainDestination.setRestaurantCantDinnerTcLocalGuide(event.getItinerary().getRestaurantCantDinnerTcLocalGuide());
            itineraryItemDomainDestination.setRestaurantCantBoxLunchTcLocalGuide(event.getItinerary().getRestaurantCantBoxLunchTcLocalGuide());

            itineraryItemDomainDestination.setRestaurantCantBreakfastGuide(event.getItinerary().getRestaurantCantBreakfastGuide());
            itineraryItemDomainDestination.setRestaurantCantLunchGuide(event.getItinerary().getRestaurantCantLunchGuide());
            itineraryItemDomainDestination.setRestaurantCantDinnerGuide(event.getItinerary().getRestaurantCantDinnerGuide());
            itineraryItemDomainDestination.setRestaurantCantBoxLunchGuide(event.getItinerary().getRestaurantCantBoxLunchGuide());

            itineraryItemDomainDestination.setRestaurantCantBreakfastDrive(event.getItinerary().getRestaurantCantBreakfastDrive());
            itineraryItemDomainDestination.setRestaurantCantLunchDrive(event.getItinerary().getRestaurantCantLunchDrive());
            itineraryItemDomainDestination.setRestaurantCantDinnerDrive(event.getItinerary().getRestaurantCantDinnerDrive());
            itineraryItemDomainDestination.setRestaurantCantBoxLunchDrive(event.getItinerary().getRestaurantCantBoxLunchDrive());


            // Supplier Type Restaurant

            itineraryItemDomainDestination.setLodgingForOperator(event.getItinerary().getLodgingForOperator());
            itineraryItemDomainDestination.setLodgingForOperatorStr(event.getItinerary().getLodgingForOperatorStr());

            itineraryItemDomainDestination.setOperatorPvt(event.getItinerary().getOperatorPvt());
            itineraryItemDomainDestination.setOperatorSic(event.getItinerary().getOperatorSic());

            itineraryItemDomainDestination.setTenant(UserResolver.resolveUser().getTenant());
            itineraryItemCrmRepo.edit(itineraryItemDomainDestination);
        }
    }

}
