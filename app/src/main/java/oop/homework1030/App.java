/*
 * This Java source file was generated by the Gradle 'init' task.
 *
 * TODO: move some of static functions to other classes
 */
package oop.homework1030;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson2.JSONArray;

public class App {
  private static InfoPublisher<ArrayList<StudentInfo>> publisher;
  private static ArrayList<StudentInfo> students;

  private static void init() {
    students = new ArrayList<StudentInfo>();

    try {
      students.addAll(UndergraduateStudent.readFromFile("us.txt"));
    } catch (Exception e) {
      System.err.println("[Error] Read undergraduate students failed!");
    }

    try {
      students.addAll(GraduateStudent.readFromFile("gs.json"));
    } catch (Exception e) {
      System.err.println("[Error] Read graduate students failed!");
    }

    try {
      students.addAll(DoctoralStudent.readFromFile("ds.xml"));
    } catch (Exception e) {
      System.err.println("[Error] Read doctoral students failed!");
    }


    publisher = new InfoPublisher<ArrayList<StudentInfo>>();

    publisher.subscribe("us", (list) -> {
      List<UndergraduateStudent> undergraduateStudents = list.stream()
        .filter(student -> student instanceof UndergraduateStudent)
        .map(student -> (UndergraduateStudent) student)
        .toList();

      try {
        FileWriter out = new FileWriter("us.txt");
        undergraduateStudents.forEach(student -> {
          try {
            out.write(student.toString() + "\n");
          } catch (Exception e) {
            System.err.println("[Error] Error occured when writing undergraduate student info!");
          }
        });
        out.close();
      } catch (Exception e) {
        System.err.println("[Error] Write undergraduate students failed!");
      }
    });

    publisher.subscribe("gs", (list) -> {
      List<GraduateStudent> graduateStudents = list.stream()
        .filter(student -> student instanceof GraduateStudent)
        .map(student -> (GraduateStudent) student)
        .toList();

      JSONArray res = new JSONArray(graduateStudents);
      try {
        FileWriter out = new FileWriter("gs.json");
        out.write(res.toJSONString());
        out.close();
      } catch (Exception e) {
        System.err.println("[Error] Write graduate students failed!");
      }
    });

    publisher.subscribe("ds", (list) -> {
      Document doc = DocumentHelper.createDocument();

      Element root = doc.addElement("doctoralStudents");

      list.forEach(student -> {
        if (student instanceof DoctoralStudent) {
          root.add(((DoctoralStudent) student).toElement());
        }
      });

      try {
        FileWriter out = new FileWriter("ds.xml");
        doc.write(out);
        out.close();
      } catch (Exception e) {
        System.err.println("[Error] Write doctoral students failed!");
      }
    });
  }

  private static String greeting =
    "[A] ????????????????????????\n" +
    "[B] ?????????????????????\n" +
    "[C] ?????????????????????\n" +
    "[D] ?????????????????????\n" +
    "[E] ?????????????????????\n" +
    "[F] ?????????????????????\n" +
    "[G] ????????????\n" +
    "[Z] ????????????\n";

  private static String prompt = ">>> ";

  private static void run() {
    System.out.println(greeting);

    Scanner scan = new Scanner(System.in);

    while (true) {
      System.out.print(prompt);
      System.out.flush();

      String input = scan.nextLine();

      switch (input) {
        case "A": {
          printStudents(students);
          break;
        }
        case "B": {
          addUndergraduateStudent(scan);
          break;
        }
        case "C": {
          addGraduateStudent(scan);
          break;
        }
        case "D": {
          addDoctoralStudent(scan);
          break;
        }
        case "E": {
          String id = scan.nextLine();
          findStudentById(id);
          break;
        }
        case "F": {
          String name = scan.nextLine();
          findStudentByName(name);
          break;
        }
        case "G": {
          sortStudents();
          break;
        }
        case "Z": {
          scan.close();
          return;
        }
        default: {
          System.err.println("[Error] Unknown command!");
        }
      }
    }
  }

  private static void printStudents(ArrayList<StudentInfo> students) {
    students.forEach(student -> System.out.println(student));
  }

  private static void addUndergraduateStudent(Scanner scan) {
    try {
      students.add(UndergraduateStudent.readOne(scan));
      publisher.publish("us", students);
    } catch (Exception e) {
      System.err.println("[Error] Invalid undergraduate student info!");
    }
  }

  private static void addGraduateStudent(Scanner scan) {
    try {
      students.add(GraduateStudent.readOne(scan));
      publisher.publish("gs", students);
    } catch (Exception e) {
      System.err.println("[Error] Invalid graduate student info!");
    }
  }

  private static void addDoctoralStudent(Scanner scan) {
    try {
      students.add(DoctoralStudent.readOne(scan));
      publisher.publish("ds", students);
    } catch (Exception e) {
      System.err.println("[Error] Invalid doctoral student info!");
    }
  }

  private static void findStudentById(String id) {
    students.forEach(student -> {
      if (student.getStudentNumber().equals(id)) {
        System.out.println(student);
      }
    });
  }

  private static void findStudentByName(String name) {
    students.forEach(student -> {
      if (student.getStudentName().equals(name)) {
        System.out.println(student);
      }
    });
  }

  private static void sortStudents() {
    ArrayList<StudentInfo> tempStudents = new ArrayList<StudentInfo>();
    students.forEach(student -> tempStudents.add(student));

    tempStudents.sort((lhs, rhs) -> {
      if (lhs.getClass() == rhs.getClass()) {
        return rhs.getBirthday().compareTo(lhs.getBirthday());
      }

      if (lhs.getClass() == UndergraduateStudent.class) {
        return -1;
      }

      if (lhs.getClass() == DoctoralStudent.class) {
        return 1;
      }

      if (rhs.getClass() == UndergraduateStudent.class) {
        return 1;
      }

      if (rhs.getClass() == DoctoralStudent.class) {
        return -1;
      }

      // unreachable
      return 0;
    });

    printStudents(tempStudents);
  }

  public static void main(String[] args) {
    init();
    run();
  }
}
