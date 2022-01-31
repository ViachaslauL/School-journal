package by.itacademy.javaenterprise.lepnikau.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "by.itacademy.javaenterprise.lepnikau.dao")
public class SpringPersistenceConfig {

    private final Environment environment;

    @Autowired
    public SpringPersistenceConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public BasicDataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        dataSource.setInitialSize(Integer.parseInt(
                Objects.requireNonNull(environment.getProperty("spring.datasource.dbcp2.initial-size"))
        ));
        dataSource.setMaxTotal(Integer.parseInt(
                Objects.requireNonNull(environment.getProperty("spring.datasource.dbcp2.max-idle"))
        ));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setPackagesToScan("by.itacademy.javaenterprise.lepnikau.entity");
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate() {
        TransactionTemplate template = new TransactionTemplate();
        template.setTransactionManager(transactionManager());
        template.setTimeout(10000);
        return template;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("spring.jooq.sql-dialect",
                environment.getRequiredProperty("spring.jooq.sql-dialect"));

        properties.put("spring.jpa.show-sql",
                environment.getRequiredProperty("spring.jpa.show-sql"));

        properties.put("spring.jpa.properties.hibernate.format_sql",
                environment.getRequiredProperty("spring.jpa.properties.hibernate.format_sql"));

        return properties;
    }
}
