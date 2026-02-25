package it.exprivia.rider_dispatch.service;

import it.exprivia.rider_dispatch.model.dto.RiderDTO;

public interface IDeliveryAssignmentService {

    void processRiderAvailability(RiderDTO rider);
    
}
