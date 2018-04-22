package pers.yangs.winterframework.web.servlet;

import org.apache.commons.lang3.StringUtils;
import pers.yangs.winterframework.web.annotation.Controller;
import pers.yangs.winterframework.web.annotation.RequestMapping;
import pers.yangs.winterframework.web.util.PathUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Created by Ytadp on 2018/4/22.
 */
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger("DispatcherServlet");
    //扫描路径
    private String scanPath;

    //controller
    private Map<String, Object> mvcInstanceMap = new ConcurrentHashMap<>();

    //requestMap
    private Map<String, Method> mvcRequestMap = new ConcurrentHashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        scanPath = config.getInitParameter("scan-package");

        // TODO: 2018/4/22 后续路径可以支持多个
        initMvc(scanPath);
    }

    private void initMvc(String scanPath) {
        scanPath = "pers.yangs.business.controller.TestController";
        if(StringUtils.isEmpty(scanPath)){
            log.info("路径错误");
            return;
        }

        try {
            Class c = Class.forName(scanPath);
            if(c.isAnnotationPresent(Controller.class)){
                Controller controller = (Controller) c.getAnnotation(Controller.class);
                String value1 = controller.value();
                mvcInstanceMap.put(PathUtils.pathStr(value1), c.newInstance());

                Method[] methods = c.getDeclaredMethods();
                for(Method m : methods){
                    RequestMapping r = m.getAnnotation(RequestMapping.class);
                    if(null == r) continue;

                    String value2 = r.value();
                    String key = PathUtils.pathAppend(value1, value2);

                    mvcRequestMap.put(key, m);
                }
            }
        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = PathUtils.pathStr(req.getRequestURI());
        String[] vlaues = uri.split("/");

        if(vlaues.length != 2) {

        }
        String v1 = vlaues[0];
        Object obj = mvcInstanceMap.get(v1);

        Method m = mvcRequestMap.get(uri);

        try {
            Object result = m.invoke(obj);
            resp.getOutputStream().write(result.toString().getBytes());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
