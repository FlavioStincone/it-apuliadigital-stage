package it.exprivia.rider_dispatch.service;

import it.exprivia.rider_dispatch.model.dto.RiderDTO;
import java.util.List;

public interface IRiderService {
    
    RiderDTO updateRider(RiderDTO riderDTO);

    List<RiderDTO> getAllRiders();

    RiderDTO getRiderByFiscalCode(String fiscalCode);

    Boolean removeRider(String fiscalCode);
}