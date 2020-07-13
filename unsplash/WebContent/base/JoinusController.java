package com.sist.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sist.dao.MemberDAO;
import com.sist.dao.NoticeDAO;
import com.sist.util.CookieManager;
import com.sist.vo.Member;

@Controller
@RequestMapping("/joinus/*")
public class JoinusController {
	private SqlSession sqlSession;
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	/*private MemberDAO mdao;
	@Autowired
	public void setMdao(MemberDAO mdao) {
		this.mdao = mdao;
	}*/

	@RequestMapping(value={"login.do"}, method=RequestMethod.GET)
	public String login(HttpServletRequest request, Model model){
		System.out.println("annotation-LoginController 작동중");
		ModelAndView mv = new ModelAndView();

		Cookie[] cks = request.getCookies();
		String cookieMid = CookieManager.getCookie(cks, "mid");

		if(cookieMid!=null && !cookieMid.equals("")){
			System.out.println("cookieMid="+cookieMid);
			//request.setAttribute("cookieMid", cookieMid);
			model.addAttribute("cookieMid", cookieMid);
		}

		String error=null;
		if(request.getParameter("error") != null && !request.getParameter("error").equals("")){
			if(request.getParameter("error").equals("IDx")){
				error = "존재하지 않는 아이디입니다.";
			}else{
				error = "비밀번호가 일치하지 않습니다.";
			}
			model.addAttribute("error", error);
		}
		//mv.setViewName("login.jsp");
		return "login.jsp";
	}

	@RequestMapping(value={"login.do"}, method=RequestMethod.POST)
	public String login(String mid, String pwd, String checkBoxMid, HttpServletRequest request, HttpServletResponse response){
		System.out.println("annotation-loginProc 컨트롤러가 작동중");
		System.out.println("mid="+mid);
		//MemberDAO dao= new MemberDAO();
		MemberDAO myBatisMDAO = sqlSession.getMapper(MemberDAO.class);
		Member m = myBatisMDAO.getMember(mid);

		if(m==null){
			return "redirect:login.do?error=IDx";
		}else if(!m.getPwd().equals(pwd)){
			return "redirect:login.do?error=pwdX";
		}else{
			request.getSession().setAttribute("mid", m.getMid());
			Cookie ck = null;
			if(checkBoxMid != null && !checkBoxMid.equals("")){
				ck = new Cookie("mid", mid);
				ck.setMaxAge(60*60*24*30);
				response.addCookie(ck);
				System.out.println("쿠키생성");
			}else{
				ck = new Cookie("mid", null);
				ck.setMaxAge(0);
				response.addCookie(ck);
				System.out.println("쿠키삭제");
			}

			String returnURL = (String)request.getSession().getAttribute("returnURL");
			if(returnURL!=null && !returnURL.equals("")){
				return "redirect:"+returnURL;
			}

			return "redirect:welcomelogin.do?mid="+m.getMid();
		}
	}

	@RequestMapping(value={"join.do"}, method=RequestMethod.GET)
	public String join(){
		System.out.println("annotation-JoinController 실행");
		//ModelAndView mv = new ModelAndView();
		//mv.setViewName("join.jsp");
		return "join.jsp";
	}
	
	@RequestMapping(value={"join.do"}, method=RequestMethod.POST)
	public String join(Member m){
		System.out.println("annotation-JoinProcController 실행");

		MemberDAO myBatisMDAO = sqlSession.getMapper(MemberDAO.class);
		int af = myBatisMDAO.addMember(m);
		
		if(af == 1){
			return "redirect:login.do";
		}else{
			System.out.println("회원 가입중 오류가 발생하였습니다.");
			return null;
		}
	}


	@RequestMapping(value={"memberUpdate.do"}, method=RequestMethod.GET)
	public String memberUpdate(HttpServletRequest request, Model model){
		String mid = request.getParameter("mid");
		MemberDAO myBatisMDAO = sqlSession.getMapper(MemberDAO.class);
		Member m = myBatisMDAO.getMember(mid);
		System.out.println("m="+m);

		String year = m.getBirthday().substring(0, 4);
		String month = m.getBirthday().substring(5, 7);
		String day = m.getBirthday().substring(8);
		
		model.addAttribute("m", m);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		return "memberUpdate.jsp";
	}
	
	@RequestMapping(value={"memberUpdate.do"}, method=RequestMethod.POST)
	public String memberUpdate(Member m, HttpServletRequest request){
		String mid = (String)request.getSession().getAttribute("mid");
		m.setMid(mid);
		
		MemberDAO myBatisMDAO = sqlSession.getMapper(MemberDAO.class);
		int af = myBatisMDAO.updateMember(m);
		//ModelAndView mv = new ModelAndView();
		
		System.out.println("af="+af);
		if (af == 1) {
			//mv.setViewName("redirect:memberUpdate.do?mid="+mid);
			return "redirect:memberUpdate.do?mid="+mid;
		} else {
			System.out.println("회원정보 수정중 오류가 발생하였습니다.");
			return null;
		}
	}
	
	@RequestMapping("welcomelogin.do")
	public String welcomelogin(String mid, Model model){
		
		model.addAttribute("mid", mid);
		return "welcomelogin.jsp";
	}
	
	@RequestMapping("memberDel.do")
	public String memberDel(HttpServletRequest request, HttpServletResponse response){
		Cookie coo = null;
		String mid = (String)request.getSession().getAttribute("mid");

		//MemberDAO dao = new MemberDAO();
		MemberDAO myBatisMDAO = sqlSession.getMapper(MemberDAO.class);
		int af = myBatisMDAO.delMember(mid);

		if(af == 1){
			coo = new Cookie("mid", null);
			coo.setMaxAge(0);
			response.addCookie(coo);
			System.out.println("쿠키삭제");
			return "redirect:../index.jsp";
		}else{
			System.out.println("회원 삭제중 오류발생");
			return null;
		}
	}

	@RequestMapping("idcheck.do")
	public String idcheck(String mid, Model model){
		//String mid = request.getParameter("mid");
		System.out.println(mid);
		//MemberDAO dao = new MemberDAO();
		MemberDAO myBatisMDAO = sqlSession.getMapper(MemberDAO.class);
		Member m = myBatisMDAO.getMember(mid);
		System.out.println(m);
		if(m==null){
			//out.write("1");
			model.addAttribute("idOK", "1");
		}else{
			//out.write("0");
			model.addAttribute("idOK", "0");
		}
		return "idcheck.jsp";
	}







}
