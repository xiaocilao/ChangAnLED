package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date time = sdf.parse("2016-12-22 12:22:11");
			System.out.println(time.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(data.getTime());
		
	}

}
