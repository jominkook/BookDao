package com.kosta.jdbcdao.jominkook;


import java.util.List;
import java.util.Scanner;



public class BookManger {

	public static void main(String[] args) {
				
		while(true) {
			System.out.println("�۰���ȣ�� �Է��ϼ���");
			List<AuthorVo> alist = AgetList();
			AuthorVo avo = null;
			Scanner sc = new Scanner(System.in);
			List<BookVo> blist = null;
			BookVo bvo = null;
			String num  = sc.nextLine();
			
			if(!num.equals(null)||"".equals(num)) {				
				if("q".equals(num)) {
					System.out.println("���α׷��� �����մϴ�");
					break;
				}				
				else if("10".equals(num)) {
					AuthorVo Newavo = new AuthorVo();
					System.out.println("�����߰��� �����ϼ̽��ϴ�.");
					System.out.println();
					System.out.println("�۰���");
					Newavo.setAuthor_name(sc.nextLine());
					System.out.println("�۰� ����");
					Newavo.setAuthor_desc(sc.nextLine());
					AuthorDao dao = new AuthorDaoImpl();
					dao.insert(Newavo);
					System.out.println("�����߰��� �Ϸ�Ǿ����ϴ�.");
				}
				else if(!"10".equals(num)) {
					avo = selectID(alist,num);
				    blist = BgetList(avo);
					num = sc.nextLine();					
					if("0".equals(num)) {
						System.out.println("�۰������� �����ϼ̽��ϴ�.");
						System.out.println();
						System.out.println("�۰���:");
						avo.setAuthor_name(sc.nextLine());
						System.out.println("�۰� ����");
						avo.setAuthor_desc(sc.nextLine());
						AuthorDao dao = new AuthorDaoImpl();
						dao.update(avo);
						System.out.println("�۰� ������ �Ϸ�Ǿ����ϴ�.");												
					}											
					else if("4".equals(num)) {
						BookVo Newbvo = new BookVo();
						System.out.println("å�߰��� �����ϼ̽��ϴ�");
						System.out.println();
						System.out.print("å����:");
						Newbvo.setBook_title(sc.nextLine());
						System.out.print("���ǻ�:");
						Newbvo.setBook_pubs(sc.nextLine());
						System.out.print("������:");
						Newbvo.setBook_pub(sc.nextLine());
						System.out.print("�۰� ���̵�:");
						Newbvo.setAuthor_id(sc.nextInt());
						BookDao dao = new BookDaoImpl();
						dao.insert(Newbvo);
						System.out.println("å�߰��� �Ϸ�Ǿ����ϴ�.");
					}
					else  {						
						bvo = selectBook(blist,num);
						System.out.println();
						System.out.println("å������ �����ϼ̽��ϴ�.");
						System.out.println();
						System.out.print("å����:");
						bvo.setBook_title(sc.nextLine());
						System.out.print("���ǻ�:");
						bvo.setBook_pubs(sc.nextLine());
						System.out.print("������:");
						bvo.setBook_pub(sc.nextLine());						
						BookDao dao = new BookDaoImpl();
						//bvo.setAuthor_id(avo.getAuthor_id());	
						dao.update(bvo);
						System.out.println("å������ �Ϸ�Ǿ����ϴ�.");
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
		System.out.print("0.�۰�����");
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
		System.out.print("4.å�߰�");
		System.out.println();
		//System.out.println("�۰������� 0�� || å �߰��� 4�� ");
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
		System.out.print("10.�����߰�");
		System.out.print(" ");	
	    System.out.print("q.����");
		return list;
	}

}
