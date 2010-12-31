package libs;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.springframework.flex.roo.addon.asdt.core.internal.antlr.AS3Parser.returnStatement_return;

/**
 * 首先，我们得知道gamil的投递服务器地址，在命令行中输入nslookup命令，然后输入set type=mx，
 * 接着输入gmail.com，出来几条记录，最后有一行：gmail-smtp-in.l.google.com internet address = 209.85.222.41，
 * 好投递服务器就是它了。经过以上准备我们就可以写个程序发垃圾邮件了，当然数量可以随便定了，
 * 可以设一个很无聊的大数字，直接把对方的邮箱挤爆掉，当然某些投递服务器比较智能，
 * 一次投递给同一个帐号的邮件多了会拒绝的
 * @author Administrator
 *
 */
public class Mail {
	private static final String CRLF = "\r\n";

	public static void main(String[] args) {
		// 循环发送邮件
//		for (int i = 0; i < 3; ++i) {
//			System.out.println("++++++++++++++" + (i + 1)
//					+ "++++++++++++++++++++");
//			send();
//		}
		System.out.println(trueOrFalse());

	}

	public static boolean trueOrFalse() {
		try {
			return false;
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			return true;
		}
	}
	
	private static void send() {
		PrintStream out = null;
		Scanner in = null;
		Socket smtpSocket = null;
		try {
			smtpSocket = new Socket("gmail-smtp-in.l.google.com", 25);
			out = new PrintStream(smtpSocket.getOutputStream());
			in = new Scanner(smtpSocket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (out == null || in == null) {
			return;
		}

		System.out.println("s:" + in.nextLine());

		out.print("helo nodomain.com" + CRLF);

		System.out.println("s:" + in.nextLine());

		out.print("mail from: <lixiaoming@gmail.com>" + CRLF);

		System.out.println("s:" + in.nextLine());

		out.print("rcpt to: <yuxingphp@gmail.com>" + CRLF);

		System.out.println("s:" + in.nextLine());

		out.print("data" + CRLF);

		System.out.println("s:" + in.nextLine());

		out.print("from: <noname@nodomain.com>" + CRLF);
		out.print("to: <testname@gmail.com>" + CRLF);
		out.print("subject: test mail" + CRLF);
		out.print("" + CRLF);
		out.print("balabalabalabalabala" + CRLF);
		out.print("" + CRLF);
		out.print("." + CRLF);

		System.out.println("s:" + in.nextLine());

		out.print("quit" + CRLF);
		System.out.println("s:" + in.nextLine());

		in.close();
		out.close();
		try {
			smtpSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
