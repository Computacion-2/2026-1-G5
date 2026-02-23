package co.icesi.views;

import java.util.List;
import java.util.stream.Collectors;

import co.icesi.model.User;

public class UsersView {

    public String listUsers(List<User> users) {
        StringBuilder builder = new StringBuilder();

        builder.append("<table class=\"simple-table\">");
        builder.append("<theader>");
        builder.append("<tr>");
        builder.append("<th> ID </th>");
        builder.append("<th> Name </th>");
        builder.append("<th> UserName </th>");
        builder.append("<th> Roles </th>");

        builder.append("</tr>");
        builder.append("</theader>");
        builder.append("<tbody>");
        for (User user : users) {
            builder.append("<tr>");
            builder.append("<td>" + user.getId() + "</td>");
            builder.append("<td>" + user.getName() + "</td>");
            builder.append("<td>" + user.getUsername() + "</td>");
            builder.append("<td>" + user.getRoles().stream().map(r -> r.getName()).collect(Collectors.joining(", "))
                    + "</td>");
            builder.append("</tr>");
        }
        builder.append("</tbody>");
        builder.append("</table>");

        return builder.toString();
    }

    public String createUserForm() {
        return "<form method=\"POST\" action=\"users\">\n" +
                "        <label for=\"username\">Username:</label>\n" +
                "        <input type=\"text\" id=\"username\" name=\"username\" required>\n" +
                "\n" +
                "        <label for=\"password\">Password:</label>\n" +
                "        <input type=\"password\" id=\"password\" name=\"password\" required>\n" +
                "\n" +
                "        <label for=\"firstName\">Name:</label>\n" +
                "        <input type=\"text\" id=\"firstName\" name=\"firstName\">\n" +
                "\n" +
                "        <label for=\"roles\">Roles ids:</label>\n" +
                "        <input type=\"text\" id=\"roles\" name=\"roles\">\n" +
                "\n" +
                "        <button type=\"submit\">Create User</button>\n" +
                "    </form>";
    }

}
