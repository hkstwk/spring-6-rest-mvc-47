package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.entities.BeerOrder;
import guru.springframework.spring6restmvc.model.BeerDTO;
import guru.springframework.spring6restmvc.model.BeerOrderDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface BeerOrderService {
    Page<BeerOrderDTO> listBeerOrders(Integer pageNumber, Integer pageSize);
    Optional<BeerOrder> getBeerOrderById(UUID id);
}
