package stmall.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stmall.domain.CompleteDeliveryCommand;
import stmall.domain.CompleteReturnCommand;
import stmall.domain.Delivery;
import stmall.domain.DeliveryRepository;
import stmall.service.DeliveryService;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("deliveryService")
@Transactional
public class DeliveryServiceImpl extends EgovAbstractServiceImpl implements DeliveryService {

     private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    @Autowired
    DeliveryRepository deliveryRepository;

    @Override
    public List<Delivery> getAllDeliveries() throws Exception {
        // Get all deliveries
        return StreamSupport.stream(deliveryRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Delivery> getDeliveryById(Long id) throws Exception {
        // Get a delivery by ID
        return deliveryRepository.findById(id);
    }

    @Override
    public Delivery createDelivery(Delivery delivery) throws Exception {
        // Create a new delivery
        return deliveryRepository.save(delivery);
    }    

    @Override
    public Delivery updateDelivery(Delivery delivery)  throws Exception {
        // Update an existing delivery via DeliveryService
        if (deliveryRepository.existsById(delivery.getId())) {
            return deliveryRepository.save(delivery);
        } else {
            throw processException("info.nodata.msg");
        }
    }

    @Override
    public void deleteDelivery(Long id) throws Exception {
        // Delete a delivery 
        deliveryRepository.deleteById(id);
    }

    @Override
	public Delivery completeDelivery(CompleteDeliveryCommand completeDeliveryCommand) throws Exception {        

        // You can implement logic here, or call the domain method of the Delivery.
        
        /** Choice 1:  implement logic here        
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(completeDeliveryCommand.getDeliveryId());

        if (optionalDelivery.isPresent()) {
            Delivery delivery = optionalDelivery.get();
            
            // business Logic....
            deliveryRepository.save(delivery);

            return delivery;
        } else {
            throw processException("info.nodata.msg");
        }
       

        /** Choice 2:  call the domain method of the Delivery.   
        return Delivery.completeDelivery(completeDeliveryCommand);
           */  
    }

    @Override
	public Delivery completeReturn(CompleteReturnCommand completeReturnCommand) throws Exception { 
         
        // You can implement logic here, or call the domain method of the Delivery.
        
        /** Choice 1:  implement logic here
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(completeReturnCommand.getDeliveryId());

        if (optionalDelivery.isPresent()) {
            Delivery delivery = optionalDelivery.get();

            // business Logic....
            deliveryRepository.save(delivery);
            return delivery;
        } else {
            throw processException("info.nodata.msg");
        }
        */

        /** Choice 2:  call the domain method of the Delivery.
        return Delivery.completeReturn(completeReturnCommand);
        */
    }    
}
