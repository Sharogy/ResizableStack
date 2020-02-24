package Stacks;
import java.lang.reflect.Array;

public class ResizableStack<Item> {
	private Item[] stack;
	private int top;
	private int stacksize;
	private Class<?> c;
	@SuppressWarnings("unchecked")
	public ResizableStack (int length, Item... cls){
		if (cls.length > 0)
			throw new IllegalArgumentException("Do not provide values for class argument.");
		c = cls.getClass().getComponentType();
		stack = (Item[]) Array.newInstance(c, length);
		top = 0;
		stacksize = length;
	}
		
	@SuppressWarnings("unchecked")
	public void push(Item item)
	{
		if (top+1 > stacksize/2)
		{
			Item[] newstack = (Item[]) Array.newInstance(c, stacksize*2);
			for (int i = 0; i<top; i++)
			{
				newstack[i] = stack[i];
			}
			stack = newstack;
			stacksize = stacksize*2;
		}
	
		stack[top++] = item;
	}
	
	@SuppressWarnings("unchecked")
	public Item pop()
	{
		if (isEmpty())
		{
			System.out.println("empty");
			return null;
		}
		else
		{
			if (top < Math.round(stacksize/2))
			{
				Item[] newstack = (Item[]) Array.newInstance(c, Math.round(stacksize/2));
				for (int i = 0; i<top; i++)
				{
					newstack[i] = stack[i];
				}
				stack = newstack;
				stacksize = stacksize/2;
			}
			top--;
			return stack[top];
		}		
	}
	
	public boolean isEmpty()
	{	
		return top == 0;
	}
	
	public int size()
	{
		return top;
	}
	
	

	
	
	@Override
    public String toString() {
        return "Resizablestack of " + stack.getClass().getComponentType().getName()
            + "[" + stack.length + "]";
    }
	
	
	
	public static void main (String[] argz)
	{
		ResizableStack<Integer> teststack = new ResizableStack<Integer>(4);
		
		teststack.push(2);
		teststack.push(3);
		teststack.push(4);
		
		System.out.println("testing stack 1, commencing");
		System.out.println();
		System.out.println(teststack);
		System.out.println(teststack.size());
		System.out.println(teststack.isEmpty());

		int i = teststack.pop();
		int j = teststack.pop();
		int k = teststack.pop();
		System.out.println(teststack);
		System.out.println(i);
		System.out.println(j);
		System.out.println(k);
		System.out.println(teststack.size());
		System.out.println(teststack.isEmpty());
		teststack.pop();	
		
		ResizableStack<Object> teststack2 = new ResizableStack<Object>(4);
		teststack2.push("Test");
		teststack2.push(5);
		teststack2.push(5.0);
		System.out.println();
		System.out.println("testing stack 2, commencing");
		System.out.println();
		System.out.println(teststack2.pop());
		System.out.println(teststack2.pop());
		System.out.println(teststack2.pop());
		

	}
}
