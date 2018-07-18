package util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;



import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FTP {
//	Logger logger = LoggerFactory.getLogger(this.getClass());
	public String hostname;
	
	public Integer port;
	
	public String username;
	
	public String password;
	
	public String ftp_log_file_path;
	
	public FTPClient ftpClient;
	
	
	FileOutputStream fop ; 
	 
	File file;  
	String content;
	String start;
	String init;
	Date date;
	
	
	public FTP(String hostname, Integer port, String username, String password,String ftp_log_file_path) {
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
		this.ftp_log_file_path=ftp_log_file_path;
		
		
	}
	
	public void initFtpClient() {
		
		
		
		ftpClient = new FTPClient();
		ftpClient.setControlEncoding("utf-8");
	try {
		ftpClient.connect(hostname,port);
		ftpClient.login(username, password);
		int replyCode = ftpClient.getReplyCode();
		if(!FTPReply.isPositiveCompletion(replyCode)) {
			System.out.println("FTP connect failed...");
			date = new Date();
			SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			init = "FTP connect failed..." + sdf_1.format(date);
			fop.write(init.getBytes());
		}
		System.out.println("FTP connect successful!");
		}catch(MalformedURLException  e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	try {  
        file = new File(ftp_log_file_path);  
        fop = new FileOutputStream(file,true);  
        if (!file.exists()) {  
            file.createNewFile();  
        }  
        
        fop.write("\r\n".getBytes());
        fop.flush();  
        fop.close();  
    } catch (IOException e) {  
        e.printStackTrace();  
    } finally {  
        try {  
            if (fop != null) {  
                fop.close();  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        }
	}
	
	//upload
	public boolean uploadFile(String pathname, String fileName, String originfilename) {
		boolean flag =false;
		InputStream inputStream =null;
		try {
			
			System.out.println("开始上传文件...");
			date = new Date();
			SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			start = "开始上传文件..." + sdf_1.format(date);
			inputStream = new FileInputStream(new File(originfilename));
			initFtpClient();
			ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
			CreateDirecroty(pathname);
			ftpClient.makeDirectory(pathname);
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.storeFile(fileName, inputStream);
			//显示文件目录
//			FTPFile[] ftpFiles = ftpClient.listFiles();
//			for(FTPFile file: ftpFiles) {
//				System.out.println("folder :" +file.getName());
//			}
			inputStream.close();
			ftpClient.logout();
			flag = true;
			
			date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			content = " ==============" +fileName + "==============上传成功！ " + sdf.format(date.getTime());
			System.out.println("上传成功！！    " + sdf.format(date.getTime()));
			
			
//			logger.info("上传成功");
		}catch(Exception e) {
			
	
			date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			content = " ==============" +fileName + "==============上传失败！ " + sdf.format(date.getTime());
			System.out.println("上传失败!" + sdf.format(date.getTime()));
			e.printStackTrace();
		}finally {
		
//			if(ftpClient.isConnected()) {
//				try {
//					ftpClient.disconnect();
//				}catch(IOException e) {
//					e.printStackTrace();
//				}
//			}
		}
		try {  
            file = new File(ftp_log_file_path);  
            fop = new FileOutputStream(file,true);  
            if (!file.exists()) {  
                file.createNewFile();  
            }  
            
            byte[] contentInBytes = content.getBytes();  
            fop.write(start.getBytes());
            fop.write("\r\n".getBytes());
            fop.write(contentInBytes);  
            fop.write("\r\n".getBytes());
            fop.flush();  
            fop.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (fop != null) {  
                    fop.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
		return flag;
	}
	
	public boolean downloadFile(String pathname, String filename, String localpath) {
		
		boolean flag = false;
		OutputStream os = null;
		try {
			System.out.println("开始下载");
			initFtpClient();
//			CreateDirecroty(pathname);
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.changeWorkingDirectory(pathname + "/");
			FTPFile[] ftpFiles = ftpClient.listFiles();
			for(FTPFile file: ftpFiles) {
				if(filename.equalsIgnoreCase(file.getName())) {
					System.out.println("find download file :" +filename);
				File licalFile = new File(localpath + "/" + file.getName());
				os = new FileOutputStream(licalFile);
				ftpClient.retrieveFile(file.getName(), os);
				os.close();
				System.out.println("下载成功!");
				}
			}
			ftpClient.logout();
			flag = true;
		}catch(Exception e) {
			System.out.println("下载失败");
			e.printStackTrace();
		}finally {
			if(ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(null != os) {
				try {
					os.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	public boolean makeDirectory(String dir) {
		boolean flag = true;
		try {
			flag = ftpClient.makeDirectory(dir);
			if(flag) {
				System.out.println("创建文件夹 " + dir + "失败!");
			}else {
				System.out.println("创建文件夹 " + dir + "成功！");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	//判断服务器文件是否存在
	public boolean existFile(String path) throws IOException {
		
		boolean flag =false;
		FTPFile[] ftpFileArr = ftpClient.listFiles(path);
		if(ftpFileArr.length > 0) {
			flag = true;
		}
		return flag;
	}
	
	public boolean CreateDirecroty(String pathname) throws IOException {
		boolean success = true;
		String directory = pathname + "/";
		if(!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
			System.out.println("create folder!");
			int start = 0;
			int end = 0;
			if(directory.startsWith("/")) {
				start = 1;
			}else {
				start = 0;
			}
			end = directory.indexOf("/",start);
			String path = "";
			String paths = "";
			while(true) {
//				String subDirectory = new String(pathname.substring(start, end).getBytes("GBK"),"ios-8859-1");
				String subDirectory = new String(pathname.substring(start, end).getBytes());
				path = path + "/" + subDirectory;
				if(!existFile(path)) {
					if(makeDirectory(subDirectory)) {
						changeWorkingDirectory(subDirectory);
					}else {
						System.out.println("创建目录  " + subDirectory + "   失败");
						changeWorkingDirectory(subDirectory);
					}
				}else {
					changeWorkingDirectory(subDirectory);
				}
				paths = paths + "/" + subDirectory;
				start = end + 1;
				end = directory.indexOf("/", start);
				if(end <= start) {
					break;
				}
			}
			
		}
		return success;
	}
	//改变目录路径
	public boolean changeWorkingDirectory(String directory) {
		boolean flag = true;
		try {
			flag = ftpClient.changeWorkingDirectory(directory);
			if(flag) {
				System.out.println("进入文件夹 " + directory + "成功");
			}else {
				System.out.println("进入文件夹" + directory + "失败！开始创建文件夹");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
