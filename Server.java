package security;

import java.net.*;
import java.io.*;
import java.io.File;

public class Server{
	private static int port=5678;
	
	public static void main(String [] args)throws Exception{
		
			ServerSocket s=new ServerSocket(port);
			try {
			Socket s1=s.accept();
			System.out.println("Client connection success!");
			DataOutputStream so=new DataOutputStream(s1.getOutputStream());
 
			so.writeUTF("FileName:sample1.txt "+"\n"+"ServerIp:"+s.getInetAddress()+"\n"+"ServerPort:"+s.getLocalPort());
			FileInputStream f=new FileInputStream("/Users/hakantunc/Desktop/sample2.txt");
			BufferedReader reader = new BufferedReader(new FileReader("/Users/hakantunc/Desktop/sample2.txt"));
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			String ls = System.getProperty("line.separator");
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			// delete the last new line separator
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			reader.close();

			String list = stringBuilder.toString();
			byte[] bytes=new byte[list.length()];
			bytes=MyAESUtils.encrypt(list);
			so.write(bytes);
		  	  s.close();
		  	  f.close();
		  	  s1.close();
		  	  so.flush();
		  	  so.close();
			}catch(Exception e) {
			e.printStackTrace();
		}{
			
		}
	}
}

