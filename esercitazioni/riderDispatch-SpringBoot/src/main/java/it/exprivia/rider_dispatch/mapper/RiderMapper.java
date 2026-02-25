package it.exprivia.rider_dispatch.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import it.exprivia.rider_dispatch.model.dto.RiderDTO;
import it.exprivia.rider_dispatch.model.entity.Rider;

@Mapper(componentModel = "spring")
public interface RiderMapper {

    RiderDTO toDTO(Rider rider);
    Rider toEntity(RiderDTO riderDTO);

    List<RiderDTO> toDTOs(List<Rider> riders);
    List<Rider> toEntities(List<RiderDTO> riderDTOs);
}
