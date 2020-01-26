package com.app.todo.controller.users;

        import com.app.todo.model.User;
        import com.app.todo.repository.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @GetMapping("/users/login")
    public String login() {

        return "user/login";
    }
}
