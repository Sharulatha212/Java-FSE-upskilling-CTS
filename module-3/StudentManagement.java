public class StudentManagement {
    public static void main(String[] args) {
        try {
            // Create StudentDAO instance
            StudentDAO studentDAO = new StudentDAO();

            // Insert new students
            Student student1 = new Student("Alice Cooper", 19, 88.5f);
            Student student2 = new Student("Bob Dylan", 20, 92.0f);

            System.out.println("Inserting new students...");
            int id1 = studentDAO.insertStudent(student1);
            int id2 = studentDAO.insertStudent(student2);
            System.out.println("Inserted students with IDs: " + id1 + ", " + id2);

            // Retrieve and display all students
            System.out.println("\nAll students after insertion:");
            studentDAO.getAllStudents().forEach(System.out::println);

            // Update a student
            student1.setGrade(90.0f);
            boolean updated = studentDAO.updateStudent(student1);
            System.out.println("\nUpdating student grade... Success: " + updated);

            // Display updated student
            Student updatedStudent = studentDAO.getStudentById(student1.getId());
            System.out.println("Updated student details: " + updatedStudent);

            // Delete a student
            boolean deleted = studentDAO.deleteStudent(student2.getId());
            System.out.println("\nDeleting student... Success: " + deleted);

            // Display final student list
            System.out.println("\nFinal student list:");
            studentDAO.getAllStudents().forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}