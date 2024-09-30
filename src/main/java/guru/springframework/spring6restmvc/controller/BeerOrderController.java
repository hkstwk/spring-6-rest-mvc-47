package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.BeerOrderDTO;
import guru.springframework.spring6restmvc.services.BeerOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerOrderController {
    public static final String BEER_ORDER_PATH = "/api/v1/beerorder";
    public static final String BEER_ORDER_PATH_ID = BEER_ORDER_PATH + "/{beerOrderId}";

    private final BeerOrderService beerOrderService;

    @GetMapping(value = BEER_ORDER_PATH)
    public Page<BeerOrderDTO> listBeers(@RequestParam(required = false) Integer pageNumber,
                                        @RequestParam(required = false) Integer pageSize){
        return beerOrderService.listBeerOrders(pageNumber, pageSize);
    }

    @GetMapping(value = BEER_ORDER_PATH_ID)
    public BeerOrderDTO getBeerOrderById(@PathVariable("beerOrderId") UUID beerOrderId){
        return beerOrderService.getBeerOrderById(beerOrderId).orElseThrow(NotFoundException::new);
    }
}
