import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class IOEx4Test {

	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;

		byte[] temp = new byte[4];
		
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;

		input = new ByteArrayInputStream(inSrc);
		output = new ByteArrayOutputStream();	
		
		try {
			while(input.available() > 0) {
				int len = input.read(temp); //배열 크기만큼 불러옴
				output.write(temp, 0, len); //temp에서 0 index번지부터 len index까지
			}
		}catch(IOException e) {
			
		}
		outSrc = output.toByteArray();
		
		System.out.println("Input src" + Arrays.toString(inSrc));
		System.out.println("temp : " + Arrays.toString(temp));
		System.out.println("Output src" + Arrays.toString(outSrc));
	}

}
