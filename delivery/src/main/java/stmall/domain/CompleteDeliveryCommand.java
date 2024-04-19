package stmall.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CompleteDeliveryCommand {

    private Long deliveryId;
    private String status;
}
