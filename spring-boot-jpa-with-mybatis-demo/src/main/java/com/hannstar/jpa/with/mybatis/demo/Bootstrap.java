package com.hannstar.jpa.with.mybatis.demo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

/**
 * @author jiangxinjun
 * @date 2019/06/04
 */
@SpringBootApplication
public class Bootstrap {

    @Bean
    public JPAQueryFactory initFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }
}
