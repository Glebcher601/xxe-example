package com.nixsolutions.xxe_demo;

import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import org.xml.sax.InputSource;

@Path("/model")
public class ModelResource
{
  private static final Map<Integer, Model> DB_IMITATION = new ConcurrentHashMap<Integer, Model>();
  static volatile int currentId = 0;

  static
  {
    Model m1 = new Model(currentId++, "data1", "type1");
    Model m2 = new Model(currentId++, "data2", "type2");
    Model m3 = new Model(currentId++, "data3", "type3");
    Model m4 = new Model(currentId++, "data4", "type4");
    Model m5 = new Model(currentId++, "data5", "type5");
    Model m6 = new Model(currentId++, "data6", "type6");
    Model m7 = new Model(currentId++, "data7", "type7");

    DB_IMITATION.put(m1.getId(), m1);
    DB_IMITATION.put(m2.getId(), m2);
    DB_IMITATION.put(m3.getId(), m3);
    DB_IMITATION.put(m4.getId(), m4);
    DB_IMITATION.put(m5.getId(), m5);
    DB_IMITATION.put(m6.getId(), m6);
    DB_IMITATION.put(m7.getId(), m7);
  }

  @PUT
  @Consumes(APPLICATION_XML)
  public void createModel(Model model)
  {
    model.setId(currentId);
    System.out.println("MODEL DATA: " + model.getData());
    DB_IMITATION.put(currentId++, model);
    System.out.println(DB_IMITATION.size());
  }

  @GET
  @Produces(APPLICATION_XML)
  public List<Model> retrieveAllModels()
  {
    List<Model> result = new ArrayList();
    for (Map.Entry<Integer, Model> entry : DB_IMITATION.entrySet())
    {
      result.add(entry.getValue());
    }
    return result;
  }

  @GET
  @Path("{id}")
  @Produces(APPLICATION_XML)
  public Model retrieveModel(@PathParam("id") int id)
  {
    return DB_IMITATION.get(id);
  }

  @POST
  @Path("/reflect")
  @Produces(APPLICATION_XML)
  @Consumes(APPLICATION_XML)
  public Model reflect(String xml) throws Exception
  {
    XMLInputFactory xif = XMLInputFactory.newFactory();
    xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, true);
    xif.setProperty(XMLInputFactory.SUPPORT_DTD, true);
    xif.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, true);


    XMLEventReader xmlEventReader = xif.createXMLEventReader(new StringReader(xml));

    JAXBContext jaxbContext = JAXBContext.newInstance(Model.class);
    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    Model model = (Model) unmarshaller.unmarshal(xmlEventReader);
    return model;
  }

  @DELETE
  @Path("{id}")
  public void deleteModel(@PathParam("id") int id)
  {
    DB_IMITATION.remove(id);
  }
}
