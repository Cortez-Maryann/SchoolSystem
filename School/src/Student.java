/* Title:
 Student.java
* Abstract: This class holds all the student's information.
* Author: Maryann Cortez
* ID: 0001
* Date: 3/9/2018
*/

import java.util.HashMap;
import java.util.Map;

public class Student {
	 private String name;
	 private int num;
	 HashMap<Integer, Double> courses;
	 private double score;
	 
	 
	 public Student(String name, int num) {
		 this.name = name;
		 this.num = num;
		 courses = new HashMap<Integer, Double>();
		 score = 0;
	 }
	 
	 public void score(int i, double score) {
		 this.score += score;
		 courses.put(i, score);
	 }
	 
	 public String name() {
		 return name;
	 }
	 public void addCourse(int number) {
		 courses.put(number, 0.0);
	 }
	 public Student(Student obj) {
		 this.name.equals(obj.name);
	 }
	 
	 public String toString() {
		 System.out.println("Student Number: " + num);
		 System.out.println("Name: " + name);
		 System.out.println("Courses Enrolled:");
		 for(Map.Entry entry : courses.entrySet()) {
				System.out.println("     " + entry.getKey() + ": " + entry.getValue());
			}
		 System.out.println("Course Average: " + (score/courses.size()));
		 return "";
	 }
}
