package com.kosta.jdbcdao.jominkook;

import java.util.List;

public interface AuthorDao {
	public boolean insert(AuthorVo avo);
	public List<AuthorVo> select();
	public List<AuthorVo> AgetList();
	public boolean update(AuthorVo avo);
	
	
}
