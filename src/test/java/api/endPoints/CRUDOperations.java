package api.endPoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

import api.Payload.booksPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CRUDOperations {

	public static Response postBOOK(booksPOJO payLoad)
	{
		JSONObject jo = new JSONObject();
		JSONObject publishDate = jo.put("pDate", "2023-11-28T15:53:21.391Z");
		
		Response response = (Response) given()
		 .contentType(ContentType.JSON)
		 .body(payLoad)
		 .body(publishDate.toString())
		
		.when()
		 .post(booksURL.postURI);
		return response;
	}
	
	public static Response getBOOK(int bookID)
	{
		Response response = given()
				.contentType(ContentType.JSON)
				.pathParam("bookID", bookID)
			
			.when()
			 .get(booksURL.getURI);
		
		return response;
	}
	
	public static Response putBOOK(int bookID, booksPOJO payLoad)
	{
		JSONObject jo = new JSONObject();
		JSONObject publishDate = jo.put("pDate", "2023-11-28T15:53:21.391Z");
		
		Response response = given()
				.contentType(ContentType.JSON)
				.pathParam("bookID", bookID)
				.body(payLoad)
				.body(publishDate.toString())
				
			.when()
			 .put(booksURL.putURI);
		
		return response;
	}
	
	public static Response deleteBOOK(int bookID)
	{
		Response response = given()
				.contentType(ContentType.JSON)
				.pathParam("bookID", bookID)
				
			.when()
			 .delete(booksURL.deleteURI);
		
		return response;
	}
}
