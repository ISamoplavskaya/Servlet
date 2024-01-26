package servlets;

import Entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/post")
public class PostServlet extends HttpServlet {
private static final ObjectMapper mapper=new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder requestBody;
        try(BufferedReader reader = req.getReader()){
            requestBody=new StringBuilder();
            String line;
            while ((line=reader.readLine())!=null){
                requestBody.append(line);
            }
        }
        System.out.println("Received JSON: " + requestBody.toString());
        Person person=mapper.readValue(requestBody.toString(), Person.class);
        System.out.println(person);
    }
}
