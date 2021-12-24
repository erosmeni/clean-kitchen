import ee.cleankitchen.orderservice.OrderServiceClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApplicationTest {

    private Application application;

    private OrderServiceClient orderServiceClient;

    @BeforeAll
    public void setup() {

        class OrderServiceClientImpl implements OrderServiceClient {

            @Override
            public List<Map<String, Object>> getBy(LocalDate date) {
                if(date.isEqual(LocalDate.of(2021, 11, 22))) {
                    return List.of(
                            Map.of("date", LocalDate.of(2021, 11, 30), "time", LocalTime.of(10, 30), "orderId", "123", "customerId", "a-111"),
                            Map.of("date", LocalDate.of(2021, 11, 30), "time", LocalTime.of(10, 30), "orderId", "223", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 11, 30), "time", LocalTime.of(12, 30), "orderId", "323", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 11, 30), "time", LocalTime.of(18, 30), "orderId", "423", "customerId", "a-411")
                    );
                } else if(date.isEqual(LocalDate.of(2021, 11, 29))) {
                    return List.of(
                            Map.of("date", LocalDate.of(2021, 11, 29), "time", LocalTime.of(10, 30), "orderId", "123", "customerId", "a-111"),
                            Map.of("date", LocalDate.of(2021, 11, 29), "time", LocalTime.of(10, 30), "orderId", "223", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 11, 29), "time", LocalTime.of(10, 30), "orderId", "223", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 11, 29), "time", LocalTime.of(10, 30), "orderId", "223", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 11, 29), "time", LocalTime.of(12, 30), "orderId", "323", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 11, 29), "time", LocalTime.of(18, 30), "orderId", "423", "customerId", "a-411")
                    );
                } else if(date.isEqual(LocalDate.of(2021, 11, 30))) {
                    return List.of(
                            Map.of("date", LocalDate.of(2021, 11, 30), "time", LocalTime.of(10, 30), "orderId", "123", "customerId", "a-111"),
                            Map.of("date", LocalDate.of(2021, 11, 30), "time", LocalTime.of(10, 30), "orderId", "223", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 11, 30), "time", LocalTime.of(12, 30), "orderId", "323", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 11, 30), "time", LocalTime.of(18, 30), "orderId", "423", "customerId", "a-411")
                    );
                } else if(date.isEqual(LocalDate.of(2021, 12, 1))) {
                    return List.of(
                            Map.of("date", LocalDate.of(2021, 12, 1), "time", LocalTime.of(10, 30), "orderId", "123", "customerId", "a-111"),
                            Map.of("date", LocalDate.of(2021, 12, 1), "time", LocalTime.of(10, 30), "orderId", "223", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 12, 1), "time", LocalTime.of(10, 30), "orderId", "223", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 12, 1), "time", LocalTime.of(10, 30), "orderId", "223", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 12, 1), "time", LocalTime.of(12, 30), "orderId", "223", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 12, 1), "time", LocalTime.of(12, 30), "orderId", "223", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 12, 1), "time", LocalTime.of(12, 30), "orderId", "223", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 12, 1), "time", LocalTime.of(12, 30), "orderId", "223", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 12, 1), "time", LocalTime.of(18, 30), "orderId", "223", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 12, 1), "time", LocalTime.of(18, 30), "orderId", "323", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 12, 1), "time", LocalTime.of(18, 30), "orderId", "223", "customerId", "a-211"),
                            Map.of("date", LocalDate.of(2021, 12, 1), "time", LocalTime.of(18, 30), "orderId", "423", "customerId", "a-411")
                    );
                } else {
                    return new ArrayList<>();
                }

            }
        }

        orderServiceClient = new OrderServiceClientImpl();
        application = new Application(orderServiceClient);
    }

    @Test
    public void assertMonday() {
        var availableDeliveryTimes = application.availableDeliveryTimes(LocalDate.of(2021, 11, 22));
        assertNotNull(availableDeliveryTimes);
        assertEquals(availableDeliveryTimes,
                List.of(LocalTime.of(10, 30),LocalTime.of(18,30), LocalTime.of(12,30)));
    }

    @Test
    public void assertMondayWithFourOrders() {
        var availableDeliveryTimes = application.availableDeliveryTimes(LocalDate.of(2021, 11, 29));
        assertNotNull(availableDeliveryTimes);
        assertEquals(availableDeliveryTimes, List.of(LocalTime.of(18,30), LocalTime.of(12,30)));
    }

    @Test
    public void assertOtherDay() {
        var availableDeliveryTimes = application.availableDeliveryTimes(LocalDate.of(2021, 11, 30));
        assertNotNull(availableDeliveryTimes);
        assertEquals(availableDeliveryTimes, List.of(LocalTime.of(18,30), LocalTime.of(12,30)));
    }

    @Test
    public void assertNoAvailableTimes() {
        var availableDeliveryTimes = application.availableDeliveryTimes(LocalDate.of(2021, 12, 1));
        assertNotNull(availableDeliveryTimes);
        assertEquals(availableDeliveryTimes, new ArrayList<>());
    }

    @Test
    public void assertWhenEmptyArray() {
        var availableDeliveryTimes = application.availableDeliveryTimes(LocalDate.of(2021, 12, 29));
        assertNotNull(availableDeliveryTimes);
        assertEquals(availableDeliveryTimes, new ArrayList<>());
    }

}
