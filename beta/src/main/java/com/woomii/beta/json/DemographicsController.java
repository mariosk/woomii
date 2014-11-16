package com.woomii.beta.json;
import com.woomii.beta.backend.demographics.Demographics;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Demographics.class)
@Controller
@RequestMapping("/demographicses")
@RooWebScaffold(path = "demographicses", formBackingObject = Demographics.class)
public class DemographicsController {
}
