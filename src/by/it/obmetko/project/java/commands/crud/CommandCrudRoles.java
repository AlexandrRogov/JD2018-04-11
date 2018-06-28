package by.it.obmetko.project.java.commands.crud;

import by.it.obmetko.project.java.DAO.DAO;
import by.it.obmetko.project.java.DAO.beens.Role;
import by.it.obmetko.project.java.controller.Action;
import by.it.obmetko.project.java.controller.ActionCommand;
import by.it.obmetko.project.java.controller.FormUtil;
import by.it.obmetko.project.java.controller.Msg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;







public class CommandCrudRoles extends ActionCommand {

    @Override
    public ActionCommand execute(HttpServletRequest req, HttpServletResponse resp) throws ParseException, SQLException {       if (FormUtil.isPost(req)) {
            Role role=new Role(FormUtil.getInt(req.getParameter("id")),
                    FormUtil.getString(req.getParameter("email"), req.getParameter("role")));
            if(req.getParameter("Update")!=null){
                DAO.getDAO().roleDAO.update(role);
            }
            if(req.getParameter("Delete")!=null){
                DAO.getDAO().roleDAO.delete(role);
            }
            if(req.getParameter("Create")!=null){
                DAO.getDAO().roleDAO.create(role);

            }
        }
        List<Role> roles = DAO.getDAO().roleDAO.getAll("");
        req.setAttribute(Msg.ROLES, roles);
        return Action.CRUDROLES.command;
    }
}