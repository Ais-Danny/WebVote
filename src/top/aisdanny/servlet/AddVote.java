package top.aisdanny.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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


@WebServlet("/add")
public class AddVote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AddVote() {
        super();        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String roomId = request.getParameter("roomId");
		String name = request.getParameter("name");
		String choice = request.getParameter("choice");
		String telephone=request.getParameter("telephone");
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=simpleDateFormat.format(new Date());
		
		if(roomId.equals("")||name.equals("")||telephone.equals("")||choice.equals("")) {
			response.getWriter().append("�뽫��Ϣ��д����");
			return;
		}
		
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
		SqlSession ss = ssf.openSession();//����JDBC�е�Ԥ�������
		
		HashMap<String, Object> selectMap=new HashMap<String,Object>();
		selectMap.put("roomId", roomId);
		List<HashMap<String,Object>> selectRoom = ss.selectList("top.aisdanny.selectRoomId", selectMap);
		if(selectRoom.size()!=0) {
			response.getWriter().append("�˷�����Ѳ����ͶƱ����л���룡");
			return;
		}
		
		HashMap<String,Object> map = new HashMap<String, Object>();//�������ĳһ��
		map.put("roomId", roomId);
		map.put("name", name);
		map.put("telephone", telephone);
		map.put("time",time);
		map.put("choice", choice);
		int result = ss.insert("top.aisdanny.add", map);
		if(result>=1) {
			ss.commit();
			response.getWriter().append("ͶƱ�ɹ�");
		}else {
			ss.rollback();
			response.getWriter().append("500 Error!");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}
}
