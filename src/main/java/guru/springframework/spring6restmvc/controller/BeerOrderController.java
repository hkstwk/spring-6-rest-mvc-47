package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.entities.BeerOrder;
import guru.springframework.spring6restmvc.model.BeerOrderCreateDTO;
import guru.springframework.spring6restmvc.model.BeerOrderDTO;
import guru.springframework.spring6restmvc.model.BeerOrderUpdateDTO;
import guru.springframework.spring6restmvc.services.BeerOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
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

    @PostMapping(value = BEER_ORDER_PATH)
    public ResponseEntity<Void> createBeerOrder(@RequestBody BeerOrderCreateDTO beerOrderCreateDTO){
        BeerOrder savedBeerOrder =  beerOrderService.createBeerOrder(beerOrderCreateDTO);
        return ResponseEntity.created(URI.create(BEER_ORDER_PATH + "/" + savedBeerOrder.getId().toString())).build();
    }

    @PutMapping(value = BEER_ORDER_PATH_ID)
    public ResponseEntity<BeerOrderDTO> updateBeerOrder(@PathVariable UUID beerOrderId, @RequestBody BeerOrderUpdateDTO beerOrderUpdateDTO){
        return ResponseEntity.ok(beerOrderService.updateBeerOrder(beerOrderId, beerOrderUpdateDTO));
    }

    @DeleteMapping(value = BEER_ORDER_PATH_ID)
    public ResponseEntity deleteBeerOrder(@PathVariable UUID beerOrderId){
        if (!beerOrderService.deleteBeerOrder(beerOrderId)){
            throw new NotFoundException();
        }
        return ResponseEntity.noContent().build();
    }
}
