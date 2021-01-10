package edu.uprm.cse.datastructures.cardealer.model;

import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;

public class CarList {
	
  private static CircularSortedDoublyLinkedList<Car> cList = new CircularSortedDoublyLinkedList <Car>(new CarComparator());

  private CarList(){}
  
  //returns the instance of the list
  public static CircularSortedDoublyLinkedList<Car> getInstance(){
    return cList;
  }
  
  //creates a new instance of the list
  public static void resetCars() {
	  cList = new CircularSortedDoublyLinkedList <Car>(new CarComparator());
  }
  
}                       