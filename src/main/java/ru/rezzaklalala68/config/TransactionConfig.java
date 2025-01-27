package ru.rezzaklalala68.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableTransactionManagement
public class TransactionConfig {
    private final JpaConfig jpaConfig;
@Autowired
    public TransactionConfig(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(jpaConfig.entityManagerFactory().getObject());
        return transactionManager;
    }
}
