package Stacks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class Stackviewer<T> {
	ResizableStack<T> thisstack;
	private static int counter = 0;
	
	public Stackviewer(ResizableStack<T> s)
	{
		thisstack = s;
	}
	
	public T viewtopitem()
	{
		T t = thisstack.pop();
		thisstack.push(t);
		return t;
	}
	
	public void removetopitem()
	{
		thisstack.pop();
		
	}
	
	@SuppressWarnings("unchecked")
	public T viewbottomitem()
	{
		T t = null;
		ResizableStack<T> s2 = new ResizableStack<T>(thisstack.size());
		while (!thisstack.isEmpty())
		{
			t = thisstack.pop();
			s2.push(t);
		}
		int tempsize = s2.size();
		for (int i = 0; i<tempsize; i++)
		{
			thisstack.push(s2.pop());
		}	
		return t;
	}
	

	@SuppressWarnings("unchecked")
	public void removebottomitem()
	{
		ResizableStack<T> s2 = new ResizableStack<T>(thisstack.size());
		int tempsize = thisstack.size();
		for (int i = 1; i < tempsize; i++)
		{
			s2.push(thisstack.pop());
		}
		int tempsize2 = s2.size();
		thisstack.pop();
		for (int i = 0; i < tempsize2; i++)
		{
			thisstack.push(s2.pop());
		}
	}
	
	@SuppressWarnings("unchecked")
	public T viewnthitem(int index)
	{
		ResizableStack<T> s2 = new ResizableStack<T>(thisstack.size());
		if (index <= thisstack.size())
		{
			T t = null;
			for (int i = 0; i<index; i++)
			{
				t = thisstack.pop();
				s2.push(t);
			}
			int tempsize = s2.size();
			for (int i = 0; i<tempsize; i++)
			{
				thisstack.push(s2.pop());
			}
			return t;
		}
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public void removenthitem(int index)
	{
		if (index <= thisstack.size())
		{
			ResizableStack<T> s2 = new ResizableStack<T>(thisstack.size());
			for (int i = 0; i < index; i ++ )
			{
				s2.push(thisstack.pop());				
			}
			s2.pop();
			int tempsize = s2.size();
			for (int i = 0; i<tempsize; i++)
			{
				thisstack.push(s2.pop());
			}
		}
		else
		{
			System.out.println("No such element");
		}
			
	}
	
	public static int binarysearch(int n, ArrayList li)
	{
		Collections.sort(li);
		int med = (int) li.get((int) (Math.floor(li.size()/2)+1));
		System.out.println(li.size());
		System.out.println(n > med);
		if (li.size() <= 1)
		{
			counter = -1;
			return counter;
		}
		
		else
		{
			
		if (n == med)
		{
			counter++;
			return counter;
		}
		
		List li2 = new ArrayList(li.size()-(int) (Math.floor(li.size()/2)+1));
		
		if (n > med)
		{
			for (int i = (int) (Math.floor(li.size()/2)+1); i< li.size(); i++)
			{
				li2.add(li.get(i));
			}
			counter++;
			binarysearch(n, (ArrayList) li2);
			
		}
		if (n < med)
		{
			System.out.println("smaller");
			System.out.println(li.size()/2);
			for (int i = 0; i< (int) (Math.floor(li.size()/2)+1); i++)
			{
				li2.add(li.get(i));
			}
			System.out.println(li2.size());
			counter++;
			binarysearch(n, (ArrayList) li2);
		}
		return counter;
		}
		
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main (String[] argz)
	{
		ResizableStack<Object> rs = new ResizableStack<>(4);
//		rs.push("test");
//		rs.push(5);
//		rs.push(4.0);
//		rs.push("test2");
//		rs.push(10);
//		rs.push(6.6);
		
		Stackviewer sv = new Stackviewer(rs);
		
//		System.out.println(sv.viewnthitem(4));
//		System.out.println(sv.viewtopitem());
//		sv.removetopitem();
//		System.out.println(sv.viewtopitem());
//		System.out.println(sv.viewbottomitem());
//		sv.removebottomitem();
//		System.out.println(sv.viewbottomitem());
//		sv.removenthitem(5);
//		System.out.println(sv.viewnthitem(3));
//		sv.removenthitem(3);
//		System.out.println(sv.viewnthitem(3));
		
		List list = new ArrayList();
		list.add(1);
		list.add(4);
		list.add(76);
		list.add(234);
		list.add(5);
		list.add(67);
		list.add(867);
		list.add(433);
		list.add(423);
		list.add(76);
		
		System.out.println(sv.binarysearch(1, (ArrayList) list));
		
	}

}
