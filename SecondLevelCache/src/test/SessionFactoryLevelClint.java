package test;

import java.io.Serializable;

import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import beans.Student;

public class SessionFactoryLevelClint {

	
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("resources/Hibernate.cfg.xml");
		SessionFactory sf =  cfg.buildSessionFactory();
		Session s1 =sf.openSession();
		org.hibernate.Transaction	t =	s1.beginTransaction();
		
		Student st1= (Student)s1.get(Student.class, 111);
		System.out.println(st1.getId());
		System.out.println(st1.getAddress());
		System.out.println(st1.getName());
		
		System.out.println("trying to get same record of 111 two time by using same session 1 time by diff session");
		Student st2= (Student)s1.get(Student.class, 111);
		System.out.println(st2.getId());
		System.out.println(st2.getAddress());
		System.out.println(st2.getName());
		
		
		//means session is not same, so cache is not applied
		System.out.println("By using 2nd session ! and bang it is firing again ..Dont worry!");
		Session s2 = sf.openSession();
		Student st3 = (Student)s2.get(Student.class, 111);
		
		System.out.println(st3.getId());
		System.out.println(st3.getAddress()); 
		System.out.println(st3.getName());
		System.out.println("still it is firing query again bcz we are not using ehcache properties in xml file that is must to get SessionFactory level cache support");
		
		
	}
}
