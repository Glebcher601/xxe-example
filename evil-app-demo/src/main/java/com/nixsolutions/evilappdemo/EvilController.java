package com.nixsolutions.evilappdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.io.IOUtils.readLines;

@Controller
@RequestMapping(path = "/collect")
public class EvilController
{
  private static final Logger LOG = LoggerFactory.getLogger(EvilController.class);


  @GetMapping
  public @ResponseBody
  String collect(@RequestParam String input) throws IOException
  {
    LOG.debug("RECEIVED INPUT: " + input);
    return input;
  }

  @PostMapping
  public
  @ResponseBody String collectPost(@RequestBody String input) throws IOException
  {
    LOG.debug("RECEIVED INPUT: " + input);
    return input;
  }

  @GetMapping(path = "/dtd/evil.dtd")
  public @ResponseBody String getDtd() throws IOException
  {
    LOG.debug("Requesting malicious DTD!");
    return readDtd("static/dtd/evil.dtd");
  }

  @GetMapping(path = "/dtd/evil2.dtd")
  public @ResponseBody String getDtd2() throws IOException
  {
    LOG.debug("Requesting malicious DTD2!");
    return readDtd("static/dtd/evil2.dtd");
  }

  private String readDtd(String name) throws IOException
  {
    List<String> dtd = readLines(getClass().getClassLoader().getResourceAsStream(name), "UTF-8");
    return dtd.stream().collect(joining("\n"));
  }
}
