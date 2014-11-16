package com.woomii.beta.json;
import com.woomii.beta.frontend.languages.Languages;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Languages.class)
@Controller
@RequestMapping("/languageses")
@RooWebScaffold(path = "languageses", formBackingObject = Languages.class)
public class LanguagesController {
}
