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

import in.co.hostel.management.dto.AllotmentDTO;
import in.co.hostel.management.dto.ApplicationDTO;
import in.co.hostel.management.dto.UserDTO;
import in.co.hostel.management.dto.WardenDTO;
import in.co.hostel.management.exception.DuplicateRecordException;
import in.co.hostel.management.form.ApplicationForm;
import in.co.hostel.management.service.HostelServiceInt;
import in.co.hostel.management.service.WardenServiceInt;
import in.co.hostel.management.service.ApplicationServiceInt;




@Controller
@RequestMapping("/ctl/application")
public class ApplicationCtl extends BaseCtl {

	@Autowired
	private ApplicationServiceInt service;
	
	@Autowired 
	private HostelServiceInt hostelService;
	
	@Autowired
	private WardenServiceInt wardenService;		
	
	@ModelAttribute
	public void preload(Model model) {
				model.addAttribute("hostelList",hostelService.list());
	}
	
	@GetMapping
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") ApplicationForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			ApplicationDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "application";
	}

	@PostMapping
	public String submit(@Valid @ModelAttribute("form") ApplicationForm form, BindingResult bindingResult,
			HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/application";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "application";
				}
				ApplicationDTO bean = (ApplicationDTO) form.getDTO();
				bean.setHostel(hostelService.findBypk(bean.getHostelId()));
				UserDTO uDto=(UserDTO)session.getAttribute("user");
				bean.setUser(uDto);
				bean.setUserId(uDto.getId());
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Application update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Application Added Successfully!!!!");
				}
				return "application";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "application";
		} 
		return "";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") ApplicationForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/application/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/application";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					ApplicationDTO dto = new ApplicationDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		ApplicationDTO dto = (ApplicationDTO) form.getDTO();
		
		
		UserDTO uDto=(UserDTO)session.getAttribute("user");
		if(uDto.getRoleId()==3) {
			WardenDTO wDto=wardenService.findByUserId(uDto.getId());
			dto.setHostelId(wDto.getHostelId());
		}

		List<ApplicationDTO> list = service.search(dto, pageNo, pageSize);
		List<ApplicationDTO> totallist = service.search(dto);
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
		return "applicationList";
	}

}
