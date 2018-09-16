package com.nixsolutions.xxe_demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@NoArgsConstructor
@XmlRootElement
@Getter
@Setter
public class Note
{
  private Date date;

  private String content;
}
