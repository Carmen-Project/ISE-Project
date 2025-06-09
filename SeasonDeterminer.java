import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

// Determine Season Based on Hemisphere
public class SeasonDeterminer {
    private final Map<String, String> countryHemisphere = new HashMap<>(); // to store country (hemisphere mapping)

    public SeasonDeterminer() {
        countryHemisphere.put("USA", "Northern");
        countryHemisphere.put("China", "Northern");
        countryHemisphere.put("Germany", "Northern");
        countryHemisphere.put("Australia", "Southern");
        countryHemisphere.put("New Zealand", "Southern");
        countryHemisphere.put("Brazil", "Southern");
    }

    public String getHemisphere(String country) {
        if (country == null) {
            return "Unknown";// Null country
        }

        country = country.trim();

        if (country.equals("USA")) {
            return "Northern";
        } else if (country.equals("China")) {
            return "Northern";
        } else if (country.equals("Germany")) {
            return "Northern";
        } else if (country.equals("Australia")) {
            return "Southern";
        } else if (country.equals("New Zealand")) {
            return "Southern";
        } else if (country.equals("Brazil")) {
            return "Southern";
        } else {
            return "Unknown";
        }
    }

    public String getSeason(LocalDate date, String hemisphere) {
        if (date == null || hemisphere == null || hemisphere.equals("Unknown")) {
            return "Invalid country or hemisphere";// Invalid country
        }

        // Determine Current Date
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        if (hemisphere.equals("Northern"))// Nouthern Hemisphere
        {
            if ((month == 12 && day >= 21) || month == 1 || month == 2 || (month == 3 && day < 20)) {
                return "Winter ❄️❄️";
            }
            if ((month == 3 && day >= 20) || month == 4 || month == 5 || (month == 6 && day < 21)) {
                return "Spring 🌸🌸";
            }
            if ((month == 6 && day >= 21) || month == 7 || month == 8 || (month == 9 && day < 22)) {
                return "Summer ☀️☀️";
            }
            return "Autumn 🍂🍂"; // Sept 22 to Dec 20
        } else { // Southern Hemisphere
            if ((month == 6 && day >= 21) || month == 7 || month == 8 || (month == 9 && day < 22)) {
                return "Winter ❄️❄️";
            }
            if ((month == 9 && day >= 22) || month == 10 || month == 11 || (month == 12 && day < 21)) {
                return "Spring 🌸🌸";
            }
            if ((month == 12 && day >= 21) || month == 1 || month == 2 || (month == 3 && day < 20)) {
                return "Summer ☀️☀️";
            }
            return "Autumn 🍂🍂"; // March 20 to June 20
        }
    }
}
