import ee.cleankitchen.orderservice.OrderServiceClient;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {

    OrderServiceClient orderServiceClient;

    Application(OrderServiceClient orderServiceClient) {
        this.orderServiceClient = orderServiceClient;
    }

    private static int limitForMonday = 4;
    private static int limitForOtherDays = 2;

    public List<Object> availableDeliveryTimes(LocalDate date) {

        DayOfWeek day = date.getDayOfWeek();

        List<Map<String, Object>> existingOrders = this.orderServiceClient.getBy(date);

        // get a list of available times
        var availableTimes = existingOrders
                .stream()
                .map(order -> Map.of("time", order.get("time"))).collect(Collectors.toList());
        Map<Object, Integer> counts = new HashMap<>();

        // create a key value pair with time and orders for that time
        for(Map<String, Object> order: availableTimes) {
            for (Object c : order.values()) {
                int value = counts.get(c) == null ? 0 : counts.get(c);
                counts.put(c, value + 1);
            }
        }

        //check limit for Monday or other days
        if(day == DayOfWeek.MONDAY) {
            return counts.entrySet()
                    .stream()
                    .filter(count -> count.getValue() < limitForMonday)
                    .map(count -> count.getKey())
                    .collect(Collectors.toList());

        } else {
            return counts.entrySet()
                    .stream()
                    .filter(count -> count.getValue() < limitForOtherDays)
                    .map(count -> count.getKey())
                    .collect(Collectors.toList());
        }
    }

}
