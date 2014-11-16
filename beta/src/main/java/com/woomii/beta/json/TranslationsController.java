package com.woomii.beta.json;
import com.woomii.beta.frontend.translations.Translations;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Translations.class)
@Controller
@RequestMapping("/translationses")
@RooWebScaffold(path = "translationses", formBackingObject = Translations.class)
public class TranslationsController {
}
