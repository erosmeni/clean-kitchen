package ee.cleankitchen.orderservice;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Implementation for this will be provided by separate Order service team
 */
public interface OrderServiceClient {

    /**
     * Get list of existing orders to be delivered on {@code date}.
     * <p>
     * Each Map object in the list represents one Order record. It has the following entries:
     * <ul>
     *     <li>key: date, value: {@code LocalDate} the date when this order will be delivered</li>
     *     <li>key: time, value: {@code LocalTime} the time when this order will be delivered</li>
     *     <li>key: orderId, value: {@code String} unique order identifier</li>
     *     <li>key: customerId, value: {@code String} unique customer identifier</li>
     * </ul>
     * An example response looks like this:
     * <pre>{@code
     *  List.of(
     *        Map.of("date", LocalDate.of(2021, 11, 29), "time", LocalTime.of(10, 30), "orderId", "123", "customerId", "a-111"),
     *        Map.of("date", LocalDate.of(2021, 11, 29), "time", LocalTime.of(10, 30), "orderId", "223", "customerId", "a-211"),
     *        Map.of("date", LocalDate.of(2021, 11, 29), "time", LocalTime.of(12, 30), "orderId", "323", "customerId", "a-211"),
     *        Map.of("date", LocalDate.of(2021, 11, 29), "time", LocalTime.of(18, 30), "orderId", "423", "customerId", "a-411")
     *  )
     * }</pre>
     */
    List<Map<String, Object>> getBy(LocalDate date);
}
