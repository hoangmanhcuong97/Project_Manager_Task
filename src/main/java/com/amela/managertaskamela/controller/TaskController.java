package com.amela.managertaskamela.controller;

import com.amela.managertaskamela.model.Task;
import com.amela.managertaskamela.service.task.TaskService;
import org.springframework.data.domain.*;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
@EnableSpringDataWebSupport
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    private String success = null;

    @GetMapping("/list-task")
    public String liskTask(Model model, @RequestParam Optional<Integer> p, HttpServletRequest request) {
        String status = request.getParameter("status") == null ? "" : request.getParameter("status");
        String statusView = request.getParameter("status") == null ? "All" : request.getParameter("status");
        String title = request.getParameter("title") == null ? "" : request.getParameter("title");
        if (status.equals("All")) {
            status = "";
        }
        Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("content"));
        List<Task> listTask = taskService.findAllByPage(title, status, pageable);
        int countTask = taskService.countTasks(title, status);
        Page<Task> page = new PageImpl<>(listTask, pageable, countTask);
        model.addAttribute("page", page);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        model.addAttribute("statusView", statusView);
        model.addAttribute("success", success);
        success = null;
        return "task/tasks";
    }

    @GetMapping("/create-task")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/task/createTask");
        modelAndView.addObject("createTask", new Task());
        return modelAndView;
    }
    @PostMapping("/create-task")
    public ModelAndView saveTask(@ModelAttribute("task") Task task) {
        task.setStatus("Open");
        taskService.createTask(task);
        ModelAndView modelAndView = new ModelAndView("/task/createTask");
        modelAndView.addObject("createTask", new Task());
        modelAndView.addObject("message", "New task created successfully");
        return modelAndView;
    }
    @GetMapping("/detail-task/{id}")
    public ModelAndView showDetailTask(@PathVariable("id") int id){
        Task task = taskService.findById(id);
        ModelAndView mav = new ModelAndView("/task/detailTask");
        mav.addObject("detailTask", task);
        return mav;
    }
    @GetMapping("/edit-task/{id}")
    public ModelAndView showEditTask (@PathVariable int id){
        Task task = taskService.findById(id);
        ModelAndView  modelAndView = new ModelAndView("/task/editTask");
        modelAndView.addObject("editTask", task);
        return modelAndView;
    }

    @PostMapping("/edit-task/{id}")
    public ModelAndView updateTask(@ModelAttribute("task") Task task) {
        taskService.updateTask(task);
        ModelAndView modelAndView = new ModelAndView("/task/editTask");
        modelAndView.addObject("editTask", task);
        modelAndView.addObject("message","Update thành công");
        return modelAndView;
    }

    @GetMapping ("/delete-task/{id}")
    public String deleteTask(@PathVariable("id") int id){
        taskService.deleteTaskById(id);
        return "redirect:/list-task";
    }

    @GetMapping("/exportCsv")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Danh_sach_task_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Task> tasks = taskService.findAllTasks();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Task ID", "Title", "Content", "Status"};
        String[] nameMapping = {"id", "title", "content", "status"};

        csvWriter.writeHeader(csvHeader);

        for (Task task : tasks) {
            csvWriter.write(task, nameMapping);
        }

        csvWriter.close();

    }

}