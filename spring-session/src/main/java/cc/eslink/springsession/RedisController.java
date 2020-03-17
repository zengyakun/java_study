package cc.eslink.springsession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *@ClassName RedisController
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/3/17 17:11
 *@Version 1.0
 **/
@RestController
@RequestMapping("/admin")
public class RedisController {

    @GetMapping("/setSessionId")
    public String setredisResult(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("sessionId", "100");
        return "设置ok...";
    }

    @GetMapping("/getSessionId")
    public String redisResult(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessid = (String) session.getAttribute("sessionId");
        return "sessionId:" + sessid;
    }
}
