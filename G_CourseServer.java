import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
import java.util.*;

public class G_CourseServer {

    public static void main(String []argv){
    	String r,name;
    	String[] courses = new String[10];
    	PrintWriter bw = null;
    	File offered = null;
    	try{
    		G_CourseServant c= new G_CourseServant();
    		G_CourseInterface course;
    		FileReader file = new FileReader("CourseDetails.txt");
    		
			//BufferedWriter writer = new BufferedWriter(new FileWriter("cd2.txt"));
			Scanner input=new Scanner(file);
			
			input.useDelimiter("\t|\r\n"); //delimitor is one or more spaces
			
			//System.out.println(r);
			while(input.hasNext()){
				c = new G_CourseServant();
				c.cn = input.next();
				c.ct = input.next();
				c.desc = input.next();
				c.sec = Integer.parseInt(input.next());
				c.roomcap = 0;
				FileReader file1 = new FileReader("Timmings.txt");
				Scanner input1=new Scanner(file1);
				input1.useDelimiter("\t|\r\n");
				r =  Integer.toString(new Random().nextInt((9-1) + 1)  + 1);
				while(input1.hasNext()){
					if(input1.next().equals(r))
					{
						c.time = input1.next();
					}
				}
				name = "rmi://localhost//";
				name=name+c.ct;
				System.out.println("Binding - " + c.ct);
				//Naming.unbind(name);
    			Naming.rebind(name,c);
    			offered = new File("CoursesOffered.txt");
			    bw = new PrintWriter(new BufferedWriter(new FileWriter(offered.getAbsoluteFile(), true)));
			    bw.append(c.cn + "\t" + c.ct + "\t" + c.desc + "\t" + c.sec + "\t" + c.time + "\r\n");
			    bw.close();
			}
			
    		while(true)
    		{
    			try{
    				FileReader f1 = new FileReader("Courses.txt");
	    			Scanner in1 = new Scanner(f1);
	    			in1.useDelimiter("\t|\r\n");
	    			int i=1;
	    			while(in1.hasNext()){
	    				name = "rmi://localhost//";
	    				courses[i-1] = in1.next();
	    				//
	    				name=name+courses[i-1];
	    				//System.out.println(name);
	    				course = (G_CourseInterface)Naming.lookup(name);
			    		if(course.getCount()>5)
			    		{
			    			BufferedWriter writer = new BufferedWriter(new FileWriter("CourseDetails2.txt"));
			    			file = new FileReader("CourseDetails.txt");
							input = new Scanner(file);
							input.useDelimiter("\t|\r\n"); //delimitor is one or more spaces
			    			while(input.hasNext()){
			    				System.out.println("check");
			    				String conum = input.next();
			    				String coname = input.next();
								String desc = input.next();
								int sec = Integer.parseInt(input.next());
								if(coname.equals(courses[i-1]))
								{
									c = new G_CourseServant();
									c.cn = conum;
									c.ct = coname;
									c.desc = desc;
									c.roomcap = 0;
									c.sec = sec+1;
								}
								writer.write(conum + "\t" + coname + "\t" + desc + "\t" + sec);
			    			}
			    			writer.write(c.cn + "\t" + c.ct + "\t" + c.desc + "\t" + c.sec);
			    			file.close();
			    			File inputFile = new File("CourseDetails.txt");
								File outFile = new File("CourseDetails2.txt");
									
								if(inputFile.delete())
								{
									  outFile.renameTo(inputFile);
								}
    						FileReader file2 = new FileReader("Timmings.txt");
			    			Scanner input2 = new Scanner(file2);
			    			input2.useDelimiter("\t|\r\n");
							String r1 =  Integer.toString(new Random().nextInt((9-1) + 1)  + 1);
							System.out.println(r1);
							while(input2.hasNext()){
								//System.out.println("time loop: ");
								if(input2.next().equals(r1))
								{
									c.time = input2.next();
									System.out.println("time: "+c.time);
								}
							}
							System.out.println("rebinding");
							Naming.unbind(name);
			    			Naming.rebind(name,c);
			    			offered = new File("CoursesOffered.txt");
			    			bw = new PrintWriter(new BufferedWriter(new FileWriter(offered.getAbsoluteFile(), true)));
			    			bw.append(c.cn + "\t" + c.ct + "\t" + c.desc + "\t" + c.sec + "\t" + c.time + "\r\n");
			    			bw.close();
			    		}
	    				i++;
	    			}
			    		
			    	}
			    	catch(Exception e){
			    		System.out.println("Exception: " + e);
			    	}
    			    			
    		}
    	
    	}
    	catch(Exception e){
    		System.out.println("Exception: " + e);
    	}
    }
    
}
