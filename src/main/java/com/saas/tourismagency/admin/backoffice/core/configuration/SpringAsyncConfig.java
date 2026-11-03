package com.saas.tourismagency.admin.backoffice.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class SpringAsyncConfig {

    @Bean(name = "threadPoolTaskExecutor")
    public Executor asyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);//HILO MINIMO QUE SE VA A USAR EN LA APLICACION EJEMPLO SI TENEMOS 4 CORE, PONDREMOS 4 O MENOS
        executor.setMaxPoolSize(6);//EL MAXIMO DE HILO QUE SE VA A CREAR DESPUES DE HABERSE CREADO LOS 3 ANTERIORES
        executor.setQueueCapacity(50);//EL MAXIMO DE HILO QUE SE PUEDE CREAR EN TOTAL PERO EN ESPERA
        executor.setThreadNamePrefix("TOURY_GO_ASYNC");//NOMBRE PARA COLOCAR AL HILO A CREAR
        executor.initialize();
        return new DelegatingSecurityContextAsyncTaskExecutor(executor);
    }
}
