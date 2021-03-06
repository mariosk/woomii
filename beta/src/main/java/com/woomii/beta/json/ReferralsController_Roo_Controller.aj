// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.json;

import com.woomii.beta.backend.referrals.Referrals;
import com.woomii.beta.frontend.apps.Apps;
import com.woomii.beta.frontend.campaigns.Campaigns;
import com.woomii.beta.json.ReferralsController;
import java.io.UnsupportedEncodingException;
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

privileged aspect ReferralsController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String ReferralsController.create(@Valid Referrals referrals, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, referrals);
            return "referralses/create";
        }
        uiModel.asMap().clear();
        referrals.persist();
        return "redirect:/referralses/" + encodeUrlPathSegment(referrals.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String ReferralsController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Referrals());
        return "referralses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String ReferralsController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("referrals", Referrals.findReferrals(id));
        uiModel.addAttribute("itemId", id);
        return "referralses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String ReferralsController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("referralses", Referrals.findReferralsEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Referrals.countReferralses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("referralses", Referrals.findAllReferralses(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "referralses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String ReferralsController.update(@Valid Referrals referrals, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, referrals);
            return "referralses/update";
        }
        uiModel.asMap().clear();
        referrals.merge();
        return "redirect:/referralses/" + encodeUrlPathSegment(referrals.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String ReferralsController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Referrals.findReferrals(id));
        return "referralses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String ReferralsController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Referrals referrals = Referrals.findReferrals(id);
        referrals.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/referralses";
    }
    
    void ReferralsController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("referrals_created_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void ReferralsController.populateEditForm(Model uiModel, Referrals referrals) {
        uiModel.addAttribute("referrals", referrals);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("appses", Apps.findAllAppses());
        uiModel.addAttribute("campaignses", Campaigns.findAllCampaignses());
    }
    
    String ReferralsController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
