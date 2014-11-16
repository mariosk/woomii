// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.json;

import com.woomii.beta.frontend.apps.Apps;
import com.woomii.beta.frontend.campaigns.Campaigns;
import com.woomii.beta.json.CampaignsController;
import com.woomii.beta.types.CurrencyType;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect CampaignsController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String CampaignsController.create(@Valid Campaigns campaigns, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, campaigns);
            return "campaignses/create";
        }
        uiModel.asMap().clear();
        campaigns.persist();
        return "redirect:/campaignses/" + encodeUrlPathSegment(campaigns.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String CampaignsController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Campaigns());
        return "campaignses/create";
    }
    
    @RequestMapping(value = "/{Id}", produces = "text/html")
    public String CampaignsController.show(@PathVariable("Id") Long Id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("campaigns", Campaigns.findCampaigns(Id));
        uiModel.addAttribute("itemId", Id);
        return "campaignses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String CampaignsController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("campaignses", Campaigns.findCampaignsEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Campaigns.countCampaignses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("campaignses", Campaigns.findAllCampaignses(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "campaignses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String CampaignsController.update(@Valid Campaigns campaigns, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, campaigns);
            return "campaignses/update";
        }
        uiModel.asMap().clear();
        campaigns.merge();
        return "redirect:/campaignses/" + encodeUrlPathSegment(campaigns.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{Id}", params = "form", produces = "text/html")
    public String CampaignsController.updateForm(@PathVariable("Id") Long Id, Model uiModel) {
        populateEditForm(uiModel, Campaigns.findCampaigns(Id));
        return "campaignses/update";
    }
    
    @RequestMapping(value = "/{Id}", method = RequestMethod.DELETE, produces = "text/html")
    public String CampaignsController.delete(@PathVariable("Id") Long Id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Campaigns campaigns = Campaigns.findCampaigns(Id);
        campaigns.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/campaignses";
    }
    
    void CampaignsController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("campaigns_launch_date_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("campaigns_expiration_date_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("campaigns_credits_expiration_date_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void CampaignsController.populateEditForm(Model uiModel, Campaigns campaigns) {
        uiModel.addAttribute("campaigns", campaigns);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("appses", Apps.findAllAppses());
        uiModel.addAttribute("currencytypes", Arrays.asList(CurrencyType.values()));
    }
    
    String CampaignsController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}