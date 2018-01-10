package nukezam.mailtoyou.email;

/** 
* @Package :nukezam.mailtoyou.email
* @Title: Main.java 
* @Package nukezam.mailtoyou.email 
* @author zekun ma burmaing@gmail.com   
* @date 2018年1月8日 下午8:33:47 
* @version V1.0   
*/
public class Main {
public static void main(String[] args) {
	EmailToYou emailToYou= new EmailToYou();
	emailToYou.run();
	try {
		emailToYou.wait(1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}
}
