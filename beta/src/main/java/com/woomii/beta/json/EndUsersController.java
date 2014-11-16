package com.woomii.beta.json;
import com.woomii.beta.backend.endusers.EndUsers;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = EndUsers.class)
@Controller
@RequestMapping("/enduserses")
@RooWebScaffold(path = "enduserses", formBackingObject = EndUsers.class)
public class EndUsersController {
}
