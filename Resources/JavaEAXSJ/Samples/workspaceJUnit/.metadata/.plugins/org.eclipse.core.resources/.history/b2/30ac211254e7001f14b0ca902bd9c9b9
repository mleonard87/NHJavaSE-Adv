package com.mycom.digest;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MDExample {
	public MessageDigest curAlgorithm;
	
	public MDExample(){
		super();
		
		//get the filename
		String fileName = "c:\\LabFiles\\clipping.txt";
		
		//set the algorithm
		
		try {
			curAlgorithm = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//load the file
		FileInputStream in =null;
		ByteArrayOutputStream buffer = null;
		
		try {
			in= new FileInputStream(fileName);
			buffer = new ByteArrayOutputStream();
			int tmp;
			while((tmp=in.read())!= -1){
				buffer.write(tmp);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem opening file");
		}
		
		try {
			in.close();
		}catch(IOException e) {
			//...
		}
		curAlgorithm.reset();
		curAlgorithm.update(buffer.toByteArray());
		byte[] digestHash= curAlgorithm.digest();
		
		for (int i = 0; i<digestHash.length; i++){
			System.out.print(Integer.toString(digestHash[i]&0xFF, 16)+ " ");
		}
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MDExample myEx = new MDExample();
		System.out.println("Main done!");

	}

}
