package action;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.struts2.ServletActionContext;












import util.FTP;
import util.SaveImage;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import net.sf.json.util.CycleDetectionStrategy;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.JspDao;

public class JspAction extends ActionSupport{
	JspDao jspDao=new JspDao();
	private Properties properties;
	private FileInputStream inputFile;
	
	
@Override
	public String execute() throws Exception {
	int TA1 = 0;
	int TA2 = 0;
	int TA3 = 0;
	int TA4 = 0;
	int CA1 = 0;
	int CA2 = 0;
	int CA3 = 0;
	int FA1 = 0;
	int FA2 = 0;
	int IP = 0;
	int DA = 0;
	int PT = 0;
	int RS = 0;
	
	//计划量
	int C301_plan = 0;
	int S201_plan = 0;
	int V301_plan = 0;
	int V301s_plan = 0;
	int planCount = 0;
	//车系
	String audi_1="";
	String audi_2="";
	String audi_3="";
	String audi_4="";
	//完成量
	int finsh_1=0;
	int finsh_2=0;
	int finsh_3=0;
	int finsh_4=0;
	int finsh_5=0;
	//差异
	int diff_1=0;
	int diff_2=0;
	int diff_3=0;
	int diff_4=0;
	int diff_5=0;
	
	String JPH="0";
	int real_time=0;
	int shift=0;
	
	int atr_key=0;
	
	String FTT_1="0%";
	String FTT_2="0%";
	String FTT_3="0%";
	String FTT_4="0%";
	String FTT_5="0%";
	String str_count="100";
	String str1="";
	
	String start_time_t="";
	String end_time_t="";
	
	
	Date day=new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String time=df.format(day);
	
	List<Object> atrKey = new ArrayList<Object>();
	atrKey=jspDao.queryAtrKey();
	if(atrKey.size()>0){
		atr_key=Integer.parseInt(atrKey.get(0).toString());
	}
	
	List listShift=jspDao.queryShift();
	for(int i =0 ;i<listShift.size();i++){
		Object[] object = (Object[]) listShift.get(i);
		str_count = object[0].toString();
		start_time_t = object[1].toString();
		end_time_t = object[2].toString();
	}
	String number = "";
	shift=Integer.parseInt(str_count);
	if(shift==100){
		System.out.println("空白期");
		return "s";
	}else{
		
		if(str_count.equalsIgnoreCase("0")){
			number = "A班";
		}else if(str_count.equalsIgnoreCase("1")){
			number = "B班";
		}else{
			number = "";
		}
	
		List listPlanProduct=jspDao.queryChangeLineColor();
		for(int i =0 ;i<listPlanProduct.size();i++){
			Object[] object = (Object[]) listPlanProduct.get(i);
			if(object[0].toString().equals("TA 1")){
				if(TA1<Integer.parseInt(object[1].toString())){
					TA1 = Integer.parseInt(object[1].toString());
				}
			}else if(object[0].toString().equals("TA 2")){
				if(TA2<Integer.parseInt(object[1].toString())){
					TA2 = Integer.parseInt(object[1].toString());
				}
			}else if(object[0].toString().equals("TA 3")){
				if(TA3<Integer.parseInt(object[1].toString())){
					TA3 = Integer.parseInt(object[1].toString());
				}
			}else if(object[0].toString().equals("TA 4")){
				if(TA4<Integer.parseInt(object[1].toString())){
					TA4 = Integer.parseInt(object[1].toString());
				}
			}else if(object[0].toString().equals("CA 1")){
				if(CA1<Integer.parseInt(object[1].toString())){
					CA1 = Integer.parseInt(object[1].toString());
				}
			}else if(object[0].toString().equals("CA 2")){
				if(CA2<Integer.parseInt(object[1].toString())){
					CA2 = Integer.parseInt(object[1].toString());
				}
			}else if(object[0].toString().equals("CA 3")){
				if(CA3<Integer.parseInt(object[1].toString())){
					CA3 = Integer.parseInt(object[1].toString());
				}
			}else if(object[0].toString().equals("FA 1")){
				if(FA1<Integer.parseInt(object[1].toString())){
					FA1 = Integer.parseInt(object[1].toString());
				}
			}else if(object[0].toString().equals("FA 2")){
				if(FA2<Integer.parseInt(object[1].toString())){
					FA2 = Integer.parseInt(object[1].toString());
				}
			}else if(object[0].toString().equals("IP")){
				if(IP<Integer.parseInt(object[1].toString())){
					IP = Integer.parseInt(object[1].toString());
				}
			}else if(object[0].toString().equals("DA")){
				if(DA<Integer.parseInt(object[1].toString())){
					DA = Integer.parseInt(object[1].toString());
				}
			}else if(object[0].toString().equals("PT")){
				if(PT<Integer.parseInt(object[1].toString())){
					PT = Integer.parseInt(object[1].toString());
				}
			}else if(object[0].toString().equals("RS")){
				if(RS<Integer.parseInt(object[1].toString())){
					RS = Integer.parseInt(object[1].toString());
				}
			}
		}
		
		//车系和计划量
		List<String> s = jspDao.queryPpProductPlan(shift,start_time_t);
		if(s.size()>0){
			str1=s.get(0);
			if(!(str1==null)){
		System.out.println("s.size()="+s.size());
		System.out.println("str1="+str1);
	//	String str1="S301:200/V201:200"; //数据库取
		String[] aa = str1.split("/");
		List<String> ss=new ArrayList<String>();
		List<String> chexing = new ArrayList<String>();
		List<String> jihualiang = new ArrayList<String>();
		for(int i = 0; i<aa.length;i++) {
			String str=aa[i];
			ss.add(str);
		}
		for(int i=0;i<ss.size();i++) {
			String[] cc = ss.get(i).split(":");
			chexing.add(cc[0]);
			jihualiang.add(cc[1]);
		}
		if(chexing.size()>0){
			audi_1=chexing.get(0);
			C301_plan=Integer.parseInt(jihualiang.get(0));
			}
			if(chexing.size()>1){
				audi_2=chexing.get(1);
				S201_plan=Integer.parseInt(jihualiang.get(1));
		
			}
			if(chexing.size()>2){
				audi_3=chexing.get(2);
				V301_plan=Integer.parseInt(jihualiang.get(2));
			}
				if(chexing.size()>3){
				audi_4=chexing.get(3);
				V301s_plan=Integer.parseInt(jihualiang.get(3));
		
				}
			
			
			}
		}
		planCount=C301_plan+S201_plan+V301_plan+V301s_plan;
		//完成量
		if(start_time_t == "" || end_time_t == ""){
			System.out.println("查询班次的开班时间和结束时间出现了问题");
		}else{
			finsh_1=jspDao.queryfinshFromMes(audi_1, Integer.parseInt(str_count),start_time_t,end_time_t);
			finsh_2=jspDao.queryfinshFromMes(audi_2, Integer.parseInt(str_count),start_time_t,end_time_t);
			finsh_3=jspDao.queryfinshFromMes(audi_3, Integer.parseInt(str_count),start_time_t,end_time_t);
			finsh_4=jspDao.queryfinshFromMes(audi_4, Integer.parseInt(str_count),start_time_t,end_time_t);
			finsh_5=finsh_1+finsh_2+finsh_3+finsh_4;
		}
		//差异
		diff_1=finsh_1-C301_plan;
		diff_2=finsh_2-S201_plan;
		diff_3=finsh_3-V301_plan;
		diff_4=finsh_4-V301s_plan;
		diff_5=diff_1+diff_2+diff_3+diff_4;
		
		//JPH
		real_time=jspDao.queryRestTime(atr_key, shift,start_time_t);
		if(real_time>0){
			
			DecimalFormat df_1 = new DecimalFormat("0.0");
			System.out.println("real time  = " + real_time);
			System.out.println("finshed = " + finsh_5);
			JPH=df_1.format((finsh_5/(real_time/(60.0))));
		}
		//FTT
		List<Object> ft_1=jspDao.queryFtt(audi_1,shift);
		if(ft_1.size()>0){
			FTT_1=ft_1.get(0).toString()+"%";
		}
		List<Object> ft_2=jspDao.queryFtt(audi_2,shift);
		if(ft_2.size()>0){
			FTT_2=ft_2.get(0).toString()+"%";
		}
		List<Object> ft_3=jspDao.queryFtt(audi_3,shift);
		if(ft_3.size()>0){
			FTT_3=ft_3.get(0).toString()+"%";
		}
		List<Object> ft_4=jspDao.queryFtt(audi_4,shift);
		if(ft_4.size()>0){
			FTT_4=ft_4.get(0).toString()+"%";
		}
		List<Object> ft_5=jspDao.queryFtt_1();
		if(ft_5.size()>0){
			FTT_5=ft_5.get(0).toString()+"%";
		}
	}
	HttpServletRequest request=ServletActionContext.getRequest();
	ActionContext actionContext = ActionContext.getContext(); 
	actionContext.put("number", number);
	actionContext.put("TA1", TA1);
	actionContext.put("TA2", TA2);
	actionContext.put("TA3", TA3);
	actionContext.put("TA4", TA4);
	actionContext.put("CA1", CA1);
	actionContext.put("CA2", CA2);
	actionContext.put("CA3", CA3);
	actionContext.put("FA1", FA1);
	actionContext.put("FA2", FA2);
	actionContext.put("IP", IP);
	actionContext.put("DA", DA);
	actionContext.put("PT", PT);
	actionContext.put("RS", RS);
	
	actionContext.put("audi_1", audi_1);
	actionContext.put("audi_2", audi_2);
	actionContext.put("audi_3", audi_3);
	actionContext.put("audi_4",audi_4);
	
	actionContext.put("C301_plan", C301_plan);
	actionContext.put("S201_plan", S201_plan);
	actionContext.put("V301_plan", V301_plan);
	actionContext.put("V301s_plan",V301s_plan);
	actionContext.put("planCount", planCount);
	

	
	actionContext.put("finsh_1", finsh_1);
	actionContext.put("finsh_2", finsh_2);
	actionContext.put("finsh_3", finsh_3);
	actionContext.put("finsh_4", finsh_4);
	actionContext.put("finsh_5", finsh_5);
	
	actionContext.put("diff_1",diff_1);
	actionContext.put("diff_2",diff_2);
	actionContext.put("diff_3",diff_3);
	actionContext.put("diff_4",diff_4);
	actionContext.put("diff_5", diff_5);
	actionContext.put("JPH", JPH);
	
	actionContext.put("time", time);
	
	actionContext.put("FTT_1", FTT_1);
	actionContext.put("FTT_2", FTT_2);
	actionContext.put("FTT_3", FTT_3);
	actionContext.put("FTT_4", FTT_4);
	actionContext.put("FTT_5", FTT_5);
//	return "s";
	return SUCCESS;
	}

}
