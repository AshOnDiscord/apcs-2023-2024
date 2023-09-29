import java.util.NoSuchElementException;

public class MyArrayList {
  public static void main(String[] args) {
    MyArrayList list = new MyArrayList();
    list.push("Goodbye");
    list.push("World");
    list.push("!");
    list.print();
    list.unshift("World");
    list.unshift("Hello");
    list.print();
    System.out.println(list.find("World"));
    System.out.println(list.find("Goodbye"));
    System.out.println(list.find("Hello"));
    System.out.println(list.find("World!"));
    list.remove("Goodbye");
    list.print();
  }

  private Object[] objects;

  public Object get(int index) {
    if (index > this.filled() - 1) {
      throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
    }
    return objects[index];
  }

  public void set(int index, Object obj) {
    if (index > this.filled() - 1) {
      throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
    }
    objects[index] = obj;
  }

  public void push(Object obj) {
    int fulled = this.filled();
    if (this.objects == null || fulled == objects.length) {
      this.increase();
    }
    this.objects[fulled] = obj;
  }

  public void unshift(Object obj) {
    int fulled = this.filled();
    if (fulled == objects.length) {
      this.increase();
    }
    for (int i = fulled; i > 0; i--) {
      this.objects[i] = this.objects[i - 1];
    }
    this.objects[0] = obj;
  }

  public void remove(Object obj) {
    int index = this.find(obj);
    if (index == -1) {
      throw new NoSuchElementException("Element " + obj + " does not exist!");
      // just use a try catch, more convenient for devs than a sout
    }
    this.remove(index);
  }

  private void remove(int index) {
    for (int i = index; i < this.filled() - 1; i++) {
      this.objects[i] = this.objects[i + 1];
    }
    this.objects[this.filled() - 1] = null;
    if (this.objects.length > this.filled() * 2) {
      this.shrink();
    }
  }

  private int filled() {
    if (this.objects == null) {
      return 0;
    }
    int size = 0;
    for (int i = 0; i < this.objects.length; i++) {
      if (this.objects[i] != null) {
        size = i;
      }
    }
    return size + 1;
  }

  public int getPhysicalSize() {
    return this.objects.length;
  }

  public int getLogicalSize() {
    return this.filled();
  }

  private void increase() {
    if (this.objects == null) {
      this.objects = new Object[1];
      return;
    }
    Object[] temp = new Object[this.objects.length * 2];
    for (int i = 0; i < this.objects.length; i++) {
      temp[i] = this.objects[i];
    }
    this.objects = temp;
  }

  private void shrink() {
    // save some memory
    Object[] temp = new Object[this.filled()];
    for (int i = 0; i < temp.length; i++) {
      temp[i] = this.objects[i];
    }
  }

  public void print() {
    System.out.print("[");
    for (int i = 0; i < this.filled(); i++) {
      System.out.print(this.objects[i]);
      if (i != this.filled() - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  public int find(Object object) {
    for (int i = 0; i < this.filled(); i++) {
      if (this.objects[i].equals(object)) {
        return i;
      }
    }
    return -1;
  }
}