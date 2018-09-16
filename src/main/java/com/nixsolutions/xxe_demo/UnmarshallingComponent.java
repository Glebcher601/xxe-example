package com.nixsolutions.xxe_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;

@SuppressWarnings("unchecked")
@Component
public class UnmarshallingComponent
{
  @Autowired
  private XMLInputFactory xmlInputFactory;

  @Autowired
  private JAXBContext jaxbContext;

  public <T> T unmarshall(String source)
  {
    try
    {
      XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new StringReader(source));
      return (T) jaxbContext.createUnmarshaller().unmarshal(xmlStreamReader);
    } catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
}
