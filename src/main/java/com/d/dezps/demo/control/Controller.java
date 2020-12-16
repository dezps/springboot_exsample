package com.d.dezps.demo.control;

import com.d.dezps.demo.bean.User;
import com.d.dezps.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    UserMapper userMapper;
    @RequestMapping({"/","/index"})
    public String index(HttpSession httpSession)
    {
        if (httpSession.getAttribute("loginUser")!=null)
        {
            return "dashboard";
        }
        return "index";
    }
    @RequestMapping("/cancel")
    public String cancel(HttpSession session)
    {
        session.removeAttribute("loginUser");
        session.removeAttribute("isadmin");
        return "index";
    }
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession httpSession)
    {

        User user = userMapper.findByUser(username, password);
        if (user!=null)
        {
            if (user.getIsadmin())
            {
                httpSession.setAttribute("loginUser","[admin]"+user.getName());
                httpSession.setAttribute("isadmin",user.getIsadmin());
                return "redirect:/dashboard";
            }else
            {
                httpSession.setAttribute("loginUser",user.getName());
                httpSession.setAttribute("isadmin",user.getIsadmin());
                return "redirect:/dashboard";
            }
        }
       else
        {
            map.put("msg","用户名或密码错误");
            return "index";
        }
    }
    @RequestMapping("/dashboard")
    public String dashboard()
    {
        return "dashboard";
    }
}
