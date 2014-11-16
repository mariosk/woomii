package com.woomii.beta.json;
import com.woomii.beta.backend.impressions.Impressions;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Impressions.class)
@Controller
@RequestMapping("/impressionses")
@RooWebScaffold(path = "impressionses", formBackingObject = Impressions.class)
public class ImpressionsController {
}
