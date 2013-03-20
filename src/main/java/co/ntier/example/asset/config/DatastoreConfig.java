package co.ntier.example.asset.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="co.ntier.example.asset.repo")
public class DatastoreConfig {
	
	@SuppressWarnings("serial")
	private final Properties hibernateProperties = new Properties() {{
		put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		put("hibernate.hbm2ddl.auto", "update");
		put("hibernate.show_sql", true);
	}};

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setPackagesToScan("co.ntier.example.asset.model");
		factory.setJpaProperties(hibernateProperties);
		factory.setPersistenceProvider(new HibernatePersistence());
		return factory;
	}
	
	@Bean
	public DataSource getHsqlDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		ds.setUrl("jdbc:hsqldb:mem:.");
		ds.setUsername("sa");
		ds.setPassword("");
		return ds;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager mgr = new JpaTransactionManager(emf);
		mgr.afterPropertiesSet();
		return mgr;
	}
}
