import java.util.*;
class AndrewM
{
  //test class
  public static void main(String[] args)throws MyException{
    //Name class comprehensive tests
    System.out.println("------------------------------------------------------");
    Name me=new Name("AndRew","r","McDonald");
    Name me2=new Name();
    System.out.println(me);
    System.out.println();
    System.out.println(me.equals(me2));
    System.out.println();
    me.setFirstName("Rachel");
    me.setMI("b");
    me.setLastName("Cosgrove");
    System.out.println(me);
    System.out.println("------------------------------------------------------");
    
    
    //User Class comprehensive tests
    User you=new User();
    User you1=new User(me);
    User you2=new User(me2, "123PAssword");//Change password to test checkPassword and confrimPassword methods
    System.out.println(you);
    System.out.println();
    System.out.println(you2.changePassword("123PAssword","12Password","12Password"));
    System.out.println();
    System.out.println(you1);
    System.out.println();
    you.setName(me2);
    you.setId(20000);
    System.out.println(you);
    System.out.println("------------------------------------------------------");
    
    
    //Student Class comprehensive tests
    Student test=new Student(me,"12Password");
    Student test2=new Student(me,"12Password");
    double[] yah={12.2,14.2,13.2};
    System.out.println(test.computeGpa(yah));
    System.out.println();
    System.out.println(test.equals(test2));
    System.out.println();
    System.out.println(test);
    System.out.println("------------------------------------------------------");
    
    
    //Faculty Class comprehensive tests
    Faculty woah=new Faculty(me,"123Passowrd","dEaN");
    Faculty woah2=new Faculty(me,"123Passowrd","DeAn");
    System.out.println(woah.equals(woah2));
    System.out.println();
    System.out.println(woah);
    System.out.println();
    System.out.println(woah.generateEmail());
    System.out.println();
    woah.setPosition("ProFessOr");
    System.out.println(woah);
    System.out.println("------------------------------------------------------");
    
    
    //Graduate Class comprehensive tests
    Graduate hah=new Graduate(me,"12Password");
    Graduate hah2=new Graduate(me,"12Password");
    System.out.println(hah.equals(hah2));
    System.out.println();
    System.out.println(hah);
    System.out.println();
    System.out.println(hah.getAttempts());
    System.out.println();
    System.out.println(hah.updateExamGrade(70.0));
    System.out.println();
    System.out.println(hah.getAttempts());
    System.out.println();
    System.out.println(hah.updateExamGrade(90.0));
    System.out.println();
    hah2.updateExamGrade(70.0);
    hah2.updateExamGrade(75.0);
    System.out.println(hah2.updateExamGrade(79.0));
    System.out.println("------------------------------------------------------");
    
    
    //UnderGraduate Class comprehensive tests
    UnderGraduate what=new UnderGraduate(me,"12Password","jUNIOr");
    UnderGraduate what1=new UnderGraduate(me,"12Password","JunioR");
    System.out.println(what.equals(what1));
    System.out.println();
    System.out.println(what);
    System.out.println();
    what.setYear("fResHman");
    System.out.println(what);
    System.out.print("------------------------------------------------------");
  }
}



class MyException extends Exception
{
  //Constructors
  public MyException(){
    super("Something went wrong.");
  }
  public MyException(String s) { 
    super(s); 
  }
}



class Name
{
  //Attributes
  private String firstName;
  private String mI;
  private String lastName;
  
  //Construtors
  public Name(){
    firstName="Andrew";
    mI="R";
    lastName="Mcdonald";
  }
  public Name(String f,String m,String l)throws MyException{
    //checks for any exceptions
    isBadName(f);
    isBadName(m);
    isBadName(l);
    isTooLong(m);
    firstName=f.substring(0,1).toUpperCase()+f.substring(1,f.length()).toLowerCase();
    mI=m.toUpperCase();
    lastName=l.substring(0,1).toUpperCase()+l.substring(1,l.length()).toLowerCase();
  }
  //Methods
  public String toString(){
    return lastName+","+firstName+","+mI;
  }
  public boolean equals(Name n){
    if(firstName.equals(n.getFirstName())){
      if(mI.equals(n.getMI())){
        if(lastName.equals(n.getLastName())){
          return true;
        }
      }
    }
    return false;
  }
  //This checks to make sure all attributes are only letters
  public boolean isLetters(String s){
    char[] c=s.toCharArray();
    for(int i=0;i<c.length;i++){
      if(!Character.isLetter(c[i])){
        return false;
      }
    }
    return true;
  }
  //Checks for only letters in input strings and makes an exception if their are other characters
  public void isBadName(String s)throws MyException{
    if(isLetters(s)==false){
      throw new MyException("Incorrect Name");
    }
  }
  //Makes sure intials are only 1 length
  public void isTooLong(String s)throws MyException{
    if(s.length()>1||s.length()<1){
      throw new MyException("Intial is too Long");
    }
  }
  
  //get methods
  public String getFirstName(){
    return firstName;
  }
  public String getMI(){
    return mI;
  }
  public String getLastName(){
    return lastName;
  }
  
  //set  methods
  public void setFirstName(String s)throws MyException{
    isBadName(s);
    firstName=s;
  }
  public void setMI(String s)throws MyException{
    isTooLong(s);
    isBadName(s);
    mI=s;
  }
  public void setLastName(String s)throws MyException{
    isBadName(s);
    lastName=s;
  }
}



class User
{
  //Attributes
  private Name name;
  private long id;
  static long nextId=10000;
  private String password;
  
  //Constructors
  public User(){
    Name n=new Name();
    name=n;
    password="12Password";
    id=nextId;
    nextId++;
  }
  public User(Name n){
    name=n;
    password="12Password";
    id=nextId;
    nextId++;
  }
  public User(Name n,String p){
    name=n;
    if(checkPassword(p)){
      password=p;
    }
    id=nextId;
    nextId++;
  }
  
  //Methods
  public boolean equals(User u){
    if(name.equals(u.getName())){
      //Deccided to remove checking for Id because that is soemthing the program applies that 
      //ignores if every other single attribute is the same.
      //if(id==u.getId()){
        return true;
     // }
    }
    return false;
  }
  public String toString(){
    return "User id: "+id+"\nName: "+name;
  }
  //ONLY checks for if an inputted string meets the password requirements(used in check and changePassword)
  private boolean confirmPassword(String s){
    int numberCount=0;
    boolean lengthCheck=false;
    boolean upperCheck=false;
    if(s.length()>=8){
      lengthCheck=true;
      char[] c=s.toCharArray();
      for(int i=0;i<c.length;i++){
        if(Character.isDigit(c[i])){
          numberCount++;
        }else if(Character.isUpperCase(c[i])){
          upperCheck=true;
        }
      }
    }
    if(numberCount>=2&&lengthCheck==true&&upperCheck==true){
      return true;
    }else{
      return false;
    }
  }
  //constantly rechecks a given string until a valid password is inputted
  public boolean checkPassword(String s){
    boolean passCheck=false;
    Scanner kb=new Scanner(System.in);
    while(passCheck==false){
      if(confirmPassword(s)){
        passCheck=true;
      }else{
        System.out.println("Password does not meet requirements.");
        System.out.print("Enter a new one: ");
        s=kb.next();
      }
    }
    return true;
  }
  public boolean changePassword(String oldPass, String newPass, String newPass2){
    if(oldPass.equals(password)){
      if(newPass.equals(newPass2)){
        if(confirmPassword(newPass)){
          setPassword(newPass);
          return true;
        }
      }
    }
    return false;
  }
  
  //get Methods
  public Name getName(){
    return name;
  }
  public long getId(){
    return id;
  }
  private String getPassword(){
    return password;
  }
  
  //set methods
  public void setName(Name n){
    name=n;
  }
  public void setId(long i){
    id=i;
  }
  private void setPassword(String p){
    password=p;
  }
}



class Student extends User
{
  //constructor
  public Student(Name n,String p){
    super(n,p);
  }
  
  //methods
  public double computeGpa(double[] grades){
    double sum=0;
    for(int i=0;i<grades.length;i++){
      sum+=grades[i];
    }
    return sum/grades.length;
  }
  public String toString(){
    return super.toString();
  }
  public boolean equals(Student s){
    if(super.equals(s)){
      return true;
    }
    return false;
  }
}



class Faculty extends User
{
  //Attributes
  private int position;
  
  //Constructor
  public Faculty(Name n,String p, String pos){
    super(n,p);
    pos=pos.toLowerCase();
    position=convertPosition(pos);
  }
  
  //Methods
  public String toString(){
    return super.toString() + "\nPosition: "+position+"\nEmail: "+generateEmail();
  }
  public boolean equals(Faculty f){
    if(super.equals(f)){
      if(position==f.getPosition()){
        return true;
      }
    }
    return false;
  }
  public int convertPosition(String p){
    if(p.equals("dean")){
      return 0;
    }else if(p.equals("chair")){
      return 1;
    }else if(p.equals("professor")){
      return 2;
    }else if(p.equals("secretary")){
      return 3;
    }else if(p.equals("teaching assistand")){
      return 4;
    }else{
      return -1;
    }
  }
  public String generateEmail(){
    if(super.getName().getLastName().length()>=6){
      return super.getName().getLastName().substring(0,7).toLowerCase()+
        super.getName().getFirstName().substring(0,1).toLowerCase()+"@newpaltz.edu";
    }
    return super.getName().getLastName().toLowerCase()+
        super.getName().getFirstName().substring(0,1).toLowerCase()+"@newpaltz.edu";
  }
  
  //set method
  public void setPosition(String p){
    p=p.toLowerCase();
    position=convertPosition(p);
  }
  
  //get method
  public int getPosition(){
    return position;
  }
}



class Graduate extends Student
{
  //Attributes
  private double examGrade;
  private int attempts;
  private boolean ableToGraduate;
  
  //Constructor
  public Graduate(Name n,String p){
    super(n,p);
    examGrade=0;
    attempts=0;
    ableToGraduate=false;
  }
  
  //Methods
  public String toString(){
    return super.toString()+"\nExam grade: "+examGrade+" with "+attempts+" attempts";
  }
  public boolean equals(Graduate g){
    if(super.equals(g)){
      if(examGrade==g.getExamGrade()){
        if(attempts==g.getAttempts()){
          if(ableToGraduate==g.getAbleToGraduate()){
            return true;
          }
        }
      }
    }
    return false;
  }
  //
  public String updateExamGrade(double g){
    if(g>examGrade){
      examGrade=g;
    }
    attempts++;
    calculateAbleToGraduate();
    if(ableToGraduate==true){
      return "Student is able to graduate.";
    }else if(attempts>2){
      //Only done if the number of attempts is exhausted
      return "Student can never graduate.";
    }else{
      return "Student is unable to graduate.";
    }
  }
  //Made this specifically to only make ableToGraduate true so trying to get a 
  //better test grade while exhusting the attempts does not set the value to false
  public void calculateAbleToGraduate(){
    if(examGrade>=80.0 && attempts<=2){
      ableToGraduate=true;
    }
  }
  
  //set methods
  private void setExamGrade(double g){
    examGrade=g;
  }
  private void setAttempts(int x){
    attempts=x;
  }
  private void setAbleToGraduate(boolean b){
    ableToGraduate=b;
  }
  
  //get methods
  public double getExamGrade(){
    return examGrade;
  }
  public int getAttempts(){
    return attempts;
  }
  public boolean getAbleToGraduate(){
    return ableToGraduate;
  }
}



class UnderGraduate extends Student
{
  //Attribute
  private String year;
  
  //Constructor
  public UnderGraduate(Name n, String p, String y){
    super(n,p);
    y=y.toLowerCase();
    year=checkYear(y);
  }
  
  //Methods
  public String toString(){
    return super.toString()+"\nYear: "+year;
  }
  public boolean equals(UnderGraduate u){
    if(super.equals(u)){
      if(year.equals(u.getYear())){
        return true;
      }
    }
    return false;
  }
  public String checkYear(String y){
    if(y.equals("freshman")){
      return y;
    }else if(y.equals("sophomore")){
      return y;
    }else if(y.equals("junior")){
      return y;
    }else if(y.equals("senior")){
      return y;
    }else{
      return "unknown";
    }
  }
  
  //set method
  public void setYear(String y){
    y=y.toLowerCase();
    year=checkYear(y);
  }
  
  //get method
  public String getYear(){
    return year;
  }
}