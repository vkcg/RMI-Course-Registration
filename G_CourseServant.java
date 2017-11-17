import java.rmi.*;
import java.rmi.server.*;

public class G_CourseServant extends UnicastRemoteObject implements G_CourseInterface{
	//private String msg;
	String cn, ct, time, desc;
	int roomcap, sec; 
	public G_CourseServant() throws RemoteException
	{
		//cn = name;
	}
	public void det() throws RemoteException
	{
		System.out.println(cn +": "+ct+" - "+desc +" :"+roomcap+ " time: "+time);
	}
	public int getCount() throws RemoteException
	{
		return roomcap;
	}
	public void setCount(int i) throws RemoteException
	{
		roomcap = i;
	}
	public String enroll() throws RemoteException
	{
		int x = getCount();
		setCount(x+1);
		det();
		return "Enrolling! " + cn;
	}
	public void drop() throws RemoteException
	{
		int x = getCount();
		if(x>0)
		{
			setCount(x-1);
		}
		det();
	}
}
