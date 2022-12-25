package brs;
import java.util.Scanner;
import brs.*;
//Bus class to store all the data related to a bus
class Bus{ // storing arr class for arr obj
	String name, time;
	int g ,ID;
	int seats[][];
	String stops[];
	int dist[];
	LinkedList Psg=new LinkedList();
	Queue WL=new Queue();
	Bus(int ID,int noofstops){
		this.ID=ID;
		seats=new int[noofstops][10];
		stops=new String[noofstops];
		dist= new int[noofstops];
	}
}