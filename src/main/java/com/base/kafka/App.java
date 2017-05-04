package com.base.kafka;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	Map map = Collections.synchronizedMap(new HashMap()) ;
        System.out.println( "Hello World!" );
    }
}
