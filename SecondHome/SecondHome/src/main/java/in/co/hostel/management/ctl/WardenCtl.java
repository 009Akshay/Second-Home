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

import in.co.hostel.management.dto.UserDTO;
import in.co.hostel.management.dto.WardenDTO;
import in.co.hostel.management.exception.DuplicateRecordException;
import in.co.hostel.management.form.WardenForm;
import in.co.hostel.management.service.HostelServiceInt;
import in.co.hostel.management.service.UserServiceInt;
import in.co.hostel.management.service.WardenServiceInt;




@Controller
@RequestMapping("/ctl/warden")
public class WardenCtl extends BaseCtl {

	@Autowired
	private WardenServiceInt service;
	
	@Autowired 
	private HostelServiceInt hostelService;
	
	@Autowired
	private UserServiceInt userService;
	
	@ModelAttribute
	public void preload(Model model) {
			model.addAttribute("hostelList",hostelService.list());
			UserDTO uDto=new UserDTO();
			uDto.setRoleId(3L);
			model.addAttribute("userList",userService.search(uDto));
	}
	
	@GetMapping
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") WardenForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			WardenDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "warden";
	}

	@PostMapping
	public String submit(@Valid @ModelAttribute("form") WardenForm form, BindingResult bindingResult,
			HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/warden";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "warden";
				}
				WardenDTO bean = (WardenDTO) form.getDTO();
				bean.setHostel(hostelService.findBypk(bean.getHostelId()));
				bean.setUser(userService.findBypk(bean.getUserId()));
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Warden update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Warden Added Successfully!!!!");
				}
				return "warden";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "warden";
		} 
		return "";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") WardenForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/warden/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/warden";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					WardenDTO dto = new WardenDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		WardenDTO dto = (WardenDTO) form.getDTO();

		WardenDTO uDto = (WardenDTO) session.getAttribute("Warden");
		List<WardenDTO> list = service.search(dto, pageNo, pageSize);
		List<WardenDTO> totallist = service.search(dto);
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
		return "wardenList";
	}

}
