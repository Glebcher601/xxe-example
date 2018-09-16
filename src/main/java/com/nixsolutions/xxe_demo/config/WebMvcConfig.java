package com.nixsolutions.xxe_demo.config;

import com.nixsolutions.xxe_demo.model.Note;
import com.nixsolutions.xxe_demo.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLInputFactory;
import java.util.Arrays;
import java.util.List;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.nixsolutions.xxe_demo")
public class WebMvcConfig
{
  @Bean
  public XMLInputFactory xmlInputFactory()
  {
    XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
    //xmlInputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
    //xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
    return xmlInputFactory;
  }

  @Bean
  public JAXBContext jaxbContext() throws JAXBException
  {
    return JAXBContext.newInstance(xmlDomainClasses());
  }

  @Bean
  public ContentNegotiatingViewResolver contentNegotiatingViewResolver()
  {
    ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();

    List<View> views = Arrays.asList(new View[]{new MappingJackson2XmlView()});
    contentNegotiatingViewResolver.setDefaultViews(views);

    return contentNegotiatingViewResolver;
  }

  public Class[] xmlDomainClasses()
  {
    return new Class[]{User.class, Note.class};
  }
}
