package services.scientist.author;

import java.util.ArrayList;
import java.util.List;

public class GetAuthorImpl implements GetAuthor{

	public List<Author> getAuthors() {
		List<Author> list = new ArrayList<Author>();
		Author author = new Author();
		author.setRealname("yufaye");
		author.setEmail("squallapp@gmail.com");
		author.setUid("uestc");
		list.add(author);
		Author author2 = new Author();
		author.setRealname("dujuan");
		author.setEmail("dujuan@gmail.com");
		author.setUid("whut");
		list.add(author2);
		Author author3 = new Author();
		author.setRealname("goyuanyuan");
		author.setEmail("goyuanyuan@gmail.com");
		author.setUid("peiking");
		list.add(author3);
		System.out.println("list size is: "+list.size());
		return list;
	}

}
