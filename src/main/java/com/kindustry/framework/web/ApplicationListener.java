package com.kindustry.framework.web;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

// import com.soak.framework.scheduler.SchedulerManager;

// import com.kindustry.etl.job.EtlJobImpl;
// import com.kindustry.framework.scheduler.SchedulerManager;

public class ApplicationListener extends ContextLoaderListener {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private static ApplicationContext applicationContext;

  private Timer timer;

  /**
   * web 应用 初始化操作
   */
  @Override
  public void contextInitialized(ServletContextEvent event) {
    super.contextInitialized(event);
    // String pwd = KeyedDigestMD5.getKeyedDigest(employeePass,
    // "").toUpperCase();
    // Spring 上下文 初始化
    ServletContext context = event.getServletContext();
    applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);

    ServletContext servletContext = event.getServletContext();
    // SecurityManager securityManager = this.getSecurityManager(servletContext);
    // Map<String, String> urlAuthorities = securityManager.loadUrlAuthorities();
    // servletContext.setAttribute("urlAuthorities", urlAuthorities);

    // 上下文初始化执行
    logger.debug("================>[ServletContextListener]自动加载启动开始.");
    // 读取Spring容器中的Bean[此时Bean已加载,可以使用]
    logger.debug("================>[ServletContextListener]自动加载启动结束.");

    // SchedulerManager scheduler = SchedulerManager.getInstance();
    // scheduler.putSchedule(new Thread() {
    // public void run() {
    // // EtlJobImpl etljob = new EtlJobImpl();
    // // etljob.work();
    // // logger.debug(" etljob work  ");
    // }
    // });

  }

  /**
   * Get SecurityManager from ApplicationContext
   * 
   * @param servletContext
   * @return
   */
  protected SecurityManager getSecurityManager(ServletContext servletContext) {
    return (SecurityManager)WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("securityManager");
  }

  // 定时任务
  private void schedule() {

    long interval = 1000 * 60 * 60 * 24; // 隔一天

    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    calendar.set(Calendar.HOUR_OF_DAY, 2);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);

    Calendar d = Calendar.getInstance();

    logger.debug("TimerTask calendar : " + calendar.getTime().toLocaleString());
    Date current = new Date();
    logger.debug("TimerTask Date : " + current.toLocaleString());
    logger.debug("TimerTask calendar : " + d.getTime().toLocaleString());

    timer = new Timer();
    TimerTask task = new TimerTask() {
      int i = 0;

      public void run() {
        i++;
        logger.debug(" timer[" + i + "]");
      }
    };

    // 定时任务
    timer.schedule(task, calendar.getTime(), interval);

  }

  /**
   * web 应用 关闭
   */
  @Override
  public void contextDestroyed(ServletContextEvent contextEvent) {
    logger.debug("ApplicationListener  :  contextDestroyed   ....................");
    super.contextDestroyed(contextEvent);
    // contextEvent.getServletContext().removeAttribute("urlAuthorities");

    if (timer != null) {
      timer.cancel();
    }
  }

  /**
   * 返回 Spring Context
   * 
   * @return
   */
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  public static <T> T getBean(String beanName) {
    return (T)applicationContext.getBean(beanName);
  }

}