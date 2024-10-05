package guru.springframework.spring6restmvc.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class BeerOrderUpdateDTO {
    @NotNull
    private UUID customerId;
    private String customerRef;

    private Set<BeerOrderLineUpdateDTO> beerOrderLines;

    private BeerOrderShipmentDTO beerOrderShipment;
}
