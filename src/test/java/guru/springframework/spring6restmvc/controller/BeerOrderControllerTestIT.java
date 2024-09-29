package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.entities.BeerOrder;
import guru.springframework.spring6restmvc.repositories.BeerOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static guru.springframework.spring6restmvc.controller.BeerControllerTest.jwtRequestPostProcessor;
import static org.hamcrest.core.Is.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BeerOrderControllerTestIT {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Autowired
    BeerOrderRepository beerOrderRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    void listBeerOrders() throws Exception {

        mockMvc.perform(get(BeerOrderController.BEER_ORDER_PATH)
                        .with(jwtRequestPostProcessor)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.length()", is(6)));
    }

    @Test
    void getBeerOrderById() throws Exception {
        BeerOrder testBeerOrder = beerOrderRepository.findAll().getFirst();

        mockMvc.perform(get(BeerOrderController.BEER_ORDER_PATH_ID, testBeerOrder.getId())
                        .with(jwtRequestPostProcessor))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testBeerOrder.getId().toString())))
                .andExpect(jsonPath("$.customerRef", is(testBeerOrder.getCustomerRef())));
    }

    @Test
    void getBeerOrderByIdNotFound() throws Exception {
        mockMvc.perform(get(BeerOrderController.BEER_ORDER_PATH_ID, UUID.randomUUID())
                        .with(jwtRequestPostProcessor))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
