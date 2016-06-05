package com.demo.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.demo.model.FoodGroup;
import com.demo.model.FoodGroupDAO;

public class myAppDAO {

	public static void main(String[] args) {
		
		ApplicationContext appContext = new FileSystemXmlApplicationContext("appContext.xml");
		
		FoodGroupDAO myFoodGroupDAO = appContext.getBean("foodGroupDAO", FoodGroupDAO.class);
		
//		List<FoodGroup> myFoodGroupList = myFoodGroupDAO.getFoodGroups();
		
//		for(FoodGroup fg: myFoodGroupList){
//			
//			System.out.println(fg.talkAboutYourself());
//		}
		
//		FoodGroup meat = myFoodGroupDAO.getFoodGroup(4);
//		System.out.println(meat.talkAboutYourself());
//		
//		FoodGroup dairy = myFoodGroupDAO.getFoodGroup(5);
//		System.out.println(dairy.talkAboutYourself());
		
		myFoodGroupDAO.addFoodGroup("NotRealFoodGroup", "This is not a real food group");
		
		
		((FileSystemXmlApplicationContext) appContext).close();

	}

}
