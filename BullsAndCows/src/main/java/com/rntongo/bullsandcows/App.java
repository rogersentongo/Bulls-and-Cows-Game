/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.bullsandcows;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author rogersentongo
 */

//Annotation to tell compiler this is a Spring application
@SpringBootApplication
public class App {
    
    
    public static void main(String[] args) {
        
        //Run the program using Spring
        SpringApplication.run(App.class, args);
        
    }
    
}
