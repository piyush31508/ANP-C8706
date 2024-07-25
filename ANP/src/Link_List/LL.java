package Link_List;

public class LL {
	
	class Node{
		String data;
		Node next;
		
		Node(String data){
			this.data=data;
			next=null;
		}
	}
	Node head=null;
	public void addFirst(String data) {
		Node newNode = new Node(data);
		if(head==null) {
			head=newNode;
			return;
		}	
		newNode.next = head;
		head = newNode;
	}
	
	public void addLast(String data) {
		Node newNode = new Node(data);
		if(head==null) {
			head=newNode;
			return;
		}
		Node curent=head;	
		while(curent.next!=null)
		{
			curent=curent.next;
		}
		curent.next = newNode;
		
	}
	
	public void printList() {
		if(head == null) {
			System.out.println("List is empty");
		}
		Node curent=head;
		while(curent!=null)
		{
			System.out.print(curent.data+" -> ");
			curent=curent.next;
		}
		
	}
	
	public void deleteFirst() {
		if(head == null) {
			System.out.println("The list is empty");
 		    return;
		}
		head=head.next;
	}
	
	public void deleteLast() {
		if(head == null) {
			System.out.println("The list is empty");
 		    return;
		}
		if(head.next==null) {
			head=null;
			return;
		}
		
		Node secondLast = head;
		Node last = head.next;
		
		while(last.next!=null) {
			last=last.next;
			secondLast = secondLast.next;
		}
		
		secondLast.next = null;
	}
	
	public static void main(String[] args) {
		
		LL list = new LL();
		list.addFirst("is");
		list.addFirst("This");
		
		list.printList();
		
		System.out.println();
		list.addLast("a");
		list.addLast("List");
		
		list.printList();
		System.out.println();

		list.deleteFirst();
		
		list.printList();
		
		System.out.println();
		
		list.deleteLast();
		list.printList();
		
		
		
	}

}
