package top.aisdanny.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.alibaba.fastjson.JSON;

import top.aisdanny.dao.SelectVoteNumber;

/**
 * Servlet implementation class voteNumber
 */
@WebServlet("/voteNumber")
public class voteNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public voteNumber() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
		SqlSession ss = ssf.openSession();//类似JDBC中的预处理对象
		List<HashMap<String,Object>> okList = ss.selectList("top.aisdanny.selectOkNumber");
		List<HashMap<String,Object>> noList = ss.selectList("top.aisdanny.selectNoNumber");
		List<HashMap<String,Object>> defList = ss.selectList("top.aisdanny.selectDefNumber");
		
		SelectVoteNumber  number= new SelectVoteNumber(okList.size(),noList.size(),defList.size());
		response.getWriter().append(JSON.toJSONString(number));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
