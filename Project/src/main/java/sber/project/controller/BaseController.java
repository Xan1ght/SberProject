package sber.project.controller;

import sber.project.entity.ArchiveTask;
import sber.project.entity.Base;
import sber.project.entity.User;
import sber.project.enums.Category;
import sber.project.service.ArchiveService;
import sber.project.service.BaseService;
import sber.project.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class BaseController {
    private UserService userService;
    private BaseService baseService;
    private ArchiveService archiveService;

    @Autowired
    public BaseController(UserService userService, BaseService baseService, ArchiveService archiveService) {
        this.userService = userService;
        this.baseService = baseService;
        this.archiveService = archiveService;
    }
//
//    @GetMapping()
//    public String getAll(Model model) {
//        model.addAttribute("base", new Base());
//        return "main";
//    }
//
//    @GetMapping("/main")
//    public String main(Model model) {
//        model.addAttribute("base", new Base());
//        return "main";
//    }
//
//    @PostMapping("/main")
//    public String addProduct(@Valid @ModelAttribute("base") Base baseModel, BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails userDetails) {
//        if (bindingResult.hasErrors()) {
//            return "main";
//        }
//        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
//        baseModel.setUser(user);
//        baseService.createOrUpdateBase(baseModel, user);
//        return "main";
//    }
//
//    @GetMapping("/main/{id}")
//    public String getById(@PathVariable int id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
//        Base base = baseService.getBaseById(id, user);
//        model.addAttribute("task", base);
//        return "info";
//    }
//
//    @GetMapping("/main/all")
//    public String getAllBase(Model model, @AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
//        model.addAttribute("all", baseService.getAllBase(user));
//        return "all";
//    }
//
//    @PostMapping("/main/edit/{id}")
//    public String editBase(@PathVariable("id") int id,
//                           @Valid @ModelAttribute("base") Base baseModel, BindingResult bindingResult,
//                           @AuthenticationPrincipal UserDetails userDetails,
//                           Model model) {
//        if (bindingResult.hasErrors()) {
//            return "edit";
//        }
//        System.out.println(12121212);
//        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
//        baseService.createOrUpdateBase(baseModel, user);
//        return "edit";
//    }
//
//    @GetMapping("/main/edit/{id}")
//    public String editBase(@ModelAttribute("base") Base baseModel) {
//        return "/edit";
//    }
//
//    @PostMapping("/delete/{id}")
//    public String delete(@PathVariable int id,
//                         @AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
//        baseService.deleteBaseById(id, user);
//        return "all";
//    }
//
//    @GetMapping("/main/all/completed")
//    public String getAllBaseComplete(Model model, @AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
//        model.addAttribute("all", baseService.findByActive(true, user));
//        return "all";
//    }
//
//    @GetMapping("/main/all/notcompleted")
//    public String getAllBaseNotComplete(Model model, @AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
//        model.addAttribute("all", baseService.findByActive(false, user));
//        return "all";
//    }
//
//    @GetMapping("/main/all/forTime")
//    public String getAllBaseTime(Model model, @RequestParam(required = false) LocalDateTime date, @AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
//        model.addAttribute("all", baseService.findByTime(date, user));
//        return "all";
//    }
//
//    @GetMapping("/main/all/forCategory")
//    public String getAllForCategory(Model model, @RequestParam(required = false) Category category, @AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
//        model.addAttribute("all", baseService.findByCategory(category, user));
//        return "all";
//    }
//
//    @GetMapping("/main/all/forName")
//    public String getAllBaseName(Model model, @RequestParam(required = false) String search, @AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
//        model.addAttribute("all", baseService.findByName(search, user));
//        return "all";
//    }
//
//    @GetMapping("archive/{id}")
//    public String archived(@PathVariable int id, @ModelAttribute("base") Base baseModel, @AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
//        Base base = baseService.getBaseById(id, user);
//        ArchiveTask archiveTask = new ArchiveTask(base.getId(), base.getName(), base.getDescription(), base.getTime(), base.getActive(), base.getRating(), base.getCategory(), base.getRepeatable(), base.getUser());
//        baseService.deleteBaseById(id, user);
//        archiveService.createOrUpdateArchive(archiveTask, user);
//        return "all";
//    }
//
//    @GetMapping("main/all/all")
//    public String allAll(Model model,@AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
//        List<Base> bases = baseService.getAllBase(user);
//        List<ArchiveTask> archiveTasks = archiveService.getAllArchive(user);
//        model.addAttribute("all", bases);
//        model.addAttribute("archive", archiveTasks);
//        return "all";
//    }
//
//    @GetMapping("main/all/allSort")
//    public String AllSort(Model model, @AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
//        List<Base> bases = baseService.sortByRating(user);
//        model.addAttribute("all", bases);
//        return "all";
//    }
//
//    @GetMapping("/main/archive/{id}")
//    public String getByIdArchive(@PathVariable int id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
//        ArchiveTask archiveTask = archiveService.getArchiveById(id,user);
//        model.addAttribute("task", archiveTask);
//        return "info";
//    }
//
//    @Scheduled(initialDelay = 2000, fixedRate = 3000)
//    @Async
//    public void nextTime(@AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
//        List<Base> bases = baseService.findByTime(LocalDateTime.now(), user);
//        baseService.nextTime(bases);
//    }
    @GetMapping()
    public String getAll(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
        model.addAttribute("base", new Base());
        model.addAttribute("all", baseService.getAllBase(user));
        return "main";
    }

    @GetMapping("/main")
    public String main(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
        model.addAttribute("base", new Base());
        model.addAttribute("all", baseService.getAllBase(user));
        return "main";
    }

    @PostMapping("/main")
    public String addProduct(@Valid @ModelAttribute("base") Base baseModel, BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            return "main";
        }
        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
        baseModel.setUser(user);
        baseService.createOrUpdateBase(baseModel, user);
        return "redirect:/";
    }

    @GetMapping("/main/{id}")
    public String getById(@PathVariable int id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
        Base base = baseService.getBaseById(id, user);
        model.addAttribute("task", base);
        return "info";
    }

    @PostMapping("/main/edit/{id}")
    public String editBase(@PathVariable("id") int id,
                           @Valid @ModelAttribute("base") Base baseModel, BindingResult bindingResult,
                           @AuthenticationPrincipal UserDetails userDetails,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
        baseService.createOrUpdateBase(baseModel, user);
        return "redirect:/";
    }

    @GetMapping("/main/edit/{id}")
    public String editBase(@ModelAttribute("base") Base baseModel) {
        return "edit";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id,
                         @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
        baseService.deleteBaseById(id, user);
        archiveService.deleteTaskArchiveById(id, user);
        return "redirect:/";
    }

    @GetMapping("/main/completed")
    public String getAllBaseComplete(@ModelAttribute("base") Base baseModel, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
        model.addAttribute("all", baseService.findByActive(true, user));
        return "main";
    }

    @GetMapping("/main/notcompleted")
    public String getAllBaseNotComplete(@ModelAttribute("base") Base baseModel, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
        model.addAttribute("all", baseService.findByActive(false, user));
        return "main";
    }

    @GetMapping("/main/forTime")
    public String getAllBaseTime(@ModelAttribute("base") Base baseModel, Model model, @RequestParam(required = false) LocalDateTime date, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
        model.addAttribute("all", baseService.findByTime(date, user));
        return "main";
    }

    @GetMapping("/main/forCategory")
    public String getAllForCategory(@ModelAttribute("base") Base baseModel, Model model, @RequestParam(required = false) Category category, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
        model.addAttribute("all", baseService.findByCategory(category, user));
        return "main";
    }

    @GetMapping("/main/forName")
    public String getAllBaseName(@ModelAttribute("base") Base baseModel, Model model, @RequestParam(required = false) String search, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
        model.addAttribute("all", baseService.findByName(search, user));
        return "main";
    }

    @GetMapping("/archive/{id}")
    public String archived(@PathVariable int id, @ModelAttribute("base") Base baseModel, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
        Base base = baseService.getBaseById(id, user);
        ArchiveTask archiveTask = new ArchiveTask(base.getId(), base.getName(), base.getDescription(), base.getTime(), base.getActive(), base.getRating(), base.getCategory(), base.getRepeatable(), base.getUser());
        baseService.deleteBaseById(id, user);
        archiveService.createOrUpdateArchive(archiveTask, user);
        return "redirect:/";
    }

    @GetMapping("/main/all")
    public String allAll(@ModelAttribute("base") Base baseModel, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
        List<Base> bases = baseService.getAllBase(user);
        List<ArchiveTask> archiveTasks = archiveService.getAllArchive(user);
        model.addAttribute("all", bases);
        model.addAttribute("archive", archiveTasks);
        return "main";
    }

    @GetMapping("/main/allSort")
    public String allSort(@ModelAttribute("base") Base baseModel, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
        List<Base> bases = baseService.sortByRating(user);
        model.addAttribute("all", bases);
        return "main";
    }

    @GetMapping("/main/archive/{id}")
    public String getByIdArchive(@PathVariable int id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
        ArchiveTask archiveTask = archiveService.getArchiveById(id,user);
        model.addAttribute("task", archiveTask);
        return "info";
    }

    @Scheduled(initialDelay = 2000, fixedRate = 3000)
    @Async
    public void nextTime(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername()).orElseThrow();
        List<Base> bases = baseService.findByTime(LocalDateTime.now(), user);
        baseService.nextTime(bases);
    }
}