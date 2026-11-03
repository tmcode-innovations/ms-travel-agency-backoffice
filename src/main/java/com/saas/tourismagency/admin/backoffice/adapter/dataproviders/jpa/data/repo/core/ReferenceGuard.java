package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReferenceGuard {

    private final MongoTemplate mongoTemplate;

    public ReferenceGuard(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private record Ref(String collection, String field) {
    }

    private static final Map<String, List<Ref>> REFS = buildRefs();

    public boolean isReferenced(String type, String id) {
        return countReferences(type, id) > 0;
    }

    public long countReferences(String type, String id) {
        if (id == null || !ObjectId.isValid(id)) {
            return 0L;
        }
        List<Ref> refs = REFS.get(type);
        if (refs == null) {
            return 0L;
        }
        ObjectId oid = new ObjectId(id);
        long total = 0L;
        for (Ref ref : refs) {
            Query query = new Query();
            query.addCriteria(Criteria.where("deleted").is(false));
            query.addCriteria(Criteria.where(ref.field()).is(oid));
            total += mongoTemplate.count(query, ref.collection());
        }
        return total;
    }

    private static Map<String, List<Ref>> buildRefs() {
        Map<String, List<Ref>> map = new java.util.HashMap<>();

        map.put("Customer", list(
                ref("booking_register_groups", "customer"),
                ref("booking_register_booking_bases", "customer"),
                ref("crm_quotations", "customer"),
                ref("account_cost_sheet_two", "customer"),
                ref("booking_itinerary_items", "customer"),
                ref("account_voucher_ingress_details", "customer"),
                ref("account_document_finances", "customer"),
                ref("account_proformas", "customer"),
                ref("maintenance_contacts", "customer")));

        map.put("Bank", list(
                ref("account_account_payments", "bank"),
                ref("account_voucher_egress", "bank")));

        map.put("Currency", list(
                ref("account_cost_sheet_two", "currency"),
                ref("account_cost_sheet_two_details", "currency"),
                ref("account_document_finances", "currency"),
                ref("account_expense_guides", "currency"),
                ref("account_proformas", "currency"),
                ref("account_voucher_egress", "currency"),
                ref("account_voucher_egress_detail_items", "currency"),
                ref("account_voucher_egress_details", "currency"),
                ref("account_voucher_ingress", "currency"),
                ref("crm_quotations", "currency")));

        map.put("Detraction", list(
                ref("account_voucher_egress", "detraction"),
                ref("account_voucher_egress_detail_items", "detraction"),
                ref("account_voucher_egress_details", "detraction")));

        map.put("Entry", list(
                ref("account_document_finance_details", "entry")));

        map.put("DocumentAccountType", list(
                ref("account_cost_sheet_two_details", "documentAccountType"),
                ref("account_service_package_tourists", "documentAccountType"),
                ref("account_voucher_egress_details", "documentAccountType")));

        map.put("DocumentType", list(
                ref("base_entities", "documentType"),
                ref("booking_passenger_groups", "documentType"),
                ref("crm_passengers", "documentType")));

        map.put("Igv", list(
                ref("account_cost_sheet_two_details", "igv"),
                ref("account_document_finances", "igv"),
                ref("account_voucher_egress_detail_items", "igv")));

        map.put("LodgingCategory", list(
                ref("base_entities", "lodgingCategory"),
                ref("booking_itinerary_items", "lodgeCategory"),
                ref("booking_itinerary_items", "lodgingCategory"),
                ref("booking_register_booking_item_glampings", "lodgingCategory"),
                ref("booking_register_booking_item_lodger_lodgings", "lodgingCategory"),
                ref("booking_register_booking_item_lodgings", "lodgingCategory"),
                ref("crm_itinerary_items", "lodgeCategory"),
                ref("crm_itinerary_items", "lodgingCategory")));

        map.put("PassengerType", list(
                ref("booking_cost_tour_items", "passengerType"),
                ref("booking_passenger_groups", "passengerType"),
                ref("crm_cost_tour_items", "passengerType"),
                ref("crm_passengers", "passengerType")));

        map.put("ServiceCategory", list(
                ref("maintenance_service_categories", "parent"),
                ref("maintenance_services", "serviceCategory")));

        map.put("Service", list(
                ref("account_expense_guide_details", "service"),
                ref("booking_cost_tour_service_items", "service"),
                ref("booking_itinerary_items", "service"),
                ref("booking_register_booking_item_institutions", "service"),
                ref("booking_register_booking_item_lodger", "service"),
                ref("booking_register_booking_item_operators", "service"),
                ref("booking_register_booking_item_restaurants", "service"),
                ref("crm_cost_tour_service_items", "service"),
                ref("crm_itinerary_items", "service"),
                ref("crm_program_items", "service"),
                ref("crm_tour_items", "service"),
                ref("operation_order_service_items", "service")));

        map.put("SupplierType", list(
                ref("account_cost_sheet_two_details", "supplierType"),
                ref("account_voucher_egress", "supplierType"),
                ref("account_voucher_egress_details", "supplierType"),
                ref("base_entities", "supplierType"),
                ref("booking_cost_tour_service_items", "supplierType"),
                ref("booking_itinerary_items", "supplierType"),
                ref("booking_register_booking_bases", "supplierType"),
                ref("crm_cost_tour_service_items", "supplierType"),
                ref("crm_itinerary_items", "supplierType"),
                ref("crm_program_items", "supplierType"),
                ref("crm_tour_items", "supplierType"),
                ref("maintenance_contacts", "supplierType"),
                ref("operation_order_service_items", "supplierType"),
                ref("operation_order_services", "supplierType")));

        map.put("TouristDestine", list(
                ref("account_cost_sheet_two_details", "touristDestine"),
                ref("account_voucher_egress_details", "touristDestine"),
                ref("base_entities", "touristDestine"),
                ref("maintenance_services", "touristDestine")));

        map.put("Guide", list(
                ref("account_cost_sheet_two_details", "guideDrive"),
                ref("account_cost_sheet_two_details", "guideTransfer"),
                ref("account_expense_guide_details", "guideDrive"),
                ref("account_expense_guides", "guideTransfer"),
                ref("account_voucher_egress_details", "guideDrive"),
                ref("account_voucher_egress_details", "guideTransfer"),
                ref("booking_itinerary_items", "guideTransferDrive"),
                ref("booking_itinerary_items", "guideTransport"),
                ref("operation_order_service_items", "drive"),
                ref("operation_order_service_items", "guideTransfer"),
                ref("operation_order_services", "drive"),
                ref("operation_order_services", "guideTransfer")));

        map.put("Supplier", list(
                ref("account_cost_sheet_two_details", "supplier"),
                ref("account_document_finances", "supplier"),
                ref("account_voucher_egress", "supplier"),
                ref("account_voucher_egress_details", "supplier"),
                ref("account_voucher_ingress_details", "supplier"),
                ref("booking_cost_tour_service_items", "supplier"),
                ref("booking_itinerary_items", "supplier"),
                ref("booking_register_booking_bases", "supplier"),
                ref("booking_register_booking_bases", "supplierLodging"),
                ref("crm_cost_tour_service_items", "supplier"),
                ref("crm_itinerary_items", "supplier"),
                ref("crm_program_items", "supplier"),
                ref("crm_tour_items", "supplier"),
                ref("maintenance_contacts", "supplier"),
                ref("maintenance_guides", "supplier"),
                ref("operation_order_service_items", "supplier"),
                ref("operation_order_service_items", "supplierGlamping"),
                ref("operation_order_service_items", "supplierGuide"),
                ref("operation_order_service_items", "supplierLodger"),
                ref("operation_order_service_items", "supplierLodging"),
                ref("operation_order_service_items", "supplierOperator"),
                ref("operation_order_service_items", "supplierTransport"),
                ref("operation_order_services", "supplier"),
                ref("operation_order_services", "supplierGlamping"),
                ref("operation_order_services", "supplierGuide"),
                ref("operation_order_services", "supplierLodger"),
                ref("operation_order_services", "supplierLodging"),
                ref("operation_order_services", "supplierOperator"),
                ref("operation_order_services", "supplierTransport")));

        return map;
    }

    private static Ref ref(String collection, String field) {
        return new Ref(collection, field);
    }

    private static List<Ref> list(Ref... refs) {
        return new ArrayList<>(java.util.Arrays.asList(refs));
    }
}
