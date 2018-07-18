package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.JspDao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


public class Top10Action extends ActionSupport{
	
	JspDao jspDao=new JspDao();
@Override
	public String execute() throws Exception {
	 List listQuityQuestion=jspDao.queryQuityQuestion();
	 Date day=new Date();
	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 String nowTime=df.format(day);
     //拼接json格式字符串
     List<Map> dataList = new ArrayList<Map>();
     //如果得到的数据小于10
     if(listQuityQuestion.size()>10){
    	 for(int i =0 ;i<10;i++){
    		 //判断空
  			Object[] object = (Object[]) listQuityQuestion.get(i);
  			Map dataMap = new HashMap();
  			if(object[0]!=null){
  				dataMap.put("MQSC_ICC", object[0].toString());
  			}else{
  				dataMap.put("MQSC_ICC", " ");
  			}
  			if(object[1]!=null){
  				dataMap.put("MQSC_CMCODE", object[1].toString());
  			}else{
  				dataMap.put("MQSC_CMCODE", " ");
  			}
  			if(object[2]!=null){
  				dataMap.put("NUM", object[2].toString());
  			}else{
  				dataMap.put("NUM"," ");
  			}
  			dataList.add(dataMap);
  			}
     }else{
    	 for(int i =0 ;i<listQuityQuestion.size();i++){
    		 Object[] object = (Object[]) listQuityQuestion.get(i);
   			Map dataMap = new HashMap();
   			if(object[0]!=null){
   				dataMap.put("MQSC_ICC", object[0].toString());
   			}else{
   				dataMap.put("MQSC_ICC", " ");
   			}
   			if(object[1]!=null){
   				dataMap.put("MQSC_CMCODE", object[1].toString());
   			}else{
   				dataMap.put("MQSC_CMCODE", " ");
   			}
   			if(object[2]!=null){
   				dataMap.put("NUM", object[2].toString());
   			}else{
   				dataMap.put("NUM"," ");
   			}
   			dataList.add(dataMap);
   			}
     }
     	for(int i = 0; i < dataList.size(); i++){
     		System.out.println("质量问题：" + dataList.get(i).toString());
     	}
	    HttpServletRequest request=ServletActionContext.getRequest();
		ActionContext actionContext = ActionContext.getContext(); 
		actionContext.put("dataList", dataList);
		actionContext.put("nowTime", nowTime);
		return "Top10";	
	}
}
