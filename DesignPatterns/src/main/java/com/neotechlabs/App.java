package com.neotechlabs;

import com.neotechlabs.singleton.Singleton;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "WELCOME TO DESIGN PATTERNS" );
        System.out.println("SINGLETON DESIGN PATTERN EXAMPLE");

        Singleton instance1 = Singleton.getInstance();
        System.out.println(instance1);
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance2);
    }
}
