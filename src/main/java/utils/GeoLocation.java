package utils;

/**
 * @author AH19309
 */
public class GeoLocation {

        public String countryName;
        public String countryGPSLocation;
    public static String countryLocation;
    public static GeoLocation _geoLocation;

        public GeoLocation() {
        }

        public static void setGeoLocationFromContext() {
            GeoLocation geoLocation = new GeoLocation();
            geoLocation.countryName = countryLocation;
            geoLocation.countryGPSLocation = countryLocation.equalsIgnoreCase(CommonUtils.CountryGPSLocation.FR.name()) ? CommonUtils.CountryGPSLocation.FR.getGpsLocation() : CommonUtils.CountryGPSLocation.GB.getGpsLocation();
            _geoLocation = geoLocation;
    }


}
