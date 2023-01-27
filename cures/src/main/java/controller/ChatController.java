package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dao.ChatDaoImpl;

@RestController
//@RequestMapping(path = "/chat")
public class ChatController {

	 
		@RequestMapping(value = "/chat/msg", produces = "application/json", method = RequestMethod.POST)
		public @ResponseBody int chat_store(@RequestParam(required = false) Integer chat_id, @RequestBody HashMap chatMap, HttpServletRequest request) {
		
			System.out.println("hh");
			if (chat_id==null)
			{
			
			chat_id= ChatDaoImpl.ChatStore();
			System.out.println(chat_id);
			}
			
			return ChatDaoImpl.Chat_Store(chat_id, chatMap);
		
		
		}
		
		
		@RequestMapping(value = "/leads/count/{doc_id}", produces = "application/json", method = RequestMethod.POST)
		public @ResponseBody int doctor_leads(@PathVariable int doc_id) {
		
			return ChatDaoImpl.DoctorLeads(doc_id);
		
		}
		
		@RequestMapping(value = "/chat/{chat_id}", produces = "application/json", method = RequestMethod.GET)
		public @ResponseBody List chat_search(@PathVariable int chat_id) {
		
			return ChatDaoImpl.Chat_Search(chat_id);
		
		}
		
		@RequestMapping(value = "/chat/history", produces = "application/json", method = RequestMethod.GET)
		public @ResponseBody List chat_history1(@RequestParam(required = false) Integer doc_id,@RequestParam(required = false) Date date1) {
		
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate1 = dateFormat.format(date1);
			System.out.println(strDate1);
			return ChatDaoImpl.Chat_History(doc_id,strDate1);
		
		}
		
		@RequestMapping(value = "/chat/archive", produces = "application/json", method = RequestMethod.PUT)
		public @ResponseBody Integer chat_archive() {
		
			
			return ChatDaoImpl.Chat_Archive();
		
		}
}
