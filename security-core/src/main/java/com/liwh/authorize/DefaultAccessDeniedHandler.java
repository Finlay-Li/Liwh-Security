package com.liwh.authorize;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author: Lwh
 * @ClassName: DefaultAccessDeniedHandler
 * @Description:
 * @version: 1.0.0
 * @date: 2019-03-15 2:19 PM
 */
@Component("accessDeniedHandler")
public class DefaultAccessDeniedHandler implements AccessDeniedHandler {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        /*try {
            response.setContentType("application/json;charset=UTF-8");
            BaseResponse<String> result = new BaseResponse<String>(new ResponseData<>(ResponseStatus., "您无访问权限！", null));
            String jsonString = objectMapper.writeValueAsString(result);
            response.setStatus(200);
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            throw new ServletException(e);
        }*/
        System.out.println("您无访问权限！@@@@@@@@403@@@@@@@@@");
    }
}
