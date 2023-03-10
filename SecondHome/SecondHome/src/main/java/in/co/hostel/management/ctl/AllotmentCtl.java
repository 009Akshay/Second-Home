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
import in.co.hostel.management.dto.RoomDTO;
import in.co.hostel.management.dto.UserDTO;
import in.co.hostel.management.dto.WardenDTO;
import in.co.hostel.management.exception.DuplicateRecordException;
import in.co.hostel.management.form.AllotmentForm;
import in.co.hostel.management.service.HostelServiceInt;
import in.co.hostel.management.service.RoomServiceInt;
import in.co.hostel.management.service.WardenServiceInt;
import in.co.hostel.management.util.DataUtility;
import in.co.hostel.management.service.AllotmentServiceInt;
import in.co.hostel.management.service.ApplicationServiceInt;




@Controller
@RequestMapping("/ctl/allotment")
public class AllotmentCtl extends BaseCtl {

	@Autowired
	private AllotmentServiceInt service;
	
	@Autowired 
	private HostelServiceInt hostelService;
	
	@Autowired
	private RoomServiceInt roomService;
			
	@Autowired
	private WardenServiceInt wardenService;			
	
	@Autowired
	private ApplicationServiceInt applicationService;
	
	@ModelAttribute
	public void preload(Model model,@RequestParam(required = false) Long aId) {
		long alId=DataUtility.getLong(String.valueOf(aId));
		RoomDTO rDto=new RoomDTO();
		if(alId>0) {
		ApplicationDTO apDto=applicationService.findBypk(alId);
			rDto.setHostelId(apDto.getHostelId());
		}
		model.addAttribute("roomList",roomService.search(rDto));
	}
	
	@GetMapping
	public String display(@RequestParam(required = false) Long id, Long aId, @ModelAttribute("form") AllotmentForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			AllotmentDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		long alId=DataUtility.getLong(String.valueOf(aId));
		if(alId>0) {
			session.setAttribute("allotId",alId);
		}else {
			return"redirect:/search/allotment";
		}
		return "allotment";
	}

	@PostMapping
	public String submit(@Valid @ModelAttribute("form") AllotmentForm form, BindingResult bindingResult,
			HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/allotment";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "allotment";
				}
				AllotmentDTO bean = (AllotmentDTO) form.getDTO();
				long aId=DataUtility.getLong(String.valueOf(session.getAttribute("allotId")));
				ApplicationDTO aDto=applicationService.findBypk(aId);
				bean.setHostel(aDto.getHostel());
				bean.setUser(aDto.getUser());
				bean.setRoom(roomService.findBypk(bean.getRoomId()));
				bean.setUserId(aDto.getUserId());
				bean.setHostelId(aDto.getHostelId());
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Allotment update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Allotment Added Successfully!!!!");
				}
				return "allotment";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "allotment";
		} 
		return "";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") AllotmentForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/allotment/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/allotment";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					AllotmentDTO dto = new AllotmentDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		AllotmentDTO dto = (AllotmentDTO) form.getDTO();
		
		UserDTO uDto=(UserDTO)session.getAttribute("user");
		if(uDto.getRoleId()==2) {
			dto.setUserId(uDto.getId());
		}else if(uDto.getRoleId()==3) {
			WardenDTO wDto=wardenService.findByUserId(uDto.getId());
			dto.setHostelId(wDto.getHostelId());
		}

		List<AllotmentDTO> list = service.search(dto, pageNo, pageSize);
		List<AllotmentDTO> totallist = service.search(dto);
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
		return "allotmentList";
	}

}
