package com.d.dezps.demo.control;

import com.d.dezps.demo.bean.User;
import com.d.dezps.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/emps")
    public String list(Model model) {
        List<User> user = userMapper.findAllUser();
        //查询所有员工返回列表页面
        model.addAttribute("emps", user);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage() {
        return "emp/add";
    }
    //根据id返回修改页面
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id")Long id,Model model)
    {
        User byId = userMapper.findById(id);
        model.addAttribute("user",byId);
        return "emp/edit";
    }

    @PutMapping("/emp")
    public String EditUser(@RequestParam("id")Long id,@RequestParam("name")String name, @RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("isadmin")boolean isadmin)
    {

        userMapper.updateUser(name,username,password,isadmin,id);
        return "redirect:/emps";
    }
    @DeleteMapping("/emp/{id}")
    public String deleteUser(@PathVariable("id")Long id,HttpSession session,Map<String,Object> map)
    {
        Object loginUser = session.getAttribute("loginUser");

        if (loginUser.equals("[admin]"+userMapper.findById(id).getUsername())){

            return "redirect:/emps";
        }
        userMapper.deleteUser(id);
        return "redirect:/emps";
    }

    @PostMapping("/emp")
    public String toList(@RequestParam("name")String name, @RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("isadmin")boolean isadmin, Map<String,Object> map)
    {
        if ("".equals(name)||"".equals(password)||"".equals(username))
        {
            map.put("msga","请认真填写每一项");
            return "emp/add";
        }
        User user = userMapper.findByUsername(username);
        if (user==null)
        {
            userMapper.addUser(name,username,password,isadmin);
            return "redirect:/emps";
        }else
        {
            map.put("msg","账户名重复");
            return "emp/add";
        }
    }
}
