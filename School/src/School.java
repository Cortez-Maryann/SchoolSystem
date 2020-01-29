/* Title:
 School.java
* Abstract: This class holds all the school's information.
* Author: Maryann Cortez
* ID: 0001
* Date: 3/9/2018
*/

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class School {
	HashMap<Integer, Instructor> instructors;
	HashMap<Integer, Course> courses;
	HashMap<Integer, Student> students;
	private String schoolName;
	
	public School(String string) {
		this.schoolName = string;
		instructors = new HashMap<Integer, Instructor>();
		courses = new HashMap<Integer, Course>();
		students = new HashMap<Integer, Student>();
	}

	public void readData(String textFile) throws IOException {
		FileReader file = new FileReader(textFile);
		BufferedReader bufferReader = new BufferedReader(file);
		String line = null;
		ArrayList<String> data = new ArrayList<String>();
		while((line = bufferReader.readLine()) != null) {
			data.add(line);
		}
		bufferReader.close();
		
		//Parser below
		
		int ii = 0;
		for(int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(data.get(ii));
			String y = st.nextToken();
			int x = Integer.parseInt(y);
			ii++;
		     for(int l = x; l > 0; l--) {
		    	 if(i == 0) {
		    		 StringTokenizer stkn = new StringTokenizer(data.get(ii));
		    		 String p = stkn.nextToken(",");
		    		 int pin = Integer.parseInt(p);
		    		 String name = stkn.nextToken(",");
		    		 String email = stkn.nextToken(",");
		    		 String pn = stkn.nextToken(",");
		    		 
		    		 if(instructors.containsKey(pin)) {
		    				System.out.println("Instructor info reading failed – Employee number " + pin + " already used.");
		    			}
		    		 else {
		    			 Instructor ins = new Instructor(name, email, pn, pin);
		    			 instructors.put(pin, ins);
		    		 }
		    		 
		    		 ii++;
		    	 }
		    	 if(i == 1) {
		    		 StringTokenizer stkn = new StringTokenizer(data.get(ii));
		    		 String p = stkn.nextToken(",");
		    		 int pin = Integer.parseInt(p);
		    		 String name = stkn.nextToken(",");
		    		 String c = stkn.nextToken(",");
		    		 int cap = Integer.parseInt(c);
		    		 String loc = stkn.nextToken(",");
		    		 
		    		 Course course = new Course(name, cap, loc);
		    		 courses.put(pin, course);
		    		 ii++;
		    	 }
				 if(i == 2) {
					 StringTokenizer stkn = new StringTokenizer(data.get(ii));
					 String p = stkn.nextToken(",");
		    		 int pin = Integer.parseInt(p);
		    		 String name = stkn.nextToken(",");
		    		 
		    		 if(students.containsKey(pin)) {
		    				System.out.println("Student info reading failed – Student ID " + pin + " already used.");
		    			}
		    		 else {
		    			 Student student = new Student(name, pin);
		    			 students.put(pin, student);
		    		 }
		    		 
					 ii++;
				}
		     } 
		}
		System.out.println("Done.");
	}
	
	public void schoolInfo() {
		System.out.println("School Name: " + schoolName);
		System.out.println("Instructor Information");
		for(Map.Entry entry : instructors.entrySet()) {
			System.out.print("      " + ((Instructor) entry.getValue()).name());
		}

		System.out.println("Course Information");
		for(Map.Entry entry : courses.entrySet()) {
			System.out.print("      " + entry.getValue());
		}
		
		System.out.println("Student Information");
		for(Map.Entry entry : students.entrySet()) {
			System.out.println("      " + ((Student) entry.getValue()).name());
		}
	}

	public boolean searchByEmail(String string) {
		for(Map.Entry entry : instructors.entrySet()) {
			Instructor ins = (Instructor) entry.getValue();

			if(ins.getEmail().equals(string)) {
				System.out.println("Search key: " + string);
				System.out.println("      Employee Number: " + entry.getKey());
				ins.instructorInfo();
				return true;
				
			}
			
		}
			System.out.println("Search key: " + string);
			System.out.println("      No employee with email " + string);
			return false;
		
	}

	public boolean addInstructor(int pin, String string, String string2, String string3) {
		if(instructors.containsKey(pin)) {
			return false;
		}
		Instructor newInstructor = new Instructor(string, string2, string3, pin);
		instructors.put(pin, newInstructor);
		return true;
	
	}

	public boolean addCourse(int i, String string, int j, String string2) {
		if(courses.containsKey(i)) {
			System.out.println("Course addition failed – Course number 306 already used.");
			return false;
		}
		
		Course newCourse = new Course(string, j, string2);
		courses.put(i, newCourse);
		return true;
		
	}

	public void assignInstructor(int i, int j) {
		if(courses.containsKey(i)) {
			if(instructors.containsKey(j)) {
				courses.get(i).asignTeacher(j);
				instructors.get(j).addCourse(i);
			}
			else {
				System.out.println("Instructor " + j + " does not exist.");
			}
		}
		else {
			System.out.println("Course " + i + " does not exist.");
		}
	}

	public void register(int i, int j) {
		// register student in course
		if(courses.get(i).full()) {
			System.out.println("Registration failed – Class is full.");
		}
		else if(students.containsKey(j)) {
			courses.get(i).addStudent(j);
			students.get(j).addCourse(i);
			int x = courses.get(i).instructor();
			int y = courses.get(i).enrolled();
			instructors.get(x).updateStudents(i,y);
		}
		
		else {
			System.out.println("Student " + j + " does not exist.");
		}
		
	}

	public void putScore(int i, int j, double d) {
		// put student score
		if(!(courses.get(i).updateGrade(j, d))) {
			System.out.println("Student " + j + " (" + students.get(j) + ") is not enrolled in " + i + ".");
		}
		students.get(j).score(i,d);
	}

	public void unRegister(int i, int j) {
		// unregister student from course
		courses.get(i).remove(j);
		int x = courses.get(i).instructor();
		int y = courses.get(i).enrolled();
		instructors.get(x).updateStudents(i,y);
	}

	public void courseInfo(int i) {
		// print course info
		System.out.println("Course Number: " + i);
		int teach = courses.get(i).instructor();
		System.out.print("Instructor: " + instructors.get(teach).name());
		courses.get(i).print();
	}

	public Course getCourse(int i) {
		// return course
		return courses.get(i);
	}
  
	public void courseInfo() {
		// print course info
		System.out.println("Number of Courses: " + courses.size());
		for(Map.Entry entry : courses.entrySet()) {
			System.out.println(entry.getKey() + ": " + ((Course) entry.getValue()).enrolled()+ " enrolled");
		}
		
		System.out.println("");
	}

	public boolean deleteCourse(int i) {
		// delete course
		if(courses.get(i).enrolled() > 0) {
			System.out.print("Course deletion failed – Enrolled student(s) in the class");
			return false;
		}
		int x = courses.get(i).instructor();
		if(x != 0) {
			instructors.get(x).removeCourse(i);
		}
		courses.remove(i);
		return true;
	}

	public void addStudent(int i, String string) {
		// add student
		Student student = new Student(string, i);
		students.put(i, student);
		
	}

	public Instructor getInstructor(int i) {
		// return instructor
			int x = courses.get(i).instructor();
			return instructors.get(x).instructor();
		}
		
	public Student getStudent(int i) {
		// return student
		return students.get(i);
	}
	
	public void graduateStudent(int i) {
		// drop student from classes
		for(Map.Entry entry : courses.entrySet()) {
			if(((Course) entry.getValue()).containsStudent(i)) {
				  ((Course) entry.getValue()).remove(i);
			}
		}
		
		students.remove(i);
	}
	
}
