package Stacks;
public class Stackviewer<T> {
	ResizableStack<T> thisstack;
	
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main (String[] argz)
	{
		ResizableStack<Object> rs = new ResizableStack<>(4);
		rs.push("test");
		rs.push(5);
		rs.push(4.0);
		rs.push("test2");
		rs.push(10);
		rs.push(6.6);
		
		Stackviewer sv = new Stackviewer(rs);
		
		System.out.println(sv.viewnthitem(4));
		System.out.println(sv.viewtopitem());
		sv.removetopitem();
		System.out.println(sv.viewtopitem());
		System.out.println(sv.viewbottomitem());
		sv.removebottomitem();
		System.out.println(sv.viewbottomitem());
		sv.removenthitem(5);
		System.out.println(sv.viewnthitem(3));
		sv.removenthitem(3);
		System.out.println(sv.viewnthitem(3));
		
	}

}
