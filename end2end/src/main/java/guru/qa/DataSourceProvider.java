package guru.qa;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public enum DataSourceProvider {
	INSTANCE;

	public DataSource dataSource;

	public DataSource getDataSource(){
		if (dataSource == null) {
			MysqlDataSource mysqlDataSource = new MysqlDataSource();
			mysqlDataSource.setUrl("jdbc:mysql://localhost:3306/petclinic");
			mysqlDataSource.setUser("petclinic");
			mysqlDataSource.setPassword("petclinic");
		}
		return dataSource;
	}
}
