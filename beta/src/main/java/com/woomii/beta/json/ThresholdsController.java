package com.woomii.beta.json;
import com.woomii.beta.backend.thresholds.Thresholds;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Thresholds.class)
@Controller
@RequestMapping("/thresholdses")
@RooWebScaffold(path = "thresholdses", formBackingObject = Thresholds.class)
public class ThresholdsController {
}
