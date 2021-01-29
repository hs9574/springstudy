package kr.or.ddit.ioc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//<context:component-scan base-package="kr.or.ddit"></context:component-scan>
@ComponentScan(basePackages = {"kr.or.ddit"})
@Configuration
public class ComponentScanJavaConfig {
	
}
