package com.bharath.flightreservation.entities;
import java.util.regex.Pattern; 

public class Test {
	
	

	

	public static void main(String[] args)
	{

	String regex = ".@.";

	Test.compare("Hacker@Earth.com", regex);
	Test.compare("a@N", regex);
	Test.compare("Java@Program", regex);

	}

	public static void compare(String str, String regex)
	{
	    if (str.matches(regex))
	    {
	        System.out.println(str + " matches");
	    }
	    else
	    {
	        System.out.println(str + " does not match");
	    }
	}
	}
	
	
	
	
	
	


