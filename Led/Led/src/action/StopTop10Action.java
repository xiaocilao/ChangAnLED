package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.JspDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


public class StopTop10Action extends ActionSupport{
	
	JspDao jspDao=new JspDao();
@Override
	public String execute() throws Exception {
		 //获取当前时间
		Date day=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime=df.format(day);
		//当班停线top问题
         List listTopStopLine=jspDao.queryTopStopLine();
         //拼接json格式字符串
         List<Map> dataList = new ArrayList<Map>();
         //如果得到的数据小于10
         if(listTopStopLine.size()>10){
        	 for(int i =0 ;i<10;i++){
        		 //判断空
      			Object[] object = (Object[]) listTopStopLine.get(i);
      			Map dataMap = new HashMap();
      			if(object[0]!=null){
      				dataMap.put("PLINE_NO", object[0].toString());
      			}else{
      				dataMap.put("PLINE_NO", " ");
      			}
      			if(object[1]!=null){
      				dataMap.put("STATION_NO", object[1].toString());
      			}else{
      				dataMap.put("STATION_NO", " ");
      			}
      			if(object[2]!=null){
      				dataMap.put("REMARK", object[2].toString());
      			}else{
      				dataMap.put("REMARK"," ");
      			}
      			if(object[3]!=null){
      				dataMap.put("ID", object[3].toString());
      			}else{
      				dataMap.put("ID", " ");
      			}
      			if(object[4]!=null){
      				dataMap.put("TIME", object[4].toString());
      			}else{
      				dataMap.put("TIME", " ");
      			}
      			dataList.add(dataMap);
      			}
         }else{
        	 for(int i =0 ;i<listTopStopLine.size();i++){
        		 Object[] object = (Object[]) listTopStopLine.get(i);
        		 Map dataMap = new HashMap();
       			if(object[0]!=null){
       				dataMap.put("PLINE_NO", object[0].toString());
       			}else{
       				dataMap.put("PLINE_NO", " ");
       			}
       			if(object[1]!=null){
       				dataMap.put("STATION_NO", object[1].toString());
       			}else{
       				dataMap.put("STATION_NO", " ");
       			}
       			if(object[2]!=null){
       				dataMap.put("REMARK", object[2].toString());
       			}else{
       				dataMap.put("REMARK"," ");
       			}
       			if(object[3]!=null){
       				dataMap.put("ID", object[3].toString());
       			}else{
       				dataMap.put("ID", " ");
       			}
       			if(object[4]!=null){
       				dataMap.put("TIME", object[4].toString());
       			}else{
       				dataMap.put("TIME", " ");
       			}
       			dataList.add(dataMap);
       			}
         }
 		HttpServletRequest request=ServletActionContext.getRequest();
 		ActionContext actionContext = ActionContext.getContext(); 
 		actionContext.put("dataList", dataList);
 		actionContext.put("nowTime", nowTime);
	return "StopTop10";	
	}
}
