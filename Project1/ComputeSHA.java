import java.io.*;
import java.security.*;

public class ComputeSHA{

		static MessageDigest md; // static field is better
		static InputStream file; // InputStream instead of FileInputStream is better

	public static void main(String[] args) {

		if(args == null || args.length == 0) {
			System.out.println("Please input the file's name.");
			return;
		}

		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch(NoSuchAlgorithmException e) {
			System.out.println("Cannot compute SHA-1 hash code.");
			return;
		}

		try {
			file = new FileInputStream(args[0]);
		} catch (FileNotFoundException e){
			System.out.println("Cannot find the file " + args[0]);
		}

		byte[] dataBytes = new byte[1024];
		int nread = 0;

		try {
			while((nread = file.read(dataBytes)) != -1) { 
			//Reads up to dataBytes.length bytes of data from this input stream into an array of bytes
			//the total number of bytes read into the buffer, or -1 if there is no more data because the end of the file has been reached.

				md.update(dataBytes,0,nread); //public void update(byte[] input, int offset, int len)

			}
			
			byte[] mdbytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < mdbytes.length; i++) {
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			file.close();
			System.out.println(sb.toString());

		} catch (IOException e) {
			System.out.println("Cannot read from file.");
			return;
		}
	}
}
