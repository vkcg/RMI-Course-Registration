import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.io.*;

public class G_CourseClient {

    public static void main(String[] args){
    	G_CourseInterface course;
    	int cnum,opt;
    	String choice = "yes";
    	String[] courses = new String[10];
    	String user;
    	Scanner sc = new Scanner(System.in);
    	do{
    		try{
    			int flag =0;
	    		FileReader file = new FileReader("Courses.txt");
	    		Scanner input=new Scanner(file);
	    		input.useDelimiter("\t|\r\n");
	    		FileReader fileu = new FileReader("Users.txt");
	    		Scanner inu = new Scanner(fileu);
	    		inu.useDelimiter("\t|\r\n");
	    		System.out.println("enter username");
	    		user = sc.nextLine();
	    		String[] cou = null;
	    		while(inu.hasNext()){
	    			String usr = inu.next();
	    			String sub = inu.next();
	    			System.out.println(usr + "---" + sub);
	    			if(usr.equals(user))
	    			{
	    				flag = 1;
	    				cou = sub.split("\\+"); 
	    			}
	    		}
	    		fileu.close();
	    		//System.out.println(cou[0]);
	    		/*if(cou.length>0)
	    		{
		    		for(int x =0;x<cou.length;x++ )
		    		{
		    			System.out.println(cou[x]);
		    		}
	    			
	    		}*/
	    		int i=1;
	    		if(flag == 0)//user not avail
	    		{
	    			i=1;
		    		//System.out.println("List of Courses \n======================== \n");
		    		while(input.hasNext()){
		    			courses[i-1] = input.next();
		    			//System.out.println(i + " : " + courses[i-1]);
		    			i++;
		    		}
		    		//file.close();
	    		}
	    		else//user available
	    		{
	    			int f1 = 0;
	    			String temp = null;
	    			courses = new String[10];
	    			i=1;
	    			if(cou.length>0)
		    		{
			    		input=new Scanner(file);
	    				input.useDelimiter("\t|\r\n");
			    		while(input.hasNext())
			    		{
			    			temp = input.next();
			    			//System.out.println(temp);
			    			for(int x =0;x<cou.length;x++ )
			    			{
					    		if(temp.equals(cou[x])){
					    				f1 = 1;
					    		}
			    			}
			    			if(f1 == 0)
				    		{
				    			courses[i-1] = temp;
				    			i++;
				    		}
				    		else
				    		{
				    			f1 = 0;
				    		}
				    	}
				    	//file.close();
		    		}//if
		    		//print subjects which are not enrolled
		    		/*for(i=0;courses[i]!=null;i++)
		    		{
		    			System.out.println(i + " : " + courses[i]);
		    		}*/
	    		}//else
	    		
	    		file.close();
	    		System.out.println("1.Enroll 2.Drop");
			    opt = Integer.parseInt(sc.nextLine());
			    String name = "rmi://localhost//";;
			    switch(opt)
			    {
			    	case 1:
			    		if(courses[0] != null)
			    		{ 
				    		for(i=0;courses[i]!=null;i++)
			    			{
			    				System.out.println(i + " : " + courses[i]);
			    			}
			    			System.out.println("select course: enter number");
							cnum = Integer.parseInt(sc.nextLine());
							name = "rmi://localhost//";
			    			if(cnum<i && cnum>=0)
							{
								name=name+courses[cnum];
								System.out.println(name);
						    	try{
						    		course = (G_CourseInterface)Naming.lookup(name);
						    		//System.out.println(course.enroll());
						    		System.out.println(course.enroll());
						    	}//try block end
						    	catch(Exception e){
						    		System.out.println("Exception: " + e);
						    	}
							}
							else
							{
								System.out.println("wrong course!");
							}
							//write to file
							if(flag == 0)
							{
								BufferedWriter writer = new BufferedWriter(new FileWriter("Users.txt",true));
								writer.write(user + "\t" + courses[cnum] + "\r\n" );
								writer.close();
							}
							else
							{
								BufferedWriter writer = new BufferedWriter(new FileWriter("Users2.txt"));
								fileu = new FileReader("Users.txt");
					    		inu = new Scanner(fileu);
					    		inu.useDelimiter("\t|\r\n");
					    		String ch = "+";
					    		//System.out.println("char = " + ch);
					    		while(inu.hasNext()){
					    			String u1 = inu.next();
					    			String s1 = inu.next();
					    			if(u1.equals(user))
					    			{
					    				writer.write(user + "\t" + s1 + ch + courses[cnum] + "\r\n");
					    			}
					    			else{
					    				writer.write(u1 + "\t" + s1 + "\r\n");
					    			}
					    		}
					    		writer.close();
					    		fileu.close();
					    		File inputFile = new File("Users.txt");
								File outFile = new File("Users2.txt");
									
								if(inputFile.delete())
								{
									  outFile.renameTo(inputFile);
								}
					    		
							}
			    			
			    		}
			    		else{
			    			System.out.println("No new courses to enroll");
			    		}
		    			break;
			    	case 2:
			    		if(flag == 0)
	    				{ 
	    					System.out.println("No courses enrolled!");
	    				}
	    				else
	    				{
	    					int x;
	    					for(x =0;x<cou.length;x++ )
			    			{
					    		System.out.println(x + " : " + cou[x]);
			    			}
			    			System.out.println("select course: enter number");
							cnum = Integer.parseInt(sc.nextLine());
							name = "rmi://localhost//";
							if(cnum<x && cnum>=0)
							{
								name=name+cou[cnum];
								System.out.println(name);
						    	try{
						    		course = (G_CourseInterface)Naming.lookup(name);
						    		//System.out.println(course.enroll());
						    		course.drop();
						    	}//try block end
						    	catch(Exception e){
						    		System.out.println("Exception: " + e);
						    	}
						    	BufferedWriter writer = new BufferedWriter(new FileWriter("Users2.txt"));
								fileu = new FileReader("Users.txt");
					    		inu = new Scanner(fileu);
					    		inu.useDelimiter("\t|\r\n");
					    		String ch = "+";
					    		//System.out.println("char = " + ch);
					    		String[] drop = null;
					    		if(cou.length!=1)
					    		{
					    			while(inu.hasNext())
						    		{
						    			String u1 = inu.next();
						    			String s1 = inu.next();
						    			if(u1.equals(user))
						    			{
						    				writer.write(user + "\t" );
						    				for( x =0;x<cou.length;x++ )
							    			{
									    		if(x!=cnum)
									    		{
									    			writer.write(cou[x]);
									    			if(x+1<cou.length && x+1!=cnum)
									    			{
									    				writer.write(ch );
									    			}
									    		}
							    			}
							    			writer.write("\r\n" );
						    			}
						    			else{
						    				writer.write(u1 + "\t" + s1 + "\r\n");
						    			}
						    		}
					    		}
					    		else
					    		{
					    			while(inu.hasNext())
						    		{
						    			String u1 = inu.next();
						    			String s1 = inu.next();
						    			if(u1.equals(user))
						    			{
						    				//do nothing write nothing
						    			}
						    			else{
						    				writer.write(u1 + "\t" + s1 + "\r\n");
						    			}
						    		}
					    		}
					    		
					    		writer.close();
					    		fileu.close();
					    		File inputFile = new File("Users.txt");
								File outFile = new File("Users2.txt");
									
								if(inputFile.delete())
								{
									  outFile.renameTo(inputFile);
								}
					    		
							}
							else
							{
								System.out.println("wrong course!");
							}
	    				}
	    				break;
			    	default: System.out.println("Wrong Option");
			    }
    		}
    		catch(Exception e){
    			System.out.println("Exception: " + e);
    		}
    		
	    	System.out.println("do you want to continue?");
	    	System.out.println("enter yes or no ");
			choice = sc.nextLine();
    	}while(choice.equals("yes"));
    }
    
}
