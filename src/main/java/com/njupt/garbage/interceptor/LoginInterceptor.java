package com.njupt.garbage.interceptor;

import com.njupt.garbage.anotation.OnlyManager;
import com.njupt.garbage.anotation.OnlyRoot;
import com.njupt.garbage.common.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:wong
 */
@Component
public class LoginInterceptor implements HandlerInterceptor{

    @Value("${COOKIE_ID}")
    private String COOKIE_ID;
    @Value("${LOGIN_PAGE_URL}")
    private String LOGIN_PAGE_URL;
    @Value("${COOKIE_IDENTITY}")
    private String COOKIE_IDENTITY;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //用户权限问题

        //直接从cookie中取username
        String id= CookieUtils.getCookieValue(request, COOKIE_ID);
        String identity = CookieUtils.getCookieValue(request, COOKIE_IDENTITY);
        //判断其是否为空
        if (id == null || id.equals("")){
            response.sendRedirect(LOGIN_PAGE_URL);
            return false;
        }
        boolean haveAnnotataion = handler.getClass().isAssignableFrom(HandlerMethod.class);
        if (haveAnnotataion) {
            // 检测是否有 @OnlyAdmin 注解
            OnlyManager om = ((HandlerMethod)handler).getMethodAnnotation(OnlyManager.class);
            OnlyRoot or = ((HandlerMethod)handler).getMethodAnnotation(OnlyRoot.class);
            if (or != null){
                if (!identity.equals("-1")){
                    System.out.println("您未拥有管理员权限！");
                    return false;
                }
            }
            if (om != null) {
                // 如果有 @OnlyAdmin则表明该方法只允许管理员操作
                if (!identity.equals("0") && !identity.equals("-1")) { // 这里假设以ID为1为管理员
                    System.out.println("您未拥有编辑权限！");
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
