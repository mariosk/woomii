package com.woomii.beta.json;
import com.woomii.beta.frontend.customers.Customers;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Customers.class)
@Controller
@RequestMapping("/customerses")
@RooWebScaffold(path = "customerses", formBackingObject = Customers.class)
public class CustomersController {
}
