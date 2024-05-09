
package br.com.kodcar.serialization;

import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;


//Configuração para usar o MediaType yaml


public class YamlJackson2HttpMesageConverter extends AbstractJackson2HttpMessageConverter{

	public YamlJackson2HttpMesageConverter() {
		
		super(new YAMLMapper()
				.setSerializationInclusion(JsonInclude.Include.NON_NULL),
				MediaType.parseMediaType("application/x-yaml"));
	}
	


}
				

	

