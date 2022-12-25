package brs;
import brs.*;
//linked list
class LinkedList{
	Node head;
	LinkedList(){
		head=null;
	}
	void add(PassengerDetails p) {
		Node temp=new Node(p);
		if(head==null) {
			head=temp;
		}
		else {
			Node ptr=head;
			while(ptr.next!=null) {
				ptr=ptr.next;
			}
			ptr.next=temp;

		}
	}

	PassengerDetails search(int prn){
		Node ptr=head;
		while(ptr!=null) {
			if(ptr.p.PNR==prn) {
				return ptr.p;
			}
			ptr=ptr.next;
		}
		return null;
	}
	
	void display() {
		Node ptr=head;
		while(ptr!=null) {
			System.out.print("PNR"+ptr.p.PNR);
			ptr=ptr.next;
		}
	}

	void delete(PassengerDetails psg) {
		Node ptr=head;
		if(ptr!=null) {
			if(ptr.p.PNR==psg.PNR) {
				head=head.next;
			}
			else {
				while(ptr.next!=null) {
					if(ptr.next.p.PNR==psg.PNR) {
						break;
					}
					ptr=ptr.next;
				}
				ptr.next=ptr.next.next;
			}

		}

	}
}