package com.woomii.beta.json;
import com.woomii.beta.backend.transactions.Transactions;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Transactions.class)
@Controller
@RequestMapping("/transactionses")
@RooWebScaffold(path = "transactionses", formBackingObject = Transactions.class)
public class TransactionsController {
}
