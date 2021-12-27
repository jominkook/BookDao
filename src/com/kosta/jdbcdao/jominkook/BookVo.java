package com.kosta.jdbcdao.jominkook;

public class BookVo  {
	
	private int book_id;
	private String book_title;
	private String book_pubs;
    private String book_pub;
    private int author_id;
    private String author_name;
	
	@Override
	public String toString() {
		return "BookVo [book_id=" + book_id + ", book_title=" + book_title + ", book_pubs=" + book_pubs + ", book_pub="
				+ book_pub + ", author_id=" + author_id + ", author_name=" + author_name + "]";
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getBook_title() {
		return book_title;
	}
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	public String getBook_pubs() {
		return book_pubs;
	}
	public void setBook_pubs(String book_pubs) {
		this.book_pubs = book_pubs;
	}
	public String getBook_pub() {
		return book_pub;
	}
	public void setBook_pub(String book_pub) {
		this.book_pub = book_pub;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getAuthor_name() {
		return author_name;
	}
	
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}                         
  
    
    
	

    
	
	
	
	
	
 

}
