package com.woomii.beta.json;
import com.woomii.beta.backend.referrals.Referrals;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Referrals.class)
@Controller
@RequestMapping("/referralses")
@RooWebScaffold(path = "referralses", formBackingObject = Referrals.class)
public class ReferralsController {
}
