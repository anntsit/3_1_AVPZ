import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ImageServerDataProvider extends Remote {
	FileInfo getFileInfo(String fileName) throws RemoteException;
	String[] getAllFiles() throws RemoteException;
}
