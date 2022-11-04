package oop.homework1030;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class DoctoralStudent extends StudentInfo {
  private String supervisor;
  private String researchFields;

  public String getSupervisor() {
    return supervisor;
  }
  public void setSupervisor(String supervisor) {
    this.supervisor = supervisor;
  }

  public String getResearchFields() {
    return researchFields;
  }
  public void setResearchFields(String researchFields) {
    this.researchFields = researchFields;
  }

  public DoctoralStudent() { }

  public DoctoralStudent(
    String initStudentNumber,
    String initStudentName,
    Gender initGender,
    Date initBirthday,
    String initAcademy,
    String initMajor,
    String initSupervisor,
    String initResearchFields
  ) {
    super(
      initStudentNumber,
      initStudentName,
      initGender,
      initBirthday,
      initAcademy,
      initMajor
    );

    this.supervisor = initSupervisor;
    this.researchFields = initResearchFields;
  }

  private static DateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
  public static DoctoralStudent readOne(Scanner scan) throws ParseException {
    String[] args = scan.nextLine().split("_");

    if (args.length != 8) {
      throw new IllegalArgumentException("Invalid input");
    }

    Date birthday = formatter.parse(args[3]);

    return new DoctoralStudent(
      args[0],
      args[1],
      args[2].equals("男") ? Gender.Male : Gender.Female,
      birthday,
      args[4],
      args[5],
      args[6],
      args[7]
    );
  }
  
  private static DoctoralStudent parseStudent(Element ele) throws ParseException {
    DoctoralStudent res = new DoctoralStudent();

    for (int i = 0, size = ele.nodeCount(); i < size; ++ i) {
      Node node = ele.node(i);
      if (node instanceof Element) {
        switch (node.getName()) { // maybe we can simplify this ugly `switch` with reflection?
          case "studentNumber": {
            res.setStudentNumber(node.getText());
            break;
          }
          case "studentName": {
            res.setStudentName(node.getText());
            break;
          }
          case "gender": {
            res.setGender(node.getText());
            break;
          }
          case "birthday": {
            Date birthday = formatter.parse(node.getText());
            res.setBirthday(birthday);
            break;
          }
          case "academy": {
            res.setAcademy(node.getText());
            break;
          }
          case "major": {
            res.setMajor(node.getText());
            break;
          }
          case "supervisor": {
            res.setSupervisor(node.getText());
            break;
          }
          case "researchFields": {
            res.setResearchFields(node.getText());
            break;
          }
          default: {
            System.err.println("[Warn] Unknown attribute `" + node.getName() + "`!");
          }
        }
      }
    }

    return res;
  }

  public static ArrayList<DoctoralStudent> readFromFile(String path) throws DocumentException {
    SAXReader reader = new SAXReader();
    Document doc = reader.read(new File(path));
    Element root = doc.getRootElement();

    ArrayList<DoctoralStudent> res = new ArrayList<DoctoralStudent>();

    for (int i = 0, size = root.nodeCount(); i < size; ++ i) {
      Node node = root.node(i);
      if (node instanceof Element) {
        try {
          DoctoralStudent newStudent = parseStudent((Element) node);
          res.add(newStudent);
        } catch (Exception e) {
          System.err.println("[Error] Invalid doctoral student info!");
        }
      }
    }

    return res;
  }

  public Element toElement() {
    Element res = DocumentHelper.createElement("studentInfo");

    res.addElement("studentNumber") .addText(getStudentNumber());
    res.addElement("studentName")   .addText(getStudentName());
    res.addElement("gender")        .addText(getGender());
    res.addElement("birthday")      .addText(formatter.format(getBirthday()));
    res.addElement("academy")       .addText(getAcademy());
    res.addElement("major")         .addText(getMajor());
    res.addElement("supervisor")    .addText(supervisor);
    res.addElement("researchFields").addText(researchFields);

    return res;
  }

  public String toString() {
    return toElement().asXML();
  }
}
