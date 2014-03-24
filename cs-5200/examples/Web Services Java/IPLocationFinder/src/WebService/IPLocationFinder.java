package WebService;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import net.webservicex.GeoIPServiceSoap;

public class IPLocationFinder 
{
	public static void main(String[] args) 
	{
		if (args.length != 1)
			System.out.println("You have to pass in ONE IP Address...");
		else
		{
			String ipAddress = args[0];
			GeoIPService ipservice = new GeoIPService();
			GeoIPServiceSoap geoIPServiceSoap = ipservice.getGeoIPServiceSoap();
			GeoIP geoip = geoIPServiceSoap.getGeoIP(ipAddress);
			System.out.println("The location of the \"" + ipAddress + "\" IPAddress is in: " + geoip.getCountryName());
		}
	}

}
