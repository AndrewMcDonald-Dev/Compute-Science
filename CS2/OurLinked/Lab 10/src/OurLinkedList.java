import java.util.*;
public class OurLinkedList<E> {
    private Node<E> head;

    public OurLinkedList() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFront(E e) {
        Node<E> n = new Node<>(e);
        if (isEmpty()) {
            head = n;
        } else {
            Node<E> temp = head;
            head = n;
            head.setLink(temp);
			/*int temp=a[i];
			a[i]=a[min];
			a[min]=temp;*/
        }
		/*Node<E> n=new Node<E>(e);
		if(!isEmpty())
		{
			n.setLink(head);
			head=n;
		}
		else
		{
			head=n;
		}*/
    }

    public int size() {
        int count = 0;
        Node<E> temp = head;
        while (temp != null) {
            count++;
            temp = temp.getLink();
        }
        return count;
    }

    public String toString() {
        Node<E> temp = head;
        String s = "head ==> ";
        while (temp != null) {
            s += temp.getData() + " ==> ";
            temp = temp.getLink();
        }
        s += " null";
        return s;
    }

    public Node<E> getLast() {
        if (isEmpty())
            return null;
        Node<E> temp = head;
        while (temp.getLink() != null)
            temp = temp.getLink();
        return temp;
    }

    public void add(E e) {
        Node<E> n = new Node<E>(e);
        if (!isEmpty()) {
            Node<E> l = getLast();
            l.setLink(n);
        } else
            head = n;
    }

    public Node<E> get(int i) {
        if (i > size() || i < 0 || isEmpty())
            return null;//throw exception
        Node<E> temp = head;
        int x = 0;
        while (x < i) {
            temp = temp.getLink();
            x++;
        }
        return temp;
    }


    public E remove() {
        if (isEmpty())
            return null;
        Node<E> temp = head;
        E x = head.getData();
        head = head.getLink();
        temp.setLink(null);
        return x;
    }

    public OurLinkedList<E> copy() {
        OurLinkedList<E> hold = new OurLinkedList<E>();
        Node<E> temp = head;
        while (temp != null) {
            hold.add(temp.getData());
            temp = temp.getLink();
        }
        return hold;
    }

    public static OurLinkedList<Object> toList(Object[] a) //call with OurLinkedList<Object> ll=OurLinkedList.toList();
    {
        OurLinkedList<Object> l = new OurLinkedList<Object>();
        for (int i = 0; i < a.length; i++) {
            l.add(a[i]);
            // 3 6 9
            //addFront   9 6 3
            //add  3 6 9
        }
        return l;
    }

    public OurLinkedList<E> toList2(E[] a) {
        OurLinkedList<E> l = new OurLinkedList<E>();
        for (int i = 0; i < a.length; i++) {
            l.add(a[i]);
        }
        return l;
    }

    public Object[] toArray() {
        Object[] a = new Object[size()];
        for (int i = 0; i < size(); i++)
            a[i] = get(i).getData();
        return a;
    }

    public boolean add(E e, int i) {
        if (i < 0 || i > size())
            return false;
        if (isEmpty()) {
            add(e);
            return true;
        } else if (i == 0) {
            addFront(e);
            return true;
        } else if (i == size()) {
            add(e);
            return true;
        } else {
            int count = 1;
            Node<E> n = new Node<>(e);
            Node<E> temp = head;
            while (temp.getLink() != null && count < i) {
                temp = temp.getLink();
                count++;
            }
            n.setLink(temp.getLink());
            temp.setLink(n);
            return true;
        }
    }

    public int indexOf(E e) {
        int count = 0;
        Node<E> temp = head;
        while (temp != null) {
            if (temp.getData().equals(e))
                return count;
            temp = temp.getLink();
            count++;
        }
        return -1;
    }

    public boolean contains(E e) {
        Node<E> temp = head;
        while (temp != null) {
            if (temp.getData().equals(e))
                return true;
            temp = temp.getLink();
        }
        return false;
    }

    public int count(E e) {
        int count = 0;
        Node<E> temp = head;
        while (temp != null) {
            if (temp.getData().equals(e))
                count++;
            temp = temp.getLink();
        }
        return count;
    }

    public E remove(int i) {
        if(i<0||i>size()-1)
            return null;
        if(i==0)
            return remove();
        Node<E> temp = get(i);
        Node<E> l=get(i-1);
        if(i==size()-1)
            l.setLink(null);
        else
            l.setLink(get(i + 1));
        return temp.getData();
    }

    public E removeLast() {
        if (isEmpty())
            return null;
        if (size() == 1)
            return remove();
        Node<E> temp = getLast();
        Node<E> temp2 = get(size() - 2);
        temp2.setLink(null);
        return temp.getData();
    }

    public OurLinkedList<E> toList(ArrayList<E> al) {
        OurLinkedList<E> temp = new OurLinkedList<>();
        for (int i = 0; i < al.size(); i++)
            temp.add(al.get(i));
        return temp;
    }

    public ArrayList<E> toList() {
        ArrayList<E> temp = new ArrayList<>();
        for (int i = 0; i < size(); i++)
            temp.add(get(i).getData());
        return temp;
    }

    public void clear() {
        head=null;
    }

    public boolean remove(E e){
        return remove(indexOf(e)) != null;
    }

    public boolean equals(OurLinkedList<E> oll){
        if(this.size() != oll.size())
            return false;
        Node<E> temp = head;
        Node<E> temp2 = oll.head;
        while(temp != null){
            E data1 = temp.getData();
            E data2 = temp2.getData();
            if(!data1.equals(data2))
                return false;
            temp = temp.getLink();
            temp2 = temp2.getLink();
        }
        return true;
    }

    public void join(OurLinkedList<E> ll2){
        this.get(size()-1).setLink(ll2.head);
        ll2.clear();
    }

    public void merge(OurLinkedList<E> ll2){
        //Empty checks
        if(this.isEmpty() && ll2.isEmpty())
            return;
        else if(this.isEmpty())
            head.setLink(ll2.head);
        else if(ll2.isEmpty())
            return;
        else {
            //Main merging algorithm
            Node<E> temp = head, temp2 = ll2.head;
            while (temp != null) {
                Node<E> hold = temp.getLink();
                Node<E> hold2 = temp2.getLink();
                //if the linked list is shorter than ll2 or both are same size
                if (hold == null) {
                    temp.setLink(temp2);
                    break;
                //if ll2 is shorter than linked list
                } else if (hold2 == null) {
                    temp.setLink(temp2);
                    temp2.setLink(hold);
                    break;
                }
                //cycles through a whole index of each linked list merging them
                temp.setLink(temp2);
                temp = temp.getLink();
                temp.setLink(hold);
                temp = temp.getLink();
                temp2 = hold2;
            }
        }
        ll2.clear();
    }
}
