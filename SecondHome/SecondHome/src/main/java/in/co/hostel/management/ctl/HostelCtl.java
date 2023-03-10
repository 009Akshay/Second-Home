package in.co.hostel.management.ctl;

import java.util.HashMap;
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

import in.co.hostel.management.dto.HostelDTO;
import in.co.hostel.management.exception.DuplicateRecordException;
import in.co.hostel.management.form.HostelForm;
import in.co.hostel.management.service.HostelServiceInt;


@Controller
@RequestMapping("/ctl/hostel")
public class HostelCtl extends BaseCtl {

	@Autowired
	private HostelServiceInt service;
	
	@ModelAttribute
	public void preload(Model model) {
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("Boys", "Boys");
		map2.put("Girls", "Girls");
		model.addAttribute("type", map2);
		
	}
	@GetMapping
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") HostelForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			HostelDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "hostel";
	}

	@PostMapping
	public String submit(@Valid @ModelAttribute("form") HostelForm form, BindingResult bindingResult,
			HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/hostel";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "hostel";
				}
				HostelDTO bean = (HostelDTO) form.getDTO();
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Hostel update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Hostel Added Successfully!!!!");
				}
				return "hostel";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "hostel";
		} 
		return "";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") HostelForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/hostel/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/hostel";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					HostelDTO dto = new HostelDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		HostelDTO dto = (HostelDTO) form.getDTO();

		HostelDTO uDto = (HostelDTO) session.getAttribute("Hostel");
		List<HostelDTO> list = service.search(dto, pageNo, pageSize);
		List<HostelDTO> totallist = service.search(dto);
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
		return "hostelList";
	}

}
