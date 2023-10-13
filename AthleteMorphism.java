public class AthleteMorphism {
  public static void main(String[] args) {
    Athlete a;
    System.out.print("Soccer or Baseball? ");
    String input = System.console().readLine().toLowerCase();
    if (input.equals("soccer")) {
      a = new SoccerPlayer();
    } else if (input.equals("baseball")) {
      a = new BaseBallPlayer();
    } else {
      System.out.println("Invalid input");
      return;
    }
    a.calcConditioningLevel();
    System.out.println(a.getCondLevel());
  }
}

abstract class Athlete extends Object {
  private double condLevel;
  private String name;

  public abstract void calcConditioningLevel();

  public void setCondLevel(double c) {
    condLevel = c;
  }

  public double getCondLevel() {
    return condLevel;
  }
}

class SoccerPlayer extends Athlete {
  public void calcConditioningLevel() {
    double cl = 34.5;

    setCondLevel(cl);
  }
}

class BaseBallPlayer extends Athlete {
  public void calcConditioningLevel() {
    double cl = 92.1;

    setCondLevel(cl);

  }
}