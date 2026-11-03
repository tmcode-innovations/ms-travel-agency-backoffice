package com.saas.tourismagency.admin.backoffice.utils;

import org.springframework.stereotype.Service;

@Service
public class ServiceBookingUtil {

    private Integer MAX_PASSENGER_BY_ROOM_TYPE_SWB = 1;
    private Integer MAX_PASSENGER_BY_ROOM_TYPE_TWIN = 2;
    private Integer MAX_PASSENGER_BY_ROOM_TYPE_MAT = 2;
    private Integer MAX_PASSENGER_BY_ROOM_TYPE_TRP = 3;

    public Integer getMaxPassengerByRoom(String typeRoomBooking) {
        Integer result = 0;
        switch (typeRoomBooking) {
            case "Swb", "Swb TC", "Swb TC Local" -> {
                result = MAX_PASSENGER_BY_ROOM_TYPE_SWB;
            }
            case "Twin", "Twin TC", "Twin TC Local" -> {
                result = MAX_PASSENGER_BY_ROOM_TYPE_TWIN;
            }
            case "Mat" -> {
                result = MAX_PASSENGER_BY_ROOM_TYPE_MAT;
            }
            case "Trp" -> {
                result = MAX_PASSENGER_BY_ROOM_TYPE_TRP;
            }
            default ->{
                result = 0;
            }
        }
        return result;
    }

}
