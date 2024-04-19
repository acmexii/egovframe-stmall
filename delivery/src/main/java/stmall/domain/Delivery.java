package stmall.domain;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import stmall.DeliveryApplication;

@Entity
@Table(name = "Delivery_table")
@Data
//<<< DDD / Aggregate Root
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Long userId;
    private Long productId;
    private String productName;
    private Integer qty;
    private String status;
    private Date deliveryDt;

    @PostPersist
    public void onPostPersist() {
        DeliveryHasStarted deliveryHasStarted = new DeliveryHasStarted(this);
        deliveryHasStarted.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        DeliveryCanceled deliveryCanceled = new DeliveryCanceled(this);
        deliveryCanceled.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {}

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public static Delivery completeDelivery(CompleteDeliveryCommand completeDeliveryCommand) {
        //implement business logic here:
        Delivery delivery = null;

        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(delivery);
        deliveryCompleted.publishAfterCommit();

        return delivery;
    }

    public static Delivery completeReturn(CompleteReturnCommand completeReturnCommand) {
         //implement business logic here:
        Delivery delivery = null;

        DeliveryCollected deliveryCollected = new DeliveryCollected(delivery);
        deliveryCollected.publishAfterCommit();

        return delivery;
    }


    public static void startDelivery(OrderPlaced orderPlaced) {
        //implement business logic here:

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DeliveryHasStarted deliveryHasStarted = new DeliveryHasStarted(delivery);
        deliveryHasStarted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DeliveryHasStarted deliveryHasStarted = new DeliveryHasStarted(delivery);
            deliveryHasStarted.publishAfterCommit();

         });
        */

    }

    public static void cancelDelivery(OrderCanceled orderCanceled) {
        //implement business logic here:

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DeliveryCanceled deliveryCanceled = new DeliveryCanceled(delivery);
        deliveryCanceled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderCanceled.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DeliveryCanceled deliveryCanceled = new DeliveryCanceled(delivery);
            deliveryCanceled.publishAfterCommit();

         });
        */

    }

}
