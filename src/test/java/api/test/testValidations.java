package api.test;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Payload.booksPOJO;
import api.endPoints.CRUDOperations;
import io.restassured.response.Response;

public class testValidations {

	booksPOJO booksPayload;
	Faker faker;
	
	@BeforeMethod
	public void setUpBooksData()
	{
		booksPayload = new booksPOJO();
		faker = new Faker();
		
		booksPayload.setId(faker.idNumber().hashCode());
		booksPayload.setPageCount(faker.book().hashCode());
		booksPayload.setTitle(faker.book().title());
		booksPayload.setDescription(faker.book().genre());
		booksPayload.setExcerpt(faker.book().publisher());
	}
	
	@Test(priority=1)
	public void testPOSTBook()
	{
		Response response = CRUDOperations.postBOOK(booksPayload);
		response.then().log().all();
		
		JSONObject jo = new JSONObject(response.asString());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.header("Content-Type"), "application/json; charset=utf-8; v=1.0");
		Assert.assertEquals(response.header("Transfer-Encoding"), "chunked");
		Assert.assertEquals(jo.get("id"), booksPayload.getId());
		Assert.assertEquals(jo.get("pageCount"), booksPayload.getPageCount());
		Assert.assertEquals(jo.get("title"), booksPayload.getTitle());
		Assert.assertEquals(jo.get("description"), booksPayload.getDescription());
		Assert.assertEquals(jo.get("excerpt"), booksPayload.getExcerpt());
	}
	
	@Test(priority=2)
	public void testGETBook()
	{
		Response response = CRUDOperations.getBOOK(this.booksPayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 404);
	}
	
	@Test(priority=3)
	public void testPUTBook()
	{
		Response response = CRUDOperations.putBOOK(this.booksPayload.getId(), booksPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=4)
	public void testDELETEBook()
	{
		Response response = CRUDOperations.deleteBOOK(this.booksPayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
