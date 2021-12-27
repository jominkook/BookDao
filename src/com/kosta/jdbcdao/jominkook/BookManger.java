package com.kosta.jdbcdao.jominkook;


import java.util.List;
import java.util.Scanner;



public class BookManger {

	public static void main(String[] args) {
				
		while(true) {
			System.out.println("작가번호를 입력하세요");
			List<AuthorVo> alist = AgetList();
			AuthorVo avo = null;
			Scanner sc = new Scanner(System.in);
			List<BookVo> blist = null;
			BookVo bvo = null;
			String num  = sc.nextLine();
			
			if(!num.equals(null)||"".equals(num)) {				
				if("q".equals(num)) {
					System.out.println("프로그램을 종료합니다");
					break;
				}				
				else if("10".equals(num)) {
					AuthorVo Newavo = new AuthorVo();
					System.out.println("저자추가를 선택하셨습니다.");
					System.out.println();
					System.out.println("작가명");
					Newavo.setAuthor_name(sc.nextLine());
					System.out.println("작가 정보");
					Newavo.setAuthor_desc(sc.nextLine());
					AuthorDao dao = new AuthorDaoImpl();
					dao.insert(Newavo);
					System.out.println("저자추가가 완료되었습니다.");
				}
				else if(!"10".equals(num)) {
					avo = selectID(alist,num);
				    blist = BgetList(avo);
					num = sc.nextLine();					
					if("0".equals(num)) {
						System.out.println("작가수정을 선택하셨습니다.");
						System.out.println();
						System.out.println("작가명:");
						avo.setAuthor_name(sc.nextLine());
						System.out.println("작가 정보");
						avo.setAuthor_desc(sc.nextLine());
						AuthorDao dao = new AuthorDaoImpl();
						dao.update(avo);
						System.out.println("작가 수정이 완료되었습니다.");												
					}											
					else if("4".equals(num)) {
						BookVo Newbvo = new BookVo();
						System.out.println("책추가를 선택하셨습니다");
						System.out.println();
						System.out.print("책제목:");
						Newbvo.setBook_title(sc.nextLine());
						System.out.print("출판사:");
						Newbvo.setBook_pubs(sc.nextLine());
						System.out.print("출판일:");
						Newbvo.setBook_pub(sc.nextLine());
						System.out.print("작가 아이디:");
						Newbvo.setAuthor_id(sc.nextInt());
						BookDao dao = new BookDaoImpl();
						dao.insert(Newbvo);
						System.out.println("책추가가 완료되었습니다.");
					}
					else  {						
						bvo = selectBook(blist,num);
						System.out.println();
						System.out.println("책수정을 선택하셨습니다.");
						System.out.println();
						System.out.print("책제목:");
						bvo.setBook_title(sc.nextLine());
						System.out.print("출판사:");
						bvo.setBook_pubs(sc.nextLine());
						System.out.print("출판일:");
						bvo.setBook_pub(sc.nextLine());						
						BookDao dao = new BookDaoImpl();
						//bvo.setAuthor_id(avo.getAuthor_id());	
						dao.update(bvo);
						System.out.println("책수정이 완료되었습니다.");
					}
				}
			}
		}
	}						
	private static BookVo selectBook(List<BookVo> list, String id) {
		BookVo viewbvo = null;
		for(BookVo bvo : list) {
			if(id.equals(String.valueOf(bvo.getBook_id()))) {
				viewbvo = bvo;
				System.out.print(bvo.getBook_title());
				System.out.print(" ");
				System.out.print(bvo.getBook_pubs());
				System.out.print(" ");
				System.out.print(bvo.getBook_pub());
				System.out.print(" ");
				//AuthorVo avo = new AuthorVo();
				//System.out.print(avo.getAuthor_name());
			}
		}
		return viewbvo;
	}




	private static List<BookVo> BgetList(AuthorVo avo) {
		BookDao dao = new BookDaoImpl();
		List<BookVo> list = dao.select(avo.getAuthor_id());
		System.out.print("0.작가수정");
		System.out.print(" ");
		for(BookVo bvo : list) {
			System.out.print(bvo.getBook_id());
			System.out.print(" ");
			System.out.print(bvo.getBook_title());
			System.out.print(" ");
			System.out.print(bvo.getBook_pubs());
			System.out.print(" ");
			System.out.print(bvo.getBook_pub());
			System.out.print(" ");
			
		}
		System.out.print("4.책추가");
		System.out.println();
		//System.out.println("작가수정은 0번 || 책 추가는 4번 ");
		return list;
	}

	private static AuthorVo selectID(List<AuthorVo> list, String id) {
		AuthorVo viewavo = null;
		for(AuthorVo avo : list) {
			if(id.equals(String.valueOf(avo.getAuthor_id()))) {
				viewavo = avo;
				System.out.print(avo.getAuthor_name());
				System.out.print(" ");
				System.out.println(avo.getAuthor_desc());
				System.out.print(" ");
				
			}
			
		}
		
		return viewavo;
	}

	private static List<AuthorVo> AgetList() {
		AuthorDao dao = new AuthorDaoImpl();
		List<AuthorVo> list = dao.select();
		//System.out.println();
		for(AuthorVo avo : list) {
			System.out.print(avo.getAuthor_id());
			System.out.print(" ");
			System.out.print(avo.getAuthor_name());
			System.out.print(" ");	
			
		}
		System.out.print("10.저자추가");
		System.out.print(" ");	
	    System.out.print("q.종료");
		return list;
	}

}
