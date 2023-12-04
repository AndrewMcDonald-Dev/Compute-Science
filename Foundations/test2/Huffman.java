import java.util.*;
import java.io.*;
class Huffman
{
	private Node answer;
	private ArrayList<Character> symbols=new ArrayList<Character>();
	private ArrayList<Integer> frequencys=new ArrayList<Integer>();
	public Huffman(String f)throws IOException
	{
		answer=run(f);
	}
	public Node run(String f)throws IOException
	{
		BufferedReader in=new BufferedReader(new FileReader(f));
		int x=in.read();
		while(x!=-1)
		{
			char c=(char)x;
			if(symbols.contains(c))
				frequencys.set(symbols.indexOf(c),frequencys.get(symbols.indexOf(c))+1);
			else
			{
				symbols.add(c);
				frequencys.add(1);
			}
			x=in.read();
		}
		in.close();
		sort();
		ArrayList<Node> priortyQueue=enqueue();
		while(priortyQueue.size()>=2)
		{
			Node first=priortyQueue.remove(0);
			Node second=priortyQueue.remove(0);
			Node sum=new Node(first.frequency+second.frequency,'@');
			sum.left=first;
			sum.right=second;
			for(int i=0;i<priortyQueue.size();i++)
			{
				if(sum.frequency<priortyQueue.get(i).frequency)
				{				
					priortyQueue.add(i,sum);
					break;
				}
			}
			if(priortyQueue.size()==0)
				priortyQueue.add(sum);
		}
		return priortyQueue.remove(0);
	}
	private Node[] enqueue()
	{
		Node[] q=new Node[frequencys.size()];
		for(int i=0;i<frequencys.size();i++)
		{
			q[i]=new Node(frequencys.get(i),symbols.get(i));
		}
		return q;
	}
	private void sort()
	{
		for(int i=0;i<symbols.size()-1;i++)
		{
			int min=i;
			for(int j=min+1;j<symbols.size();j++)
			{
				if(frequencys.get(j)<frequencys.get(min))
					min=j;
			}
			int temp=frequencys.get(i);
			char temp2=symbols.get(i);
			frequencys.set(i,frequencys.get(min));
			symbols.set(i,symbols.get(min));
			frequencys.set(min,temp);
			symbols.set(min,temp2);
		}
	}
	/*
	private String build(Node n)
	{
		if(n!=null &&n.symbol!='@')
			return ""+n.frequency+" "+n.symbol+" "+build(n.left)+" "+build(n.right);
		if(n!=null)
			return ""+n.frequency+" "+build(n.left)+" "+build(n.right);
		return "";
	}
	public String toString()
	{
		return build(answer);
	}*/
}
class Node implements Comparable<Node>
{
	private int frequency;
	private char character;
	private Node left,right;
	public Node(int f,char c)
	{
		frequency=f;
		character=c;
		left=right=null;
	}
	public Node(int f)
	{
		this(f,'@');
	}
	public char getCharacter()
	{
		return character;
	}
	public int getFrequency()
	{
		return frequency;
	}
	public Node getLeft()
	{
		return left;
	}
	public Node getRight()
	{
		return right;
	}
	public void setCharacter(char c)
	{
		character=c;
	}
	public void setFrequency(int f)
	{
		frequency=f;
	}
	public void setLeft(Node l)
	{
		left=l;
	}
	public void setRight(Node r)
	{
		right=r;
	}
	public String toString()
	{
		if(character=='@')
			return "f: "+frequency;
		return "[ f: "+frequency+", c: "+character+" ]+\n";
	}
	public int compareTo(Node e)
	{
		return this.getFrequency()-e.getFrequency();
	}
}
class Test
{
	public static void main(String[] args)throws IOException
	{
		Huffman h=new Huffman("raw.txt");
		System.out.println(h);
	}
}