package guru.qa;

import com.codeborne.selenide.Selenide;
import guru.qa.domain.Owners;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FindOwnerTests {

	private IOwnersManager om = new OwnersManagerNG();
	private int createdOwnersId;


	@BeforeEach
	void addOwner() {
		om.createOwners(Owners.builder()
			.firstName("Igor")
			.lastName("Pavlov")
			.address("Kazan")
			.city("Tatarstan")
			.telephone("7000000000")
			.build());

	}
	@AfterEach
	void releaseOwner() {
		om.deleteOwners(createdOwnersId);
	}

	@RepeatedTest(2)
	@Test
	void findOwnerTest(){
		Selenide.open("http://localhost:8080/owners/find");
		$("#lastName").setValue("Tuchs");
		$("button[type='submit']").click();
		$("table.table").should(visible);
		$$("tr").find(text("Name")).should(text("Igor Pavlov"));
	}
}
