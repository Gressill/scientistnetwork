package services.dao;

import java.util.List;

import services.scientist.author.Author;

public interface ADao {
	public List<Author> getAll();
	public void createAuthor(final Author author);
}
