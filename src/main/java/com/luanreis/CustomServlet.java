package com.luanreis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        PrintWriter writer = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        printWelcomeMessage(writer, name, email);
    }

    private void printWelcomeMessage(PrintWriter writer, String name, String email) {
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<p>Hello, " + name + "</p>");
        writer.println("<img src=\"https://www.gravatar.com/avatar/" + getHash(email) + "\" >");
        writer.println("<p>Nice picture!</p>");
        writer.println("</body>");
        writer.println("</html>");
    }

    private String getHash(String email) {
        MessageDigest messageDigest = null;
        String algorithm = "MD5";
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(algorithm.concat(" is not available."));
        }
        messageDigest.update(StandardCharsets.UTF_8.encode(email));
        return String.format("%032x", new BigInteger(1, messageDigest.digest()));
    }
}
