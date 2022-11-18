package dao;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import util.HibernateUtil;

@Component
public class SubscriptionDaoImpl {

	

	public static int addSubscriptionDetails(HashMap promoMap) {

		Session session = HibernateUtil.buildSessionFactory();

		session.beginTransaction();

		String subscription_details = (String) promoMap.get("subscription_details");
		int subscription_status = 1;

		String subscription_starttime = null;
		String subscription_endtime = null;
		int price_id = 0;
		String subscription_updatedtime = null;

		if (promoMap.containsKey("subscription_status ")) {
			subscription_status  = Integer.parseInt( (String)promoMap.get("subscription_status ") );
		}
		
		if (promoMap.containsKey("subscription_starttime")) {
			subscription_starttime = (String) promoMap.get("subscription_starttime");
		}
		if (promoMap.containsKey("subscription_endtime")) {
			subscription_endtime = (String) promoMap.get("subscription_endtime");
		}
		if (promoMap.containsKey("price_id")) {
			price_id= Integer.parseInt( (String) promoMap.get("price_id") );
		}
		
			java.util.Date date=new java.util.Date();
			java.sql.Timestamp sqlDate=new java.sql.Timestamp(date.getTime());
			subscription_updatedtime = sqlDate.toString();
			System.out.println("subscription_updatedtime>>>>>"+subscription_updatedtime);
		
//			java.util.Date date = new java.util.Date();
//			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//			updatestr += "`published_date` = '" + sqlDate + "',\r\n";
		
		Query query = session
				.createNativeQuery("INSERT INTO `subscription_master`" + " (`subscription_details`,"
						+ " `subscription_status`," + " `subscription_starttime`," + " `subscription_endtime`,"
						+ " `price_id`," + " `subscription_updatedtime`)"
						+ " VALUES" + " ('" + subscription_details + "', " + "  '" + subscription_status + "', " + "  '"
						+ subscription_starttime + "', "+"  '" + subscription_endtime + "'," + " '" + price_id
						+ "'," + " '" +  subscription_updatedtime + "');" + "");
		int ret = 0;
		try {
			ret = query.executeUpdate();
			session.getTransaction().commit();
			System.out.println("inserted new entry to subscription_master table for subscription_details =  " + subscription_details);

		} catch (Exception ex) {
			session.getTransaction().rollback();
		} finally {
		
		}

		return ret;
	}
	
	public static int addOrderDetails(int userid, int subscriptionid,HashMap promoMap) {

		Session session = HibernateUtil.buildSessionFactory();

		session.beginTransaction();

		String amount = (String) promoMap.get("amount");
		String id = (String) promoMap.get("order_id");
		String PaymentId = null;
		String Status = "created";
		int status = 0;
		String start_time = null;
		String end_date = null;
		
		java.util.Date date=new java.util.Date();
		java.sql.Timestamp sqlDate=new java.sql.Timestamp(date.getTime());
		start_time = sqlDate.toString();
		System.out.println("start_time>>>>>"+start_time);
		
		
		java.util.Date dates=new java.util.Date();
		java.sql.Timestamp sqlDates=new java.sql.Timestamp(date.getTime());
		end_date = sqlDate.toString();
		System.out.println("start_time>>>>>"+start_time);
		
		
		
		
          Query query = session
				.createNativeQuery("INSERT INTO `orders`" + " (`amount`,"
						+ " `order_id`," + " `payment_id`," 
						+ " `razorpay_status`," + " `user_id`,"+ " `subscription_id`,"+" `status`,"+"`start_date`,"+"`End_date` )"
						+ " VALUES" + " ('" + amount + "',    " + "     '" + id + "',    " + "    '"
						+ PaymentId + "',    "+"      '" + Status 
						+ "', " + " '" + userid + "', " + " '" + subscriptionid + "', "+" '" + status + "', "+" '"+start_time+"', "+" '"+end_date+"');" + "");
		int ret = 0;
		try {
			ret = query.executeUpdate();
			session.getTransaction().commit();
			System.out.println("inserted new entry to orders table for amount =  " + amount);

		} catch (Exception ex) {
			session.getTransaction().rollback();
		} finally {
		
		}

		return ret;
	}
		
	public static int updateOrderDetails(String order_id, HashMap articleMap) {

		Session session = HibernateUtil.buildSessionFactory();

		
		session.beginTransaction();

		String updatestr = "";
		if (articleMap.containsKey("payment_id")) {
			updatestr += "`payment_id` = '" + articleMap.get("payment_id") + "',";
		}
		
		if (articleMap.containsKey("razorpay_status")) {
			updatestr += "`razorpay_status` = '" + articleMap.get("razorpay_status") + "',";
		}
		

		if (articleMap.containsKey("status")) {
			updatestr += "`status` = '" + articleMap.get("status") + "',";
		}
		
		if (articleMap.containsKey("subscription_id")) {
			updatestr += "`subscription_id` = " + articleMap.get("subscription_id") + ",\r\n";
			
			if ((int) articleMap.get("subscription_id") == 1) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Calendar cal = Calendar.getInstance();
				System.out.println("Current Date: "+sdf.format(cal.getTime()));
				   
			        cal.add(Calendar.DATE, 1);  
				String end_date = sdf.format(cal.getTime());  
				System.out.println("Date after Addition: "+end_date);
			updatestr += "`End_date` = '" + end_date + "',\r\n";
		}
			else if  ((int) articleMap.get("subscription_id") == 2) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Calendar cal = Calendar.getInstance();
				System.out.println("Current Date: "+sdf.format(cal.getTime()));
				   
			        cal.add(Calendar.DAY_OF_MONTH, 30);  
				String end_date = sdf.format(cal.getTime());  
				System.out.println("Date after Addition: "+end_date);
			updatestr += "`End_date` = '" + end_date + "',\r\n";
		}
        else if  ((int) articleMap.get("subscription_id") == 3) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Calendar cal = Calendar.getInstance();
				System.out.println("Current Date: "+sdf.format(cal.getTime()));
				   
			        cal.add(Calendar.YEAR, 1);  
				String end_date = sdf.format(cal.getTime());  
				System.out.println("Date after Addition: "+end_date);
			updatestr += "`End_date` = '" + end_date + "',\r\n";
		}
		
		}
		

		updatestr = updatestr.replaceAll(",$", "");
		Query query = session.createNativeQuery(
				"UPDATE `orders`" + "SET" + updatestr + " WHERE `order_id` = " + order_id + ";");
		int ret = 0;
		try {
			ret = query.executeUpdate();
			session.getTransaction().commit();
			System.out.println("updated order table for order_id =  " + order_id);

		} catch (Exception ex) {
			session.getTransaction().rollback();
		} finally {
	
		}

		return ret;
	}
	
	
	public static int updateStatus(String curdate ) {

		Session session = HibernateUtil.buildSessionFactory();

		session.beginTransaction();

	
		Query query = session
				.createNativeQuery("UPDATE orders SET status=0 WHERE End_date  = " + curdate  + ";");
		int ret = 0;
		try {
			ret = query.executeUpdate();
			System.out.println("soft deleteed from orders, where End_date  =  " + curdate );
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
		} finally {

		}

		return ret;
	}
	
	public static ArrayList getAllSubscriptionDetails() {

		Session session = HibernateUtil.buildSessionFactory();

		Query query = session.createNativeQuery("SELECT `subscription_master`.`subscription_id`,"
				+ "    `subscription_master`.`subscription_details`," + "    `subscription_master`.`subscription_status`,"
				+ "    `subscription_master`.`subscription_starttime`," + "    `subscription_master`.`subscription_endtime`,"
				
				+ "    `subscription_master`.`price_id`," + "    `subscription_master`.`detailing`,"+ "    `subscription_master`.`plan` "+ "FROM `subscription_master`;");
		
		List<Object[]> results = (List<Object[]>) query.getResultList();
		System.out.println("result list Subscription@@@@@@@@@@@@@ size=" + results.size());
		List hmFinal = new ArrayList();
		for (Object[] objects : results) {
			HashMap hm = new HashMap();
			int subscription_id = (int) objects[0];
			String subscription_details = (String) objects[1];
			int subscription_status = (int) objects[2];
			java.sql.Timestamp subscription_starttime = (java.sql.Timestamp) objects[3];
			java.sql.Timestamp subscription_endtime = (java.sql.Timestamp) objects[4];
			int price_id = (int) objects[5];
			String detailing = (String) objects[6];
			String plan= (String) objects[7];



			hm.put("subscription_id", subscription_id);
			hm.put("subscription_details", subscription_details);
			hm.put("subscription_status", subscription_status);
			hm.put("subscription_starttime", subscription_starttime);
			hm.put("subscription_endtime", subscription_endtime);
			hm.put("price_id", price_id);
			hm.put("detailing", detailing);
			hm.put("plan", plan);
			hmFinal.add(hm);
		}
		return (ArrayList) hmFinal;
	}
	


	public static int updateSubscriptionDetails(int subscription_id, HashMap articleMap) {

		Session session = HibernateUtil.buildSessionFactory();

		
		session.beginTransaction();

		String updatestr = "";
		if (articleMap.containsKey("subscription_details")) {
			updatestr += "`subscription_details` = '" + articleMap.get("subscription_details") + "',";
		}
		if (articleMap.containsKey("subscription_status")) {
			updatestr += "`subscription_status` = " + articleMap.get("subscription_status") + ",";
		}
		if (articleMap.containsKey("subscription_starttime")) {
			updatestr += "`subscription_starttime` = '" + articleMap.get("subscription_starttime") + "',";
		}
		if (articleMap.containsKey("subscription_endtime")) {
			updatestr += "`subscription_endtime` = '" + articleMap.get("subscription_endtime") + "',";
		}
		
		if (articleMap.containsKey("price_id")) {
			updatestr += "`price_id` = " + articleMap.get("price_id") + ",";
		}
		
		if (articleMap.containsKey("subscription_updatedtime")) {
			updatestr += "`subscription_updatedtime` = '" + articleMap.get("subscription_updatedtime") + "',";
		}
		
		
		
		java.util.Date date=new java.util.Date();
		java.sql.Timestamp sqlDate=new java.sql.Timestamp(date.getTime());
		String subscription_updatedtime = sqlDate.toString();
		updatestr += " `subscription_updatedtime` = '" + subscription_updatedtime + "',";
		System.out.println("subscription_updatedtime>>>>>"+subscription_updatedtime);
		
	

		updatestr = updatestr.replaceAll(",$", "");
		Query query = session.createNativeQuery(
				"UPDATE `subscription_master`" + "SET" + updatestr + " WHERE `subscription_id` = " + subscription_id + ";");
		int ret = 0;
		try {
			ret = query.executeUpdate();
			session.getTransaction().commit();
			System.out.println("updated subscription_master table for subscription_id =  " + subscription_id);

		} catch (Exception ex) {
			session.getTransaction().rollback();
		} finally {
	
		}

		return ret;
	}  
	
	
	public static int deleteSubscriptionId(int subscription_id ) {

		Session session = HibernateUtil.buildSessionFactory();

		session.beginTransaction();

	
		Query query = session
				.createNativeQuery("UPDATE subscription_master SET subscription_status=0 WHERE subscription_id  = " + subscription_id  + ";");
		int ret = 0;
		try {
			ret = query.executeUpdate();
			System.out.println("soft deleteed from subscription_master, where subscription_id  =  " + subscription_id );
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
		} finally {

		}

		return ret;
	}

	public static ArrayList getSubscriptionDetailsById(int subscription_id) {
		
		Session session = HibernateUtil.buildSessionFactory();
		
		Query query = session.createNativeQuery("SELECT `subscription_master`.`subscription_id`,"
				+ "    `subscription_master`.`subscription_details`," + "    `subscription_master`.`subscription_status`,"
				+ "    `subscription_master`.`subscription_starttime`," + "    `subscription_master`.`subscription_endtime`,"
				
				+ "    `subscription_master`.`price_id`" + "FROM `subscription_master` where subscription_id="+subscription_id+";");
		List<Object[]> results = (List<Object[]>) query.getResultList();
		System.out.println("result list Subscription@@@@@@@@@@@@@ size=" + results.size());
		List hmFinal = new ArrayList();
		for (Object[] objects : results) {
			HashMap hm = new HashMap();
			
			int subscription_id1 = (int) objects[0];
			String subscription_details = (String) objects[1];
			int subscription_status = (int) objects[2];
			java.sql.Timestamp subscription_starttime = (java.sql.Timestamp) objects[3];
			java.sql.Timestamp subscription_endtime = (java.sql.Timestamp) objects[4];
			int price_id = (int) objects[5];
			
			hm.put("subscription_id1", subscription_id1);
			hm.put("subscription_details", subscription_details);
			hm.put("subscription_status", subscription_status);
			hm.put("subscription_starttime", subscription_starttime);
			hm.put("subscription_endtime", subscription_endtime);
			hm.put("price_id", price_id);
			hmFinal.add(hm);
		}
		return (ArrayList) hmFinal;
	}
	
public static ArrayList getOrderDetailsById(int user_id) {
		
		Session session = HibernateUtil.buildSessionFactory();
		
//		String search_str = ""; 
//		search_str = " and status ='1'";
//		
		Query query = session.createNativeQuery("SELECT `orders`.`status`,"
				+ "    `orders`.`user_id`, "+ "    `orders`.`subscription_id`" + "FROM `orders` where user_id=" +user_id+  " AND status =1"+  ";");
		List<Object[]> results = (List<Object[]>) query.getResultList();
		System.out.println("result list Subscription@@@@@@@@@@@@@ size=" + results.size());
		List hmFinal = new ArrayList();
		for (Object[] objects : results) {
			HashMap hm = new HashMap();
			
			int status = (int) objects[0];
			int user_id1 = (int) objects[1];
			int subscription_id = (int) objects[2];
			
			
			hm.put("status", status);
			hm.put("user_id1", user_id1);
			hm.put("subscription_id", subscription_id);
			
			
			hmFinal.add(hm);
		}
		return (ArrayList) hmFinal;
	}
	
	
		
	

}