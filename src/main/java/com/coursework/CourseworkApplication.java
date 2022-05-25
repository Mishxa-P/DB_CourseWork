package com.coursework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.sql.SQLException;
import java.text.ParseException;

@SpringBootApplication
public class CourseworkApplication
{
	public static void main(String[] args) throws SQLException, ParseException
	{
		SpringApplication.run(CourseworkApplication.class, args);
	}
}
