package services.flex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import blah.MyEntity;

import services.scientist.author.Author;
import services.scientist.author.AuthorDAO;

public class GetAuthorList {

	public AuthorDAO authorDAO ;
	
	public List authorList() {
		return authorList2();
//		return getMyEntities();
	}
	
	public List authorList2() {

	   // ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	   // authorDAO = (AuthorDAO)context.getBean("authorDAO");
		System.out.println("diao yong le");
		List<Author> aList = new ArrayList<Author>();
		List findSome = authorDAO.findAll();
		aList = findSome;
		System.out.println("2");
		for (Author author : aList) {
			System.out.println(author.getRealname());
		}
		System.out.println("3");
		//return getMyEntities();
		return aList;
	}
	
	public static void main(String[] args) {

		GetAuthorList getAuthorList = new GetAuthorList();
		getAuthorList.authorList();
	}

	public AuthorDAO getAuthorDAO() {
		return authorDAO;
	}

	public void setAuthorDAO(AuthorDAO authorDAO) {
		this.authorDAO = authorDAO;
	}
}
