// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.json;

import com.woomii.beta.backend.endusers.EndUsers;
import com.woomii.beta.frontend.apps.Apps;
import com.woomii.beta.json.EndUsersController;
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

privileged aspect EndUsersController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String EndUsersController.create(@Valid EndUsers endUsers, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, endUsers);
            return "enduserses/create";
        }
        uiModel.asMap().clear();
        endUsers.persist();
        return "redirect:/enduserses/" + encodeUrlPathSegment(endUsers.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String EndUsersController.createForm(Model uiModel) {
        populateEditForm(uiModel, new EndUsers());
        return "enduserses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String EndUsersController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("endusers", EndUsers.findEndUsers(id));
        uiModel.addAttribute("itemId", id);
        return "enduserses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String EndUsersController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("enduserses", EndUsers.findEndUsersEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) EndUsers.countEndUserses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("enduserses", EndUsers.findAllEndUserses(sortFieldName, sortOrder));
        }
        return "enduserses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String EndUsersController.update(@Valid EndUsers endUsers, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, endUsers);
            return "enduserses/update";
        }
        uiModel.asMap().clear();
        endUsers.merge();
        return "redirect:/enduserses/" + encodeUrlPathSegment(endUsers.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String EndUsersController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, EndUsers.findEndUsers(id));
        return "enduserses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String EndUsersController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        EndUsers endUsers = EndUsers.findEndUsers(id);
        endUsers.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/enduserses";
    }
    
    void EndUsersController.populateEditForm(Model uiModel, EndUsers endUsers) {
        uiModel.addAttribute("endUsers", endUsers);
        uiModel.addAttribute("appses", Apps.findAllAppses());
    }
    
    String EndUsersController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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