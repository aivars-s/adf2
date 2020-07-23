package aivars.adf.order;

import aivars.adf.common.Attributes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private static final String CREATE_TEMPLATE = "create";
    private static final String DETAILS_REDIRECT = "redirect:/orders/";
    private static final String DETAILS_TEMPLATE = "details";
    private static final String SEARCH_TEMPLATE = "search";

    private final OrderMapper mapper;
    private final OrderService service;

    @GetMapping
    public String searchOrders(OrderSearchRequest req, Model model) {
        model.addAttribute(Attributes.ORDERS, mapper.toOrderResponse(service.searchOrders(req)));
        model.addAttribute(Attributes.FORM, req);
        return SEARCH_TEMPLATE;
    }

    @GetMapping("{id}")
    public String getOrder(@PathVariable long id, Model model) {
        model.addAttribute(Attributes.ORDER, mapper.toOrderDetailedResponse(service.getOrder(id)));
        return DETAILS_TEMPLATE;
    }

    @GetMapping("new")
    public String getOrderForm(Model model) {
        model.addAttribute(Attributes.ORDER, new OrderCreateRequest());
        return CREATE_TEMPLATE;
    }

    @PostMapping("new")
    public String postOrderForm(@Valid OrderCreateRequest req, Model model) {
            Order order = service.createOrder(req);
            return DETAILS_REDIRECT + order.getId();
    }

}
