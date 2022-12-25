package brs;
import brs.*;
//Queue
class Queue{
	Node tail=null;
	int length=0;
	Node front=null;
	void enqueue(PassengerDetails p) {
		Node temp=new Node(p);
		if(tail==null) {
			tail=temp;
			length=1;
			front=temp;
		}
		else {
			length++;
			tail.next=temp;
			tail=tail.next;
		}
	}
	void dequeue() {
		if(!isEmpty()) {
			front=front.next;
		}
	}
	boolean isEmpty() {
		if(front==null) {
			return true;
		}
		return false;
	}
	PassengerDetails search(int prn) {
		Node ptr=front;
		while(ptr!=null) {
			if(ptr.p.PNR==prn) {
				return ptr.p;
			}
			ptr=ptr.next;
		}
		return null;
	}
	void display() {
		Node ptr=front;
		while(ptr!=null) {
			System.out.println("Queue"+ptr.p.PNR);
			ptr=ptr.next;
		}
	}
}