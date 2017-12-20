import java.io.Serializable;


public class FileInfo implements Serializable {
	private String filePath;
	private String fileAuthor;
	private String fileCreationDate;
	private String fileDescription;
	private byte[] fileContent;

	public FileInfo(String filePath, String fileAuthor, String fileCreationDate, String fileDescription, byte[] fileContent) {
		this.filePath = filePath;
		this.fileAuthor = fileAuthor;
		this.fileCreationDate = fileCreationDate;
		this.fileDescription = fileDescription;
		this.fileContent = fileContent;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileAuthor() {
		return fileAuthor;
	}

	public void setFileAuthor(String fileAuthor) {
		this.fileAuthor = fileAuthor;
	}

	public String getFileCreationDate() {
		return fileCreationDate;
	}

	public void setFileCreationDate(String fileCreationDate) {
		this.fileCreationDate = fileCreationDate;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	
	
}
