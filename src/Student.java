public class Student {
    private int studentId;
    private String name;
    private String email;
    private String phone;
    private double marks;

 //Default constructor
    public Student(){

    }
 //parameterized constructor
    public Student(String name, String email, String phone, double marks)
    {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.marks = marks;
    }

    //Getter
    public int getStudentID(){
        return studentId;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }
    public double getMarks(){
        return marks;
    }

    //setter
    public void setStudentId(int studentId){
        this.studentId = studentId;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public  void  setMarks(double marks){
        this.marks = marks;
    }

    
}
