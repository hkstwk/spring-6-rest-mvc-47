package guru.springframework.spring6restmvc.mappers;

import guru.springframework.spring6restmvc.entities.BeerOrder;
import guru.springframework.spring6restmvc.entities.BeerOrderLine;
import guru.springframework.spring6restmvc.entities.BeerOrderShipment;
import guru.springframework.spring6restmvc.model.BeerOrderDTO;
import guru.springframework.spring6restmvc.model.BeerOrderLineDTO;
import guru.springframework.spring6restmvc.model.BeerOrderShipmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BeerOrderMapper {

    @Mapping(target = "beerOrderLines", source = "beerOrderLineDTOSet")
    @Mapping(target = "beerOrderShipment", source = "beerOrderShipmentDTO")
    @Mapping(target = "customer", source = "customerDTO")
    BeerOrder beerOrderDtoToBeerOrder(BeerOrderDTO beerOrderDTO);

    @Mapping(target = "beerOrderLineDTOSet", source = "beerOrderLines")
    @Mapping(target = "beerOrderShipmentDTO", source = "beerOrderShipment")
    @Mapping(target = "customerDTO", source = "customer")
    BeerOrderDTO beerOrderToBeerOrderDto(BeerOrder beerOrder);

    @Mapping(target = "beerDTO", source = "beer")
    BeerOrderLineDTO beerOrderLineToBeerOrderLineDto(BeerOrderLine beerOrderLine);

    @Mapping(target = "beerOrder", ignore = true)
    @Mapping(target = "beer", source = "beerDTO")
    BeerOrderLine beerOrderLineDtoToBeerOrderLine(BeerOrderLineDTO beerOrderLineDTO);

    BeerOrderShipmentDTO beerOrderShipmentToBeerOrderShipmentDto(BeerOrderShipment beerOrderShipment);

    @Mapping(target = "beerOrder", ignore = true)
    BeerOrderShipment beerOrderShipMentDtoToBeerOrderShipment(BeerOrderShipmentDTO beerOrderShipmentDTO);
}
