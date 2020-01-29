/* Title:
 Instructor.java
* Abstract: This class holds all the Instructor's information.
* Author: Maryann Cortez
* ID: 0001
* Date: 3/9/2018
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Instructor{
	private String name;
	private String email;
	private String phoneNumber;
	private int num;
	HashMap<Integer, Integer> courses;
	
	public Instructor(String name, String email,  String phoneNumber, int num) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.num = num;
		courses = new HashMap<Integer, Integer>();
	}
	
	public String getEmail() {
		return email;
	}
	
	public void addCourse(int number) {
		courses.put(number, 0);
	}
	public void updateStudents(int number, int students) {
		courses.put(number, students);
	}
	
 	public String toString() {
		System.out.println("Instructor Number: " + num);
		System.out.println("Instructor: " + name);
		System.out.println("Courses Teaching:");
		for(Map.Entry entry : courses.entrySet()) {
			System.out.println("     " + entry.getKey() + ": " + entry.getValue() + " enrolled");
		}
		return "";
	}
	 
	public boolean equals(Instructor object)
	{
		if(object.name.equals(this.name) && object.email.equals(this.email) && object.phoneNumber.equals(this.phoneNumber)) {
			return true;
		}
		return false;
	}
	
	public String name() {
		return this.name + "\n";
	}
	
	public void instructorInfo() {
		System.out.println("      Name: " + name);
		System.out.println("      Phone: " + phoneNumber);
	}
	
	public void removeCourse(int x) {
		if(courses.containsKey(x)) {
			courses.remove(x);
		}
		
	}
	
	public Instructor instructor() {
		return this;
	}
}
  