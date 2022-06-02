
package za.co.bigone.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import za.co.bigone.manager.DBPoolManagerBasic;


public class ContextListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext sc = sce.getServletContext();
    String driver = sc.getInitParameter("driver");
    String url = sc.getInitParameter("url");
    String user_name = sc.getInitParameter("user_name");
    String password = sc.getInitParameter("password");
    String database = sc.getInitParameter("database");
    String sqlsecurity = sc.getInitParameter("sqlsec");
    DBPoolManagerBasic db = new DBPoolManagerBasic(driver, url + database + sqlsecurity , user_name, password);
//  DBPoolManagerBasic db = new DBPoolManagerBasic();

    sc.setAttribute("dbconn", db);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    ServletContext sc = sce.getServletContext();
    DBPoolManagerBasic db = (DBPoolManagerBasic) sc.getAttribute("dbconn");
    if (db != null) {
      db.closePool();
    }
  }

}
