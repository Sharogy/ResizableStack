package Stacks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class Stackviewer<T> {
	ResizableStack thisstack;
	private static int counter = 0;
	
	public Stackviewer(ResizableStack s)
	{
		thisstack = s;
	}
	
	public T viewtopitem()
	{
		T t = (T) thisstack.pop();
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
		ResizableStack s2 = new ResizableStack(thisstack.size());
		while (!thisstack.isEmpty())
		{
			t = (T) thisstack.pop();
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
		ResizableStack s2 = new ResizableStack(thisstack.size());
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
		ResizableStack s2 = new ResizableStack(thisstack.size());
		if (index <= thisstack.size())
		{
			T t = null;
			for (int i = 0; i<index; i++)
			{
				t = (T) thisstack.pop();
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
			ResizableStack s2 = new ResizableStack(thisstack.size());
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
		int med = (int) li.get((int) (Math.floor(li.size()/2)));
		System.out.println(li.size());
		if (li.size() == 1)
		{
			if (n == med)
			{
				counter++;
				return counter;
			}
			else 
			{
				counter = -1;
				return counter;
			}
		}
		
		else
		{
			if (n == med)
			{
				counter++;
				return counter;
			}
//			
			int size = li.size()-(int) (Math.floor(li.size()/2));
//			
			if (n > med)
			{
				List li2 = new ArrayList(size);
				for (int i = (int) (Math.floor(li.size()/2)); i< li.size(); i++)
				{
					li2.add(li.get(i));
				}
				counter++;
				binarysearch(n, (ArrayList) li2);
//				
			}
			if (n < med)
			{
				List li2 = new ArrayList(size);
				for (int i = 0; i< size; i++)
				{
					li2.add(li.get(i));
				}

				counter++;
				binarysearch(n, (ArrayList) li2);
			}
		return counter;
		}
		
		
	}
	
	public static ResizableStack<Integer> randomstack(int size)
	{
		Random random = new Random();
		int randomint;
		ResizableStack<Integer> randomstack = new ResizableStack(size);
		for (int i = 0; i<size; i++)
		{
			randomint =  random.nextInt(100);
			randomstack.push(randomint);
		}	
		return randomstack;
	}
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main (String[] argz)
	{	
		System.out.println("viewtopitem");
		ResizableStack<Object> rs = new ResizableStack<Object>(5);
		rs.push(1);
		rs.push(2);
		rs.push(3);
		rs.push(4);
		rs.push(5);	
		Stackviewer sv = new Stackviewer(rs);
		sv.removebottomitem();
		sv.removebottomitem();
		System.out.println(sv.viewbottomitem());
		//System.out.println(sv.thisstack.size());
		
		
//		for (int i = 0; i<10; i++)
//		{
//			int size = (int) Math.pow(2, i);
//			ResizableStack<Integer> rs = Stackviewer.randomstack(10000*size);
//			Random random = new Random();
//			int randomindex = random.nextInt(rs.size());
//			Stackviewer sv = new Stackviewer(rs);
//			Stopwatch timer = new Stopwatch();
//			sv.removenthitem(randomindex);
//			double time = timer.elapsedTime();
//			System.out.print(time + ", ");
//		}
	}
}
