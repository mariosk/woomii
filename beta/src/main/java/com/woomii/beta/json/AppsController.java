package com.woomii.beta.json;
import com.woomii.beta.frontend.apps.Apps;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Apps.class)
@Controller
@RequestMapping("/appses")
@RooWebScaffold(path = "appses", formBackingObject = Apps.class)
public class AppsController {
}
