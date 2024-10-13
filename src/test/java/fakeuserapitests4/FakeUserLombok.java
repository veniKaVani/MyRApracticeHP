package fakeuserapitests4;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FakeUserLombok {
	
	private String email;
	private String username;
	private String password;
	private String phone;
	
	private Name name;
	private Address address;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Name{
		private String firstname;
		private String lastname;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Address{
		private String city;
		private String street;
		private int number;
		private String zipcode;
		
		private Geolocation geoLoc;
		
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		@Builder
		public static class Geolocation{
			private String lat;
			@JsonProperty("long")
			private String longitude;
		}
	}

}
