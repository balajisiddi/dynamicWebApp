package com.balajisiddi.rest;


import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.balajisiddi.model.Student;
import com.balajisiddi.servlets.BookInsertionServlet;


@Path("/student")
public class RestService {

	private static final Logger log= Logger.getLogger(BookInsertionServlet.class);
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/books")
	public Response jsontest() { 
		List<Student> students= new ArrayList<Student>();
		students.add(new Student(1, "Balaji", "10thClass"));
		students.add(new Student(2, "Siddi", "Inter"));
		GenericEntity<List<Student>> myresp= new GenericEntity<List<Student>>(students) {};
		return Response.status(200).entity(myresp).build();
	}
	
	@GET
	@Produces("image/png")
	@Path("/pic")
	public Response jsonImage() {
		File repositoryFile = new File("D:\\WorkFromHome\\20200502\\image_rest.png");
		return Response.status(200).entity(repositoryFile).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sendStudent")
	public Response sendStudentToSschool(Student student) {
		log.info("Hello"+student.getName());
		GenericEntity<Student> myresp= new GenericEntity<Student>(student) {};
		return Response.status(200).entity(myresp).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sendStudent/{name}")
	public Response updateStudentStat(@PathParam("name")String name) {
		log.info("Hello"+name);
		Student std=new Student();
		std.setName(name);
		GenericEntity<Student> myresp= new GenericEntity<Student>(std) {};
		return Response.status(200).entity(myresp).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getQueryParam")
	public Response getQueryParam(@QueryParam("name")String name) {
		log.info("Hello"+name);
		Student std=new Student();
		std.setName(name);
		GenericEntity<Student> myresp= new GenericEntity<Student>(std) {};
		return Response.status(200).entity(myresp).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getContextParam")
	public Response getContextParam(@Context UriInfo uriInfo) {
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		log.info("Hello"+queryParams.getFirst("name"));
		log.info("Your age:"+ queryParams.getFirst("age"));
		Student std=new Student();
		std.setName(queryParams.getFirst("name"));
		
		std.setSclass(queryParams.getFirst("age"));
		GenericEntity<Student> myresp= new GenericEntity<Student>(std) {};
		return Response.status(200).entity(myresp).build();
	}
}
