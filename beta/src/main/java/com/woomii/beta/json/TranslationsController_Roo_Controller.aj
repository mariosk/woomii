// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.json;

import com.woomii.beta.frontend.campaigns.Campaigns;
import com.woomii.beta.frontend.languages.Languages;
import com.woomii.beta.frontend.translations.Translations;
import com.woomii.beta.json.TranslationsController;
import java.io.UnsupportedEncodingException;
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

privileged aspect TranslationsController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String TranslationsController.create(@Valid Translations translations, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, translations);
            return "translationses/create";
        }
        uiModel.asMap().clear();
        translations.persist();
        return "redirect:/translationses/" + encodeUrlPathSegment(translations.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String TranslationsController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Translations());
        return "translationses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String TranslationsController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("translations", Translations.findTranslations(id));
        uiModel.addAttribute("itemId", id);
        return "translationses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String TranslationsController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("translationses", Translations.findTranslationsEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Translations.countTranslationses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("translationses", Translations.findAllTranslationses(sortFieldName, sortOrder));
        }
        return "translationses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String TranslationsController.update(@Valid Translations translations, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, translations);
            return "translationses/update";
        }
        uiModel.asMap().clear();
        translations.merge();
        return "redirect:/translationses/" + encodeUrlPathSegment(translations.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String TranslationsController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Translations.findTranslations(id));
        return "translationses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String TranslationsController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Translations translations = Translations.findTranslations(id);
        translations.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/translationses";
    }
    
    void TranslationsController.populateEditForm(Model uiModel, Translations translations) {
        uiModel.addAttribute("translations", translations);
        uiModel.addAttribute("campaignses", Campaigns.findAllCampaignses());
        uiModel.addAttribute("languageses", Languages.findAllLanguageses());
    }
    
    String TranslationsController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
