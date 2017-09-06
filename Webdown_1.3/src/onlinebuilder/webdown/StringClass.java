/**
 * @author OnlineBuilder
 * Copyright (c) <2017> <OnlineBuilder>
 * No rights or licenses from any copyright holder or contributor is granted,
 * whether expressly, by implication, estoppel or otherwise. 
 * */
package onlinebuilder.webdown;

public interface StringClass 
{
    public static void print(Object input)
    {
    	System.out.print(input);
    }
    
    public static void println(Object input)
    {
    	System.out.println(input);
    }
    
    public static boolean contains(String substring, String wholeString) 
    		throws IndexOutOfBoundsException
    {
    	if (wholeString != null && substring != null)
		{
    		CharSequence target;
    		target = substring.subSequence(0, substring.length());
    		if (wholeString.contains(target))
    			return true;
    		else
    			return false;
		}
    	else 
    	{
           throw new IndexOutOfBoundsException();
    	}
    }
    
    public static String replaceAll(String target, String wholeString, String replacement)
    {
    	CharSequence newTarget;
		CharSequence newReplacement;
		newTarget = target.subSequence(0, target.length());
		newReplacement = replacement.subSequence(0, replacement.length());
    	while (contains(target, wholeString))
    	{
            wholeString = wholeString.replace(newTarget, newReplacement);
    	}
    	return wholeString;
    }
}
