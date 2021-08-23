package guru.qa;

import guru.qa.domain.Owners;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OwnersManager implements IOwnersManager {

	private DataSource ds = DataSourceProvider.INSTANCE.getDataSource();

	@Override
	public int createOwners(Owners owners)  {
		String rawSql = "INSERT INTO owners (first_name, last_name, address, city, telephone)\n" +
			"VALUES (?, ?, ?, ?, ?)";

	try (Connection connection = ds.getConnection();
			 PreparedStatement ps = connection.prepareStatement(rawSql,
			 Statement.RETURN_GENERATED_KEYS
	         )) {
	        ps.setString(1, owners.getFirstName());
			ps.setString(2, owners.getLastName());
			ps.setString(3, owners.getAddress());
			ps.setString(4, owners.getCity());
			ps.setString(5, owners.getTelephone());
			ps.executeUpdate();

			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	return -1;
	}

	@Override
	public void deleteOwners(int id)
	{
		try (Connection connection = ds.getConnection();
			 PreparedStatement ps = connection.prepareStatement(
				 "DELETE FROM owners WHERE id = ?"
			 )) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Owners>findByLastName(String lastName) {
		List<Owners> result = new ArrayList<>();

		try (Connection connection = ds.getConnection();
			 PreparedStatement ps = connection.prepareStatement(
				 "SELECT * FROM owners WHERE last_name = ?"
			 )) {
			ps.setString(1, lastName);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				result.add(Owners.builder()
					.firstName(resultSet.getString("first_name"))
					.lastName(resultSet.getString("last_name"))
					.address(resultSet.getString("address"))
					.city(resultSet.getString("city"))
					.telephone(resultSet.getString("telephone"))
					.build());
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
}
