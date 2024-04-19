package stmall.infra;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import stmall.domain.*;
import stmall.service.*;

@RestController
// @RequestMapping(value="/deliveries")
@Transactional
public class DeliveryController {

    @Resource(name = "deliveryService")
	private DeliveryService deliveryService;

    @GetMapping("/delivery")
    public List<Delivery> getAllDeliveries() throws Exception {
        // Get all deliveries via DeliveryService
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/delivery/{id}")
    public Optional<Delivery> getDeliveryById(@PathVariable Long id) throws Exception {
        // Get a delivery by ID via DeliveryService
        return deliveryService.getDeliveryById(id);
    }

    @PostMapping("/delivery")
    public Delivery createDelivery(@RequestBody Delivery delivery) throws Exception {
        // Create a new delivery via DeliveryServcie
        return deliveryService.createDelivery(delivery);
    }

    @PutMapping("/delivery/{id}")
    public Delivery updateDelivery(@PathVariable Long id, @RequestBody Delivery delivery) throws Exception {
        // Update an existing delivery via DeliveryService
        return deliveryService.updateDelivery(delivery);
    }

    @DeleteMapping("/delivery/{id}")
    public void deleteDelivery(@PathVariable Long id) throws Exception {
        // Delete a delivery via DeliveryService
        deliveryService.deleteDelivery(id);
    }

    @RequestMapping(value = "delivery/{id}/completedelivery", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public Delivery completeDelivery(        
        @PathVariable(value = "id") Long id,
        @RequestBody CompleteDeliveryCommand completeDeliveryCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {

        return deliveryService.completeDelivery(completeDeliveryCommand);

    }

    @RequestMapping(value = "delivery/{id}/completereturn", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public Delivery completeReturn(
        @PathVariable(value = "id") Long id,
        @RequestBody CompleteReturnCommand completeReturnCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {

        return deliveryService.completeReturn(completeReturnCommand);

    }
}
