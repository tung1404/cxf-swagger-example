package com.kp.swasthik;

import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;


@Configuration
public class CxfConfig {

	@Bean
	public JacksonJaxbJsonProvider jaxbJsonProvider(ObjectMapper mapper) {
		JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
		provider.setMapper(mapper);
		return provider;
	}
	
	@Bean
	public Swagger2Feature swaggerFeature() {
		Swagger2Feature feature = new Swagger2Feature();
		feature.setDescription("KP Description");
		return feature;

	}

}
