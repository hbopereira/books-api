package books.book;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import books.base.BaseService;

@Service
public class BookService extends BaseService<Book,BookRepository>{

	public String pesquisarLivros(@Param("descricao") String descricao) throws IOException {
		String json = "";
		//Book book = null;
		try {
			System.out.println(descricao);
			URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=" + descricao
					+ "&Key=AIzaSyCucacBlCsWS0RzBFEITFDfo6wRwOmhPfU");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("charset", "utf-8");
			connection.connect();
			InputStream inStream = connection.getInputStream();
			json = streamToString(inStream);
			//book = new Gson().fromJson(json, Book.class);		
			
		
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return json;
	}

	@SuppressWarnings("resource")
	private static String streamToString(InputStream inputStream) {
		String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
		return text;
	}

}
