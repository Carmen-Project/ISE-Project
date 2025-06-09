import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TimeConverter {

    // GMT for calculation
    private static final Map<String, Integer> gmt = new HashMap<>();
    // Time zone IDs for time conversion
    private static final Map<String, String> zoneId = new HashMap<>();

    static {
        gmt.put("USA", -4);
        gmt.put("China", 8);
        gmt.put("Germany", 2);
        gmt.put("Australia", 10);
        gmt.put("New Zealand", 12);
        gmt.put("Brazil", -3);

        zoneId.put("USA", "America/New_York");
        zoneId.put("China", "Asia/Shanghai");
        zoneId.put("Germany", "Europe/Berlin");
        zoneId.put("Australia", "Australia/Sydney");
        zoneId.put("New Zealand", "Pacific/Auckland");
        zoneId.put("Brazil", "America/Sao_Paulo");
    }

    public static void timeConverter() {
        Scanner scanner = new Scanner(System.in);

        // System.out.println("Available countries: USA, China, Germany, Australia, New
        // Zealand, Brazil");

        // Input source and target country
        System.out.println("Enter source country:");
        String sourceCountry = scanner.nextLine();

        System.out.println("Enter target country:");
        String targetCountry = scanner.nextLine();

        // Input local date and time
        System.out.println("Enter local date and time in source country (yyyy-MM-ddTHH:mm), e.g., 2025-06-04T18:00:");
        String dateTime = scanner.nextLine();

        try {
            // GMT conversion
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime); // parse dataTime into object
            LocalDate sourceDate = localDateTime.toLocalDate(); // extract date
            int sourceTime = localDateTime.getHour(); // extract time

            int timeDiff = gmt.get(targetCountry) - gmt.get(sourceCountry); // calculate time difference
            int targetTime = sourceTime + timeDiff; // calculate target time
            LocalDate targetDate = sourceDate;

            if (targetTime >= 24) { // If the time is ≥ 24, it goes to next day
                targetTime %= 24;
                targetDate = targetDate.plusDays(1);
            } else if (targetTime < 0) { // If the time is < 0, it is in previous day
                targetTime = (24 + targetTime) % 24;
                targetDate = targetDate.minusDays(1);
            }

            // ZonedDateTime conversion
            ZoneId sourceZone = ZoneId.of(zoneId.get(sourceCountry));
            ZoneId targetZone = ZoneId.of(zoneId.get(targetCountry));

            ZonedDateTime sourceZoned = ZonedDateTime.of(localDateTime, sourceZone); // create source time object
            ZonedDateTime targetZoned = sourceZoned.withZoneSameInstant(targetZone); // convert the time

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

            // Output
            System.out.println("\n--- GMT Conversion ---");
            System.out.println("Time Difference: " + timeDiff + " hours");
            System.out.println("Target Local Time: " + String.format("%02d:00", targetTime));
            System.out.println("Target Date: " + targetDate);

            System.out.println("\n--- ZonedDateTime Conversion ---");
            System.out.println("Source Zoned Time: " + sourceZoned.format(formatter));
            System.out.println("Target Zoned Time: " + targetZoned.format(formatter) + "\n");

        } catch (Exception e) {
            System.out.println("Invalid input. Please check your date/time format or country names.\n");
        }
    }
}
