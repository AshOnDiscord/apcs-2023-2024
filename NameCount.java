import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NameCount {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(new File("./names.txt")));
      String line = null;
      ArrayList<Name> names = new ArrayList<Name>();
      outer: while ((line = br.readLine()) != null) {
        for (Name n : names) {
          if (n.getName().equals(line)) {
            n.increment();
            continue outer;
          }
        }
        names.add(new Name(line));
      }
      for (Name name : names) {
        // if (name.getCount() > 1) {
        System.out.println(name.getName() + " " + name.getCount());
        // }
      }
      System.out.print("\nEnter a name to search for: ");
      String input = System.console().readLine();
      for (Name name : names) {
      // for (int i = 0; i < names.size(); i++) {
      //   Name name = names.get(i);
        if (name.getName().equals(input)) {
          System.out.println(name.getCount());
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

class Name {
  private String name;
  private int count;

  public Name(String name) {
    this.name = name;
    this.count = 1;
  }

  public void increment() {
    this.count++;
  }

  public String getName() {
    return this.name;
  }

  public int getCount() {
    return this.count;
  }
}