package com.example.application.controller;

import com.example.application.form.UserDetailForm;
import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserDetailController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/detail/{userId:.+}")
    public String getUser(UserDetailForm form, Model model, @PathVariable("userId") String userId) {
        MUser user = userService.getUserOne(userId);

        user.setPassword(null);

        form = modelMapper.map(user, UserDetailForm.class);

        model.addAttribute("userDetailForm", form);

        return "user/detail";
    }


    @PostMapping(value="/detail", params = "update")
    public String updateUser(UserDetailForm form, Model model) {
        try {
            userService.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());
        }
        catch (Exception e) {
            log.error("Error in user update", e);
        }

        return "redirect:/user/list";
    }

    @PostMapping(value="/detail", params = "delete")
        public String deleteUser(UserDetailForm form, Model model) {
        userService.deleteUserOne(form.getUserId());
        return "redirect:/user/list";
    }

}
