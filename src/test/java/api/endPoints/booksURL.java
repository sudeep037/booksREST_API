package api.endPoints;

public class booksURL {

	public static String baseURI = "https://fakerestapi.azurewebsites.net/api/v1/Books";
	public static String postURI = baseURI;
	public static String getURI = baseURI+"/{bookID}";
	public static String putURI = baseURI+"/{bookID}";
	public static String deleteURI = baseURI+"/{bookID}";
}
