package com.woomii.beta.json;
import com.woomii.beta.frontend.areas.Areas;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Areas.class)
@Controller
@RequestMapping("/areases")
@RooWebScaffold(path = "areases", formBackingObject = Areas.class)
public class AreasController {
}
