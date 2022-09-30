package com.rest.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.rest.dao")
@EnableJpaRepositories(basePackages = "com.rest.dao")
@EnableTransactionManagement
public class DaoConfig {
    @Bean
    public DataSource getDataSource(@Value("${datasource.schema}") String schema, @Value("${datasource.script}") String script) {
        String jdbcUrl = String.format("jdbc:h2:mem:%s;INIT=runscript from '%s'", schema, script);
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.h2.Driver");
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername("sa");
        hikariConfig.setPassword("");
        hikariConfig.setPoolName("Billionaire Connection Pool");
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "entityManagerFactory")    // Spring Data JPA by default looks for an EntityManagerFactory named entityManagerFactory. Check: https://stackoverflow.com/questions/24520602/spring-data-jpa-no-bean-named-entitymanagerfactory-is-defined-injection-of-a
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("com.rest.model");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties properties = new Properties();
        properties.put("hbm2ddl.auto", "create");
        entityManagerFactoryBean.setJpaProperties(properties);
        return entityManagerFactoryBean;
    }

    @Bean(name = "transactionManager")  // Spring Data JPA by default looks for an TransactionManager named transactionManager. Check: https://stackoverflow.com/questions/24520602/spring-data-jpa-no-bean-named-entitymanagerfactory-is-defined-injection-of-a
    public PlatformTransactionManager getTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
