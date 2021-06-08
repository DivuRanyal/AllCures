package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.CityDaoImpl;

@RestController
@RequestMapping(path = "/city")
public class CityController {

	@Autowired
	private CityDaoImpl cityDaoImpl;

	@RequestMapping(value = "/all", produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody List getCityDetails() {
		return cityDaoImpl.getAllCityDetails();

	}

}