package dao;

import java.math.BigInteger;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Registration;
import util.HibernateUtil;

public class DeleteDaoImpl {
	public static Integer Delete_Update(Integer usr_id, String reason) {
		
		
		Session session = HibernateUtil.buildSessionFactory();

		session.beginTransaction();

		int ret = 0;
	
	//	 LocalDateTime now = LocalDateTime.now();  
		java.sql.Timestamp now = new java.sql.Timestamp(new java.util.Date().getTime());
		Query query = session.createNativeQuery(
				"Update registration set Deactivated_time= '" + now + "' ,deactivated=1,reason='"+ reason + "' where registration_id=" + usr_id + " ;");
    
		
		// needs other condition too but unable to find correct column
		ret = query.executeUpdate();
		
		
		
		session.getTransaction().commit();
		System.out.println(ret1);
		session.close();
		
		return ret;
	}
	
	public static Integer Login_Delete(Integer usr_id) {
		Session session = HibernateUtil.buildSessionFactory();

		Query query = session.createNativeQuery(
				"Select registration.activated,CAST(last_login_datetime AS DATE) ,CAST(Deactivated_time AS DATE) from allcures1.registration where registration_id= " + usr_id + " ;");
		session.beginTransaction();
		
		List<Object[]> results = (List<Object[]>) query.getResultList();
		List hmFinal = new ArrayList();
		for (Object[] objects : results) {
			
			HashMap hm = new HashMap();

			Integer count1 = (Integer) objects[0];

			Date date1 = (Date) objects[1];
			Date date2 = (Date) objects[2];
			hm.put("No. of hits", count1);
			System.out.println(date1);
			System.out.println(count1);
			System.out.println(date2);
			
			 int result = date1.compareTo(date2);
			    if (result < 0) {
			      System.out.println(date1 + " is before " + date2);
			      System.out.println("Account is deleted");
			      
			    } 
			    else
			    {
			    	System.out.println("Account is not deleted");
			    }
			
			
			hmFinal.add(hm);

		}
		
		session.getTransaction().commit();

		session.close();
		
	return 1;
	
	}
	
	
	}
