import java.awt.List;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.Permission;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageServer extends UnicastRemoteObject implements ImageServerDataProvider {
	
	private static final String rootDirectoryForImages = "./Images/";
	
	public ImageServer() throws RemoteException {
		super();
	}

	public String[] getAllFiles() throws RemoteException {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();		
			Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/imagesDB;create=true;user=me;password=mine");
			
			ArrayList<String> results = new ArrayList<String>();
				
			Statement st1 = connection.createStatement();
			ResultSet rs = st1.executeQuery("Select file_name from file_info_table");
			
			while(rs.next()) {
				results.add(rs.getString(1));
			}
			
			String[] res = new String[results.size()];
			results.toArray(res);
			return res;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	public FileInfo getFileInfo(String fileName) throws RemoteException{
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();		
			Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/imagesDB;create=true;user=me;password=mine");

			PreparedStatement st = connection.prepareStatement("Select * from file_info_table where file_name=?");
			st.setString(1, fileName);
			ResultSet rs = st.executeQuery();
			
			FileInfo res = null;
			if (rs.next()) {
				String rFileName = rs.getString("file_name");
				String rFileAuthor = rs.getString("file_author");
				String rFileCreationDate = rs.getString("file_creation_date");
				String rFileDescription = rs.getString("file_description");
				byte[] content = getFileContent(rFileName);
					
				res = new FileInfo(rFileName, rFileAuthor, rFileCreationDate, rFileDescription, content);
			}
			
			return res;		
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	private byte[] getFileContent(String filePath) throws IOException {
		String fullPath = rootDirectoryForImages + filePath;

		byte[] imageInByte;
		BufferedImage originalImage = ImageIO.read(new File(fullPath));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(originalImage, filePath.substring(filePath.lastIndexOf('.') + 1), baos);
		
		baos.flush();
		imageInByte = baos.toByteArray();
		baos.close();
		
		return imageInByte;
	}
	private static void initDb() throws Exception {
		Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/imagesDB;create=true;user=me;password=mine");
		
		File folder = new File(rootDirectoryForImages);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> results = new ArrayList<String>();
		
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile()) {
	    		String fileName = listOfFiles[i].getName();
	    		PreparedStatement st = connection.prepareStatement(
						"insert into file_info_table " +
						"(file_name, file_author, file_creation_date, " +
						"file_description) " +
						"values (?, ?, ?, ?)");
			    st.setString(1, fileName);
			    st.setString(2, "Unknown");
			    st.setString(3, "22.11.2017");
			    st.setString(4, "Description of " + fileName);
			    st.executeUpdate();
			    st.close();
			} 
	    }
	}
	
	public static void main(String[] args) {
		try {
			System.setProperty("java.rmi.server.hostname", "localhost");
			System.setProperty("java.rmi.server.codebase", "file:///D:/Studying/III_Cource/V_Semester/AVPZ/Solutions_Eclipse/ImageViewProject/bin/");			
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager() {					
					public void checkConnect(String host, int port, Object context) {}
					public void checkConnect(String host, int port) {}
					public void checkPermission(Permission perm) {}		    
			  });
	        }
				
			ImageServer instance = new ImageServer();   
			Naming.rebind("ImageServer", instance);

			System.out.println("Сервис зарегистрирован");
		} 
		catch (Exception e) {
				e.printStackTrace();
		}
	}
}
