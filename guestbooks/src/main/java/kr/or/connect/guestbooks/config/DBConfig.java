package kr.or.connect.guestbooks.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement // 트랜잭션과 관련된 설정을 자동으로 해준다.
// 단 사용자 간의 트랜잭션 처리를 위한 platrformtransactionmanager를 설정하기 위해서는 transactionmanagementconfigurer를 구현하고
// annotationDriventTransactionManager 메서드를 오버라이딩 해야한다.
public class DBConfig implements TransactionManagementConfigurer{
	
	private String driverClassName = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=utf8";
	private String username = "connectuser";
	private String password = "0000";
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManger();
	}

	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	}
}
