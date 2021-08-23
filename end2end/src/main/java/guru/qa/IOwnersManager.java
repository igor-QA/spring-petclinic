package guru.qa;

import guru.qa.domain.Owners;
import java.util.List;

public interface IOwnersManager {
	int createOwners(Owners owners);

	void deleteOwners(int id);

	List<Owners> findByLastName(String lastName);
}
