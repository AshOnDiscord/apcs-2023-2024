package AdvancedClassesHW;

class Athlete {
  private int age;
  private String name;
  private String yearInSchool;

  public Athlete(int age, String name, String yearInSchool) {
    this.age = age;
    this.name = name;
    this.yearInSchool = yearInSchool;
  }

  public Athlete() {
    this(15, "SallyJoeBob", "Sophmore");
  }

  protected int getAge() {
    return age;
  }

  @Override
  public String toString() {
    return "Athlete [age=" + age + ", name=" + name + ", yearInSchool=" + yearInSchool + "]";
  }
}

class Runner extends Athlete implements Comparable<Runner> {

  // runner type can be a sprinter, hurdler or miler
  private String typeOfRunner;

  // runnerLevel (1-10) is the evaluation of a runners ability, 1 lowest 10
  // highest
  private int runnerLevel;

  public Runner(int age, String name, String yearInSchool, String typeOfRunner, int runnerLevel) {
    super(age, name, yearInSchool);
    if (!typeOfRunner.equalsIgnoreCase("sprinter") && !typeOfRunner.equalsIgnoreCase("hurdler")
        && !typeOfRunner.equalsIgnoreCase("miler")) {
      throw new IllegalArgumentException("Runner type must be sprinter, hurdler or miler");
    }

    if (runnerLevel < 1 || runnerLevel > 10) {
      throw new IllegalArgumentException("Runner level must be between 1 and 10");
    }
    this.typeOfRunner = typeOfRunner;
    this.runnerLevel = runnerLevel;
  }

  @Override
  public int compareTo(Runner other) {
    return this.runnerLevel - other.runnerLevel;
  }

  @Override
  public String toString() {
    String parent = super.toString();
    return parent.substring(0, parent.length() - 1) + ", typeOfRunner=" + typeOfRunner + ", runnerLevel="
        + runnerLevel + "]";
  }
}
