package guru.qa;

import guru.qa.domain.Owners;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static sun.security.krb5.Confounder.intValue;

public class OwnersManagerNG implements IOwnersManager {


	private JdbcTemplate jdbcTemplate = new JdbcTemplate(
		DataSourceProvider.INSTANCE.getDataSource()
	);

	@Override
	public int createOwners(Owners owners) {
		return new SimpleJdbcInsert(jdbcTemplate)
			.withTableName("owners")
			.usingGeneratedKeyColumns("id")
			.executeAndReturnKey(intValue(),

				Map<String, String> map = new HashMap<>(){{
				put("first_name", owners.getFirstName()),
				put("last_name", owners.getLastName()),
				put("address", owners.getAddress()),
				put("city", owners.getCity()),
				put("telephone", owners.getTelephone()).initValue()).
	}

	@Override
	public void deleteOwners(int id) {
		jdbcTemplate.update("DELETE FROM owners WHERE id = ?", id);
	}

	@Override
	public List<Owners> findByLastName(String lastName) {
		return null;
	}
  }
}
