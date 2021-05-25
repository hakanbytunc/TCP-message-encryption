package security;

import java.io.*;
import java.net.*;


public class Client{
    public static void main(String [] args){
        try{
        	Socket s=new Socket("localhost",5678);
        	DataInputStream so=new DataInputStream(s.getInputStream());
            System.out.println(so.readUTF());
            FileOutputStream f=new FileOutputStream("/Users/hakantunc/Desktop/sample1.txt");
            int p;
            System.out.println("please wait........");
 
            while((p=so.read())!=-1){
                   f.write(p);
            }
 
            InputStream fi=new FileInputStream("/Users/hakantunc/Desktop/sample1.txt");
 
 
            byte[] bytes=MyAESUtils.toByteArray(fi);
            byte[] debytes=MyAESUtils.decrypt(bytes);
            String temp=new String(debytes);
            fi.close();
            System.out.println("decrypted content:"+temp);
 
            FileOutputStream fos=new FileOutputStream("/Users/hakantunc/Desktop/sample1.txt");
            fos.write(debytes);
              s.close();
              so.close();
              f.flush();
              f.close();
              System.out.println("complete!");
        }catch(IOException e){
            System.out.println("file transfer failed!");
        }
    }
}
