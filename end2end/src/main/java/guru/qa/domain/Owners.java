package guru.qa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Owners {
	int id;
	String firstName;
	String lastName;
	String address;
	String city;
	String telephone;
}
