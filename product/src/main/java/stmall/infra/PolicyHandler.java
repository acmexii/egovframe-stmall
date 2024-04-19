package stmall.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import stmall.config.kafka.KafkaProcessor;
import stmall.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    InventoryRepository inventoryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryCompleted'"
    )
    public void wheneverDeliveryCompleted_DecreseStock(
        @Payload DeliveryCompleted deliveryCompleted
    ) {
        DeliveryCompleted event = deliveryCompleted;
        System.out.println(
            "\n\n##### listener DecreseStock : " + deliveryCompleted + "\n\n"
        );

        // Sample Logic //
        Inventory.decreseStock(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryCollected'"
    )
    public void wheneverDeliveryCollected_IncreseStock(
        @Payload DeliveryCollected deliveryCollected
    ) {
        DeliveryCollected event = deliveryCollected;
        System.out.println(
            "\n\n##### listener IncreseStock : " + deliveryCollected + "\n\n"
        );

        // Sample Logic //
        Inventory.increseStock(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
