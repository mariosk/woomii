// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.json;

import com.woomii.beta.backend.demographics.Demographics;
import com.woomii.beta.backend.endusers.EndUsers;
import com.woomii.beta.json.DemographicsController;
import com.woomii.beta.types.AgeRangeType;
import com.woomii.beta.types.SexType;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect DemographicsController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String DemographicsController.create(@Valid Demographics demographics, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, demographics);
            return "demographicses/create";
        }
        uiModel.asMap().clear();
        demographics.persist();
        return "redirect:/demographicses/" + encodeUrlPathSegment(demographics.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String DemographicsController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Demographics());
        return "demographicses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String DemographicsController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("demographics", Demographics.findDemographics(id));
        uiModel.addAttribute("itemId", id);
        return "demographicses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String DemographicsController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("demographicses", Demographics.findDemographicsEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Demographics.countDemographicses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("demographicses", Demographics.findAllDemographicses(sortFieldName, sortOrder));
        }
        return "demographicses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String DemographicsController.update(@Valid Demographics demographics, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, demographics);
            return "demographicses/update";
        }
        uiModel.asMap().clear();
        demographics.merge();
        return "redirect:/demographicses/" + encodeUrlPathSegment(demographics.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String DemographicsController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Demographics.findDemographics(id));
        return "demographicses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String DemographicsController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Demographics demographics = Demographics.findDemographics(id);
        demographics.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/demographicses";
    }
    
    void DemographicsController.populateEditForm(Model uiModel, Demographics demographics) {
        uiModel.addAttribute("demographics", demographics);
        uiModel.addAttribute("enduserses", EndUsers.findAllEndUserses());
        uiModel.addAttribute("agerangetypes", Arrays.asList(AgeRangeType.values()));
        uiModel.addAttribute("sextypes", Arrays.asList(SexType.values()));
    }
    
    String DemographicsController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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