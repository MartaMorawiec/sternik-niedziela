package pl.sternik.mm.niedziela.web.controlers.th;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sternik.mm.niedziela.entities.Moneta;
import pl.sternik.mm.niedziela.entities.Status;
import pl.sternik.mm.niedziela.services.KlaserService;
import pl.sternik.mm.niedziela.services.NotificationService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
public class MonetyController {

    @Autowired
    // @Qualifier("spring-data")
    @Qualifier("tablica")
    // @Qualifier("lista")
    private KlaserService klaserService;

    @Autowired
    private NotificationService notifyService;

    @ModelAttribute("statusyAll")
    public List<Status> populateStatusy() {
        return Arrays.asList(Status.ALL);
    }

    @ModelAttribute("MyMessages")
    public List<NotificationService.NotificationMessage> populateMessages() {
        System.out.println("nic");
        return notifyService.getNotificationMessages();
    }


    @GetMapping(value = "/monety/{id}")
    public String view(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Moneta> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            Moneta moneta = result.get();
            model.addAttribute("moneta", moneta);
            return "th/moneta";
        } else {
            notifyService.addErrorMessage("Cannot find moneta #" + id);
            model.clear();
            return "redirect:/monety";
        }
    }

    @RequestMapping(value = "/monety/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Moneta> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Moneta> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            Moneta moneta = result.get();
            return new ResponseEntity<Moneta>(moneta, HttpStatus.OK);
        } else {
            notifyService.addErrorMessage("Cannot find moneta #" + id);
            model.clear();
            return new ResponseEntity<Moneta>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/monety", params = { "save" }, method = RequestMethod.POST)
    public String saveMoneta(Moneta moneta, BindingResult bindingResult, ModelMap model) {
        // @Valid
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            model.addAttribute("MyMessages",  notifyService.getNotificationMessages());
            return "th/moneta";
        }

        if (moneta.getStatus() == Status.NOWA) {
            moneta.setStatus(Status.STARA);
        }

        Optional<Moneta> result = klaserService.edit(moneta);
        if (result.isPresent())
            notifyService.addInfoMessage("Zapis udany");
        else
            notifyService.addErrorMessage("Zapis NIE udany");
        model.clear();
        return "redirect:/monety";
    }

    @RequestMapping(value = "/monety", params = { "create" }, method = RequestMethod.POST)
    public String createMoneta(Moneta moneta, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            model.addAttribute("MyMessages",  notifyService.getNotificationMessages());
            return "th/moneta";
        }
        klaserService.create(moneta);
        model.clear();
        notifyService.addInfoMessage("Zapis nowej udany");
        return "redirect:/monety";
    }

    @RequestMapping(value = "/monety", params = { "remove" }, method = RequestMethod.POST)
    public String removeRow(final Moneta moneta, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("remove"));
        Optional<Boolean> result = klaserService.deleteById(rowId.longValue());
        return "redirect:/monety";
    }

    @RequestMapping(value = "/monety/create", method = RequestMethod.GET)
    public String showMainPages(final Moneta moneta) {
        // Ustawiamy date nowej monety, na dole strony do dodania
        moneta.setDataNabycia(Calendar.getInstance().getTime());
        return "th/moneta";
    }
}