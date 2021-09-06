package utils;

import java.util.List;
import java.util.Optional;

/**
 * @author AH19309
 */
public class CommonUtils {

    public static String countryLocation;
    public static String FeatureName = "";

    public static void setFeatureName(String scenarioId) {
        if (FeatureName == "" || !scenarioId.contains(FeatureName)) {
            FeatureName = scenarioId.replaceFirst(".*/(\\w+).*", "$1");
        }

    }


    public static void setCountryLocation(List<String> tags) {
        Optional<String> location = tags.stream().filter((l) -> {
            return l.startsWith("@Country");
        }).findFirst();
        countryLocation = (String)location.map((s) -> {
            return s.replace("@Country_", "");
        }).orElse(CountryGPSLocation.GB.name());
        GeoLocation.setGeoLocationFromContext();
    }

    public static enum CountryGPSLocation {
        GB("51.494,-0.275372"),
        FR("48.856614,2.3522219");

        private String gpsLocation;

        private CountryGPSLocation(String gpsLocation) {
            this.gpsLocation = gpsLocation;
        }

        public String getGpsLocation() {
            return this.gpsLocation;
        }
    }

    public static void setGeoLocationFromContext() {
        GeoLocation geoLocation = new GeoLocation();
        geoLocation.countryName = countryLocation;
        geoLocation.countryGPSLocation = countryLocation.equalsIgnoreCase(CountryGPSLocation.FR.name()) ? CountryGPSLocation.FR.getGpsLocation() : CountryGPSLocation.GB.getGpsLocation();
        GeoLocation.setGeoLocationFromContext();
    }

    public static boolean isApiProfile(String activeProfile) {
        return EnumConstants.Platform.api.name().equalsIgnoreCase(activeProfile);
    }

}
