public class Course{
    private Student[] studentList; 

    public void addStudent(Student student){
        if (studentList == null){
            studentList = new Student[]{student};
        } else { 
            Student[] newStudentList = new Student[studentList.length + 1];
            int i;
            for (i = 0; i < studentList.length; ++i){
                newStudentList[i] = studentList[i];
            }

            newStudentList[i] = student;

            studentList = newStudentList;  
        }

        for (int j = 0; j < studentList.length; j++){
            System.out.println(studentList[j].getName());
        }
    }
}