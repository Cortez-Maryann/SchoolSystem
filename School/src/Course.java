/* Title:
 Course.java
* Abstract: This class holds all the course information.
* Author: Maryann Cortez
* ID: 0001
* Date: 3/9/2018
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Course{
	
	private String name;
	private String location;
	private int courseCapacity;
	HashMap<Integer, Double> students;
	private int average;
	private int enrolled;
	int teacher;
	
	public Course(String name, int cap, String location) {
		this.name = name;
		this.location = location; 
		this.courseCapacity = cap;
		students = new HashMap<Integer, Double>();
		teacher = 0;
		enrolled = 0;
		average = 0;
	}
	
	public void updateLocation(String string) {
		this.location = string;
	}
	
	public boolean containsStudent(int i) {
		if(students.containsKey(i)) {
			return true;
		}
		return false;
	}
	
	public boolean full() {
		return (enrolled == courseCapacity);
	}
	
	public void addStudent(int num) {
			students.put(num, 0.0);
			enrolled++;
	}
	
	public boolean updateGrade(int num, double score) {
		if(students.containsKey(num)) {
			students.put(num, score);
			average += score;
			return true;
		}
		
		return false;
	}
	
	private double average() {
		double score = 0;
		for(Map.Entry entry : students.entrySet()) {
			double temp = (double) entry.getValue();
			score += temp;
		}
		return score/students.size();
	}
	
	public void asignTeacher(int num) {
		teacher = num;
	}
	
	public String toString() {
		 return name + "\n";
	 }
	
	public void remove(int num) {
		
		students.remove(num);
		enrolled--;
	}
	
	public int instructor() {
		return teacher;
	}
	
	public int enrolled() {
		return enrolled;
	}
	
	public void print() {
		System.out.println("Course Title: " + name);
		System.out.println("Room: " + location);
		System.out.println("Total Enrolled: " + enrolled);
		if(enrolled == 0) {
			System.out.println("Course Average: " + 0);
		}
		else {
			int av = average/enrolled;
			System.out.println("Course Average: " + average());
		}
		
	}

}
