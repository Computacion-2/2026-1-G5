package co.icesi.views;

import java.util.List;

import co.icesi.model.User;

public class UsersView {
    

    public String listUsers(List<User> users){
        StringBuilder builder = new StringBuilder();

        builder.append("<table>");
        builder.append("<theader>");
        builder.append("<tr>");
            builder.append("<th> ID </th>");
            builder.append("<th> Name </th>");
            builder.append("<th> UserName </th>");
        builder.append("</tr>");
        builder.append("</theader>");
        builder.append("<tbody>");
        for (User user : users) {
            builder.append("<tr>");
            builder.append("<td>"+user.getId()+"</td>");
            builder.append("<td>"+user.getName()+"</td>");
            builder.append("<td>"+user.getUsername()+"</td>");
            builder.append("</tr>");
            
        }
        builder.append("</tbody>");
        builder.append("</table>");

        return builder.toString();
    }
}
