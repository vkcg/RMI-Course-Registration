import java.rmi.*;

public interface G_CourseInterface extends Remote{
	public void det() throws RemoteException;
	public String enroll() throws RemoteException;
	public void drop() throws RemoteException;
	public int getCount() throws RemoteException;
	public void setCount(int i) throws RemoteException;
}
