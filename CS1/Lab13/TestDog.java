public class TestDog
{
  public static void main(String[] args)
  {
    Dog d1=new Dog();
    System.out.println(d1);
    
    String n="Lucy";
    Dog d2=new Dog(n);
    System.out.println(d2);
    
    String b="Golden Retreiever";
    String s="Boof";
    Dog d3=new Dog(n,6,b,s);
    System.out.println(d1.getName());
    
    System.out.println(d1.getAge());
    
    System.out.println(d1.getBreed());
    //d1.makeNoise(7);
    //d1.setSound(s);
    //d1.makeNoise(8);
    System.out.println(d1.getId());
  }
}
class Dog
{
  private int id;
  static int nextId=10000;
  private String name;
  private int age;
  private String breed;
  private String sound;
  public Dog(){
    name="Cooper";
    age=8;
    breed="Golden Doodle";
    sound="Bark";
    id=nextId;
    nextId++;
  }
  public Dog(String n){
    name=n;
    id=nextId;
    nextId++;
  }
  public Dog(String n, int a, String b, String s){
    name=n;
    age=a;
    breed=b;
    sound=s;
    id=nextId;
    nextId++;
  }
  public String toString(){
    return "Name: "+name+"   Breed: "+breed+"    Age: "+age+"    Sound: "+sound+"    ID: "+id;
  }
  public String getName(){
    return name;
  }
  public int getAge(){
    return age;
  }
  public String getBreed(){
    return breed;
  }
  public String getSound(){
    return sound;
  }
  public int getId(){
    return id;
  }
  public void setName(String n){
    name=n;
  }
  public void setAge(int a){
    age=a;
  }
  public void setBreed(String b){
    breed=b;
  }
  public void setSound(String s){
    sound=s;
  }
  public void makeNoise(int x){
    for(int i=0;i<x;i++){
      System.out.println(sound+" ");
    }
  }
}