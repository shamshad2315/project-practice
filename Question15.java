 class Students {

    private String name;
    private int rollNumber;
    private  double marks ;
    private int age;

    // constructor
     Students (int rollNumber,double marks,int age){
         this.rollNumber=rollNumber;
         this.marks=marks;
         this.age=age;

     }
     void display () {
         System.out.println("roll number: " + rollNumber);
         System.out.println("marks: " + marks);
         System.out.println("age: " + age);
     }
 }
 public class Question15 {
    public static void main(String[] args) {
        Students students=new Students(100,12,18);
        students.display();
        
    }
 }