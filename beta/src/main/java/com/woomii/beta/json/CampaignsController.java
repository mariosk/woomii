package com.woomii.beta.json;
import com.woomii.beta.frontend.campaigns.Campaigns;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Campaigns.class)
@Controller
@RequestMapping("/campaignses")
@RooWebScaffold(path = "campaignses", formBackingObject = Campaigns.class)
public class CampaignsController {
}
