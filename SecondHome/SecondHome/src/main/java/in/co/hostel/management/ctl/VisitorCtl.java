package in.co.hostel.management.ctl;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.co.hostel.management.dto.VisitorDTO;
import in.co.hostel.management.exception.DuplicateRecordException;
import in.co.hostel.management.form.VisitorForm;
import in.co.hostel.management.service.VisitorServiceInt;




@Controller
@RequestMapping("/ctl/visitor")
public class VisitorCtl extends BaseCtl {

	@Autowired
	private VisitorServiceInt service;
	
	
	@ModelAttribute
	public void preload(Model model) {
	}
	
	@GetMapping
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") VisitorForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			VisitorDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "visitor";
	}

	@PostMapping
	public String submit(@Valid @ModelAttribute("form") VisitorForm form, BindingResult bindingResult,
			HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/visitor";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "visitor";
				}
				VisitorDTO bean = (VisitorDTO) form.getDTO();
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Visitor update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Visitor Added Successfully!!!!");
				}
				return "visitor";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "visitor";
		} 
		return "";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") VisitorForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/visitor/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/visitor";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					VisitorDTO dto = new VisitorDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		VisitorDTO dto = (VisitorDTO) form.getDTO();

		VisitorDTO uDto = (VisitorDTO) session.getAttribute("Visitor");
		List<VisitorDTO> list = service.search(dto, pageNo, pageSize);
		List<VisitorDTO> totallist = service.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return "visitorList";
	}

}
