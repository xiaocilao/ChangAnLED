package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
























import org.apache.commons.lang.StringUtils;
import org.hibernate.CacheMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.inject.util.Strings;

import entity.DsPpEntity;

public class JspDao {  //finsh
	
	FileOutputStream fop = null; 
	Date date;
	 
	File file; 
	String file_name;
	public void log(String context){
		try {  
			
			date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf_file = new SimpleDateFormat("yyyy-MM-dd");
			String str= sdf.format(date.getTime())+" ==>"+context;
			file_name="F:/ledlog/led_log"+sdf_file.format(date.getTime())+".txt";
            file = new File(file_name);  
            fop = new FileOutputStream(file,true);  
            if (!file.exists()) {  
                file.createNewFile();  
            }  
            
            byte[] contentInBytes = str.getBytes();  
            fop.write(contentInBytes);  
            fop.write("\r\n".getBytes());
            fop.flush();  
            fop.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (fop != null) {  
                    fop.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
	}
	public List queryShift(){
		Session session=null;
		List list=null;
		try{
			session=HibernateSessionFactory.getSession();
			session.flush();
			list=session.createSQLQuery("select SHIFT_NAME_S,TO_CHAR(START_TIME_T,'yyyy-mm-dd hh24:mi:ss'),TO_CHAR(END_TIME_T,'yyyy-mm-dd hh24:mi:ss') from DS_WORK_CALENDAR WHERE systimestamp>=START_TIME_T and systimestamp< END_TIME_T").list();
			log("当前班次 ： select SHIFT_NAME_S from DS_WORK_CALENDAR WHERE systimestamp>=START_TIME_T and systimestamp< END_TIME_T");
		}catch(HibernateException he){
			he.printStackTrace();  //或者throw he;
		}finally{
			try{//关闭session报异常几乎不会，此处的try catch可省略，但不建议。
				if(session!=null){
					session.close();
				}
			}catch(HibernateException he){
				he.printStackTrace();
			}
		}
		return list;
	}
	
	//atr_key
	public List<Object> queryAtrKey(){  //finsh
		Session session=null;
		List<Object> list=null;
		try{
			session=HibernateSessionFactory.getSession();
			session.flush();
			list=session.createSQLQuery("select ATR_KEY from DS_WORK_CALENDAR WHERE systimestamp>=START_TIME_T and systimestamp< END_TIME_T").list();
			log("查询 ATR_KEY ：select ATR_KEY from DS_WORK_CALENDAR WHERE systimestamp>=START_TIME_T and systimestamp< END_TIME_T");
		}catch(HibernateException he){
			he.printStackTrace();  //或者throw he;
		}finally{
			try{//关闭session报异常几乎不会，此处的try catch可省略，但不建议。
				if(session!=null){
					session.close();
				}
			}catch(HibernateException he){
				he.printStackTrace();
			}
		}
		return list;
	}
	
	public List<String> queryPpProductPlan(int banci,String start_time_t){  //TODO
		Session session=null;
		List<String> list=null;
		try{
			session=HibernateSessionFactory.getSession();
			session.flush();
			String sql="";
			sql="select PRODUCT_SERIES_S from DS_PP_PRODUCTPLAN WHERE PRODUCT_DATE_T=trunc(to_date('"+start_time_t+"','yyyy-mm-dd hh24:mi:ss'),'dd') and SHIFT_I="+banci+"";
			log("查询计划量："+sql);
//			list=session.createSQLQuery("select SHIFT_COUNT_I,PRODUCT_SERIES_S from DS_PP_PRODUCTPLAN WHERE PRODUCT_DATE_T=trunc（sysdate,'dd')").addScalar("SHIFT_COUNT_I", Hibernate.INTEGER).addScalar("PRODUCT_SERIES_S", Hibernate.STRING).list();
			list=session.createSQLQuery(sql).addScalar("PRODUCT_SERIES_S", Hibernate.STRING).list();
		}catch(HibernateException he){
			he.printStackTrace();  //或者throw he;
		}finally{
			try{//关闭session报异常几乎不会，此处的try catch可省略，但不建议。
				if(session!=null){
					session.close();
				}
			}catch(HibernateException he){
				he.printStackTrace();
			}
		}
		return list;
	}
	
	
	//change color
	public List queryChangeLineColor(){  //finsh
		Session session=null;
		List list=null;
		try{
			session=HibernateSessionFactory.getSession();
			session.flush();
			String sql ="select PLINE_NO,EVENT_TYPE,TO_CHAR(END_TIME,'YYY-MM-DD hh24:mi:ss') from AN_RESULT where (sysdate-END_TIME)<0";
			list= session.createSQLQuery("select PLINE_NO,EVENT_TYPE from AN_RESULT where END_TIME is null").addScalar("PLINE_NO", Hibernate.STRING).addScalar("EVENT_TYPE", Hibernate.STRING).list();
			
		}catch(HibernateException he){
			he.printStackTrace();  //或者throw he;
		}finally{
			try{//关闭session报异常几乎不会，此处的try catch可省略，但不建议。
				if(session!=null){
					session.close();
				}
			}catch(HibernateException he){
				he.printStackTrace();
			}
		}
		return list;
	}
	

	//mes 完成量
	public Integer queryfinshFromMes(String chexing,int banci,String start_time_t,String end_time_t){
		Session session=null;
		Integer c=0;
		Integer bnci_1=banci+1;
		try{
			session=HibernateSessionFactoryMes.getSession();
			session.flush();
			String sql="";
			sql = "select count(*) from CAR_PRC_SEQ_V,CAR_VCL_BRIEF_V where CAR_PRC_SEQ_V.VID=CAR_VCL_BRIEF_V.VCL_ID and CAR_PRC_SEQ_V.SITES_CODE='2A188' and CAR_VCL_BRIEF_V.VCL_SER='"+chexing+"' and CAR_PRC_SEQ_V.CREATION_DATE>=to_date('"+start_time_t+"','yyyy-mm-dd hh24:mi:ss') and CAR_PRC_SEQ_V.CREATION_DATE<=to_date('"+end_time_t+"','yyyy-mm-dd hh24:mi:ss')";
			log("完成量:"+sql);
			Object count= (Object)session.createSQLQuery(sql).uniqueResult();
			c=Integer.parseInt(count.toString());
			
		}catch(HibernateException he){
			he.printStackTrace();  //或者throw he;
		}finally{
			try{//关闭session报异常几乎不会，此处的try catch可省略，但不建议。
				if(session!=null){
					session.close();
				}
			}catch(HibernateException he){
				he.printStackTrace();
			}
		}
		return c;
	}
	
	//mes 休息时间/实际生产时间
	public Integer queryRestTime(int atrKey,int banci,String start_time_t){
		Session session=null;
		Integer c=0;
		Integer diff=0;
		String sql_now_time="";
		List<Integer> last_time=new ArrayList<Integer>();
		int time_diff=0;
		Date date=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now_time=df.format(date);
		List list=null;
		List list_1=null;
		List<String> list_start_time=null;
		List<String> list_end_time=null;
		try{
			session=HibernateSessionFactory.getSession();
			session.flush();
			list_start_time = new ArrayList<String>();
			list_end_time = new ArrayList<String>();
			//班次休息开始时间
			String start_time = "select TO_CHAR(START_TIME_T,'yyyy-mm-dd hh24:mi:ss') from DS_WORK_TIME_SCALE where WC_KEY_I="+atrKey+" and TIME_SCALE_TYPE_I=1";
			list=session.createSQLQuery(start_time).list();
			for(int i =0 ;i<list.size();i++){
				String object = list.get(i).toString();
				System.out.println("====休息开始时间========"+object);
				list_start_time.add(object);
			}
			//休息结束时间
			String end_time="select TO_CHAR(END_TIME_T,'yyyy-mm-dd hh24:mi:ss') from DS_WORK_TIME_SCALE where WC_KEY_I="+atrKey+" and TIME_SCALE_TYPE_I=1";
			list_1=session.createSQLQuery(end_time).list();
			for(int i =0 ;i<list_1.size();i++){
				String object =  list_1.get(i).toString();
				System.out.println("====休息结束时间========"+object);
				list_end_time.add(object);
			}
			log("当前班次休息开始时间:"+start_time);
			log("当前班次休息结束时间:"+end_time);
//			list_start=session.createSQLQuery(sql_start).addScalar("START_TIME_T",Hibernate.TIME).list();
//			list_end= session.createSQLQuery(sql_end).addScalar("END_TIME_T",Hibernate.TIME).list();
			
			
			for(int i =0;i<list_start_time.size();i++){
				//当前时间大于休息结束时间
				String sql ="SELECT ceil((TO_DATE('"+list_end_time.get(i)+"','yyyy-mm-dd hh24:mi:ss')-TO_DATE('"+list_start_time.get(i)+"','yyyy-mm-dd hh24:mi:ss'))*24*60) FROM dual";
				//当前时间小于休息结束时间
				String sqlRestTime = "SELECT ceil((TO_DATE('"+now_time+"','yyyy-mm-dd hh24:mi:ss')-TO_DATE('"+list_start_time.get(i)+"','yyyy-mm-dd hh24:mi:ss'))*24*60) FROM dual";
				//判断当前时间
				try {
					Date nowTime = new Date();
					long nowTime1=nowTime.getTime();
					long restTimeEnd = df.parse(list_end_time.get(i)).getTime();
					if(nowTime1-restTimeEnd>0){
						Object count= (Object)session.createSQLQuery(sql).uniqueResult();
						c=Integer.parseInt(count.toString());
						last_time.add(c);
					}else{
						Object count= (Object)session.createSQLQuery(sqlRestTime).uniqueResult();
						c=Integer.parseInt(count.toString());
						last_time.add(c);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				System.out.println("last_time"+c);
			}

			System.out.println("start_timt_t="+start_time_t);
			System.out.println("now_time="+now_time);
				sql_now_time ="select ceil((To_date('"+now_time+"','yyyy-mm-dd hh24:mi:ss')-to_date('"+start_time_t+"','yyyy-mm-dd hh24:mi:ss'))*24*60) from dual";
			Object count_now= (Object)session.createSQLQuery(sql_now_time).uniqueResult();
			time_diff=Integer.parseInt(count_now.toString());
			System.out.println("time_diff="+time_diff);
			//如果當前時間大於休息結束時間，才能jian减
			for(int i =0;i<list_start_time.size();i++){
					diff = time_diff-last_time.get(i);
					time_diff=diff;
			}
			System.out.println("===diff========"+diff);
		}catch(HibernateException he){
			he.printStackTrace();  //或者throw he;
		}finally{
			try{//关闭session报异常几乎不会，此处的try catch可省略，但不建议。
				if(session!=null){
					session.close();
				}
			}catch(HibernateException he){
				he.printStackTrace();
			}
		}
		return diff;
	}
	
	//FTT
	public List<Object> queryFtt(String carSeries,int shift){
		Session session=null;
		List<Object> list=null;
		try{
			session=HibernateSessionFactoryMqs.getSession();
			session.flush();
			String sql="SELECT FTT FROM mqs.V_AREA_CARS_INTIME_FTT where ACT_SHIFT="+shift+" and MQSC_CARSERIES='"+carSeries+"'";	
			log("FTT:"+sql);
			list=session.createSQLQuery(sql).list();
			
//			log("FTT:"+sql);
//			//			String sql = "select * from MQS";
//			list=session.createSQLQuery(sql).list();
		}catch(HibernateException he){
			he.printStackTrace();  //或者throw he;
		}finally{
			try{//关闭session报异常几乎不会，此处的try catch可省略，但不建议。
				if(session!=null){
					session.close();
				}
			}catch(HibernateException he){
				he.printStackTrace();
			}
		}
		return list;
	}
	//Ftt_1
	public List<Object> queryFtt_1(){
		Session session=null;
		List<Object> list=null;
		try{
			session=HibernateSessionFactoryMqs.getSession();
			session.flush();
			String sql="SELECT FTT FROM mqs.V_AREA_BCTOTAL_INTIME_FTT";
			log("FTT不分班次:"+sql);
			list=session.createSQLQuery(sql).list();
		}catch(HibernateException he){
			he.printStackTrace();  //或者throw he;
		}finally{
			try{//关闭session报异常几乎不会，此处的try catch可省略，但不建议。
				if(session!=null){
					session.close();
				}
			}catch(HibernateException he){
				he.printStackTrace();
			}
		}
		return list;
	}
	//当班停线top问题
		public List queryTopStopLine(){  
			Session session=null;
			List list=null;
			Date day=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String today=df.format(day);
			try{
				session=HibernateSessionFactory.getSession();
				session.flush();
				String sql ="select PLINE_NO,STATION_NO,REMARK,ID,round((END_TIME-START_TIME)*24*60,2) as time "
						+ "from AN_RESULT where EVENT_TYPE=2 "
						+ "and END_TIME is not null and CATEGORY_CODE not LIKE 'T01' "
						+ "and IS_DETAL='1' "
						+ "and PP_DATE=TO_DATE('"+today+"','yyyy-mm-dd') ORDER BY time";
				log("停线top10问题:"+sql);
				list= session.createSQLQuery(sql).addScalar("PLINE_NO", Hibernate.STRING).addScalar("STATION_NO", Hibernate.STRING).addScalar("REMARK", Hibernate.STRING).addScalar("ID", Hibernate.STRING).addScalar("time", Hibernate.STRING).list();
			}catch(HibernateException he){
				he.printStackTrace();  //或者throw he;
			}finally{
				try{//关闭session报异常几乎不会，此处的try catch可省略，但不建议。
					if(session!=null){
						session.close();
					}
				}catch(HibernateException he){
					he.printStackTrace();
				}
			}
			return list;
		}
		//当班质量问题
		public List queryQuityQuestion(){  
			Session session=null;
			List list=null;
			try{
				session=HibernateSessionFactoryMqsAdd.getSession();
				session.flush();
				String sql ="select MQSC_ICC,MQSC_CMCODE,NUM "
						+ "from V_AREA_TOP where MQSA_CODE='A10' ORDER BY NUM DESC ";
				log("当班质量问题:"+sql);
				list= session.createSQLQuery(sql).addScalar("MQSC_ICC", Hibernate.STRING).addScalar("MQSC_CMCODE", Hibernate.STRING).addScalar("NUM", Hibernate.STRING).list();
			}catch(HibernateException he){
				he.printStackTrace();  //或者throw he;
			}finally{
				try{//关闭session报异常几乎不会，此处的try catch可省略，但不建议。
					if(session!=null){
						session.close();
					}
				}catch(HibernateException he){
					he.printStackTrace();
				}
			}
			return list;
		}
}
