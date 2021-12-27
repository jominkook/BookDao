package com.kosta.jdbcdao.jominkook;

import java.util.List;

public interface BookDao {
	public List<BookVo> select();
	public List<BookVo> select(int author_id);
	public boolean insert(BookVo bvo);
	public boolean update(BookVo bvo);
	
}
