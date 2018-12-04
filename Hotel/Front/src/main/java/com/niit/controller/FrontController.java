package com.niit.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.niit.Back.DAO.BillingAddressDAO;
import com.niit.Back.DAO.BookingDAO;
import com.niit.Back.DAO.CategoryDAO;
import com.niit.Back.DAO.CustomerDAO;
import com.niit.Back.DAO.HotelDAO;
import com.niit.Back.DAO.PaymentDAO;
import com.niit.Back.DAO.WishDAO;
import com.niit.Back.model.BillingAddress;
import com.niit.Back.model.Category;
import com.niit.Back.model.Customer;
import com.niit.Back.model.CustomerBookings;
import com.niit.Back.model.Hotels;
import com.niit.Back.model.Payment;
import com.niit.Back.model.UserCredentials;
import com.niit.Back.model.Wish;

@Controller
public class FrontController {

	@Autowired
	CustomerDAO CustomerDAO;

	@Autowired
	CategoryDAO categorydao;
	@Autowired
	HotelDAO hoteldao;

	@Autowired
	PaymentDAO payment;

	@Autowired
	BookingDAO bookingDAO;

	@Autowired
	private WishDAO wishDAO;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	BillingAddressDAO billingAddressDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public String index(Model M) {

		M.addAttribute("title", "home");
		M.addAttribute("userClickHome", true);
		System.out.println(categorydao.showAllCategory());
		M.addAttribute("catlist", categorydao.showAllCategory());
		return "page";

	}

	@RequestMapping(value = "about")
	public String about(Model M) {

		M.addAttribute("title", "About Us");
		M.addAttribute("userClickAbout", true);
		M.addAttribute("catlist", categorydao.showAllCategory());
		return "page";

	}

	@RequestMapping(value = "contact")
	public String contact(Model M) {

		M.addAttribute("title", "Contact Us");
		M.addAttribute("userClickContact", true);
		M.addAttribute("catlist", categorydao.showAllCategory());
		return "page";

	}

	@RequestMapping(value = "sendmail")
	public String sendmail(HttpServletRequest request) {
		try {
			String recipientAddress = "blushboutiquecenter@gmail.com";
			String uname = request.getParameter("uname");
			String usubject = request.getParameter("usubject");
			String uphno = request.getParameter("uphno");
			String umessage = request.getParameter("umessage");
			String finalmessage = "Hi Admin, " + umessage + " Contact me in:" + uphno + "\n\n\n regards\n\n" + uname;
			SimpleMailMessage email = new SimpleMailMessage();
			email.setTo(recipientAddress);
			email.setSubject(usubject);
			email.setText(finalmessage);
			mailSender.send(email);
		} catch (Exception e) {

		}
		return "redirect:/contact";
	}

	@RequestMapping(value = "login")
	public String login(Model M) {

		M.addAttribute("title", "Sign In");
		M.addAttribute("regsucces",false);
		M.addAttribute("loginerror", false);
		M.addAttribute("userClickLogin", true);
		M.addAttribute("catlist", categorydao.showAllCategory());
		return "page";

	}

	@RequestMapping(value = "flogin")
	public String faillogin(Model M) {

		M.addAttribute("title", "Sign In");
		M.addAttribute("userClickLogin", true);
		M.addAttribute("regsucces",false);
		M.addAttribute("loginerror", true);
		M.addAttribute("catlist", categorydao.showAllCategory());
		return "page";

	}

	@RequestMapping(value = "loginsucess")
	public String loginsuccess(HttpSession session, Model M)

	{
		if (session.getAttribute("pid") == null) {
			String useremail = SecurityContextHolder.getContext().getAuthentication().getName();
			Customer c = CustomerDAO.showcustomer(useremail);
			Collection<GrantedAuthority> authority = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities();
			for (GrantedAuthority permission : authority) {
				if (permission.getAuthority().equals("ROLE_USER")) {
					session.setAttribute("username", c.getName());
					session.setAttribute("useremail", c.getEmailId());
					session.setAttribute("userpayid", c.getPaymentid());
					session.setAttribute("Userlogin", true);
					session.setAttribute("payamount", payment.show(c.getPaymentid()).size());
					return "redirect:/Hotelsgrid";
				} else {
					session.setAttribute("username", "Administrator");
					session.setAttribute("Userlogin", false);
					return "redirect:/";
				}
			}
		}
		else
		{
			String useremail = SecurityContextHolder.getContext().getAuthentication().getName();
			Customer c = CustomerDAO.showcustomer(useremail);
			session.setAttribute("username", c.getName());
			session.setAttribute("useremail", c.getEmailId());
			session.setAttribute("userPaymentid", c.getPaymentid());
			session.setAttribute("Userlogin", true);
			session.setAttribute("Paymentsize", payment.show(c.getPaymentid()).size());
			int proid = Integer.parseInt(session.getAttribute("pid").toString());
			int qnty = Integer.parseInt(session.getAttribute("qid").toString());
			return "redirect:addHotels/"+proid+"?qnty="+qnty;
			
		}
		return "redirect:/";
	}
	

	@RequestMapping(value = "registration")
	public String registration(Model M) {

		M.addAttribute("title", "Sign Up");
		M.addAttribute("customer", new Customer());
		M.addAttribute("userClickRegistration", true);
		M.addAttribute("catlist", categorydao.showAllCategory());
		return "page";

	}

	@RequestMapping(value = "Hotelsgrid")
	public String Hotelsgrid(Model M) {
		M.addAttribute("hotellist", hoteldao.showallHotel());
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("title", "HotelsGrid");
		M.addAttribute("userClickHotelsGrid", true);

		return "page";

	}

	@RequestMapping(value = "ADMIN/Hotels")
	public String hotel(Model M) {

		M.addAttribute("hotellist", hoteldao.showallHotel());
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("status", false);
		M.addAttribute("hotel", new Hotels());
		M.addAttribute("title", "Hotels");
		M.addAttribute("userClickHotels", true);
		M.addAttribute("edit", false);
		return "page";

	}

	@RequestMapping(value = "info/{HotelsName}")
	public String Hotelinfo(@PathVariable String hotelName, Model M) {

		M.addAttribute("hotellist", hoteldao.showallHotel());
		M.addAttribute("Hotels", hoteldao.showHotel(hotelName));
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("title", "Hotels Info");
		M.addAttribute("userClickHotelsInfo", true);

		return "page";

	}

	@RequestMapping(value = "prodcat/{catid}")
	public String hotelcat(@PathVariable int catid, Model M) {

		M.addAttribute("hotellist", hoteldao.getHotel(catid));
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("title", "Hotelcat");
		M.addAttribute("userClickHotelscat", true);

		return "page";

	}

	@RequestMapping(value = "ADMIN/setHotels")
	public String setpro(@Valid @ModelAttribute("Hotels") Hotels hotel, BindingResult result, Model M) {
		System.out.println("print me");
		if (result.hasErrors()) {
			System.out.println("inside error");
			System.out.println(result.getErrorCount());
			M.addAttribute("hotellist", hoteldao.showallHotel());
			M.addAttribute("catlist", categorydao.showAllCategory());
			M.addAttribute("status", true);
			M.addAttribute("edit", false);
			M.addAttribute("hotel", hotel);
			M.addAttribute("title", "Hotels");
			M.addAttribute("userClickHotels", true);
			return "page";
		}

		try {
			System.out.println("inside try");

			hoteldao.addHotel(hotel);
			uploadfile(hotel.getHotelid(), hotel.getPimage());
			return "redirect:/ADMIN/Hotelss";
		} catch (Exception e) {
			System.out.println("inside catch" + e.getMessage());

			M.addAttribute("hotellist", hoteldao.showallHotel());
			M.addAttribute("catlist", categorydao.showAllCategory());
			M.addAttribute("status", true);
			M.addAttribute("edit", false);
			M.addAttribute("hotel", hotel);
			M.addAttribute("title", "Hotels");
			M.addAttribute("userClickHotels", true);
			return "page";
		}

	}

	void uploadfile(int hotelId, MultipartFile f) throws Exception {
		String path = "D:\\Eclipse\\Eclipse-Workspace\\BoutiqueFront\\src\\main\\webapp\\resources\\pimages\\";
		path = path + String.valueOf(hotelId + ".jpg");
		if (!f.isEmpty()) {
			byte[] b = f.getBytes();
			System.out.println(b);
			BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(new File(path)));
			bs.write(b);
			bs.close();
			Thread.sleep(10000);
		}
	}

	@RequestMapping(value = "ADMIN/delprod/{HotelsName}")
	public String delprod(@PathVariable String hotelName, Model M) {
		try {
			hoteldao.deleteHotel(hotelName);
			Hotels hotel = hoteldao.showHotel(hotelName);
			String path = "D:\\Eclipse\\Eclipse-Workspace\\BoutiqueFront\\src\\main\\webapp\\resources\\pimages\\";
			path = path + String.valueOf(hotel.getHotelid() + ".jpg");
			Path paths = Paths.get(path);
			if (Files.exists(paths)) {
				try {
					Files.delete(paths);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return "redirect:/ADMIN/Hotelss";
		} catch (Exception e) {
			M.addAttribute("hotellist", hoteldao.showallHotel());
			M.addAttribute("catlist", categorydao.showAllCategory());
			M.addAttribute("status", false);
			M.addAttribute("hotel", new Hotels());
			M.addAttribute("title", "Hotels");
			M.addAttribute("userClickHotels", true);
			M.addAttribute("edit", false);
			return "page";
		}
	}

	@RequestMapping(value = "ADMIN/editprod/{HotelsName}")
	public String editHotels(@PathVariable String hotelName, Hotels hotel, Model M) {

		M.addAttribute("hotellist", hoteldao.showallHotel());
		M.addAttribute("status", false);
		M.addAttribute("edit", true);
		M.addAttribute("hotel", hoteldao.showHotel(hotelName));
		M.addAttribute("title", "Edit Hotels");
		M.addAttribute("userClickHotels", true);
		M.addAttribute("catlist", categorydao.showAllCategory());
		return "page";

	}

	@RequestMapping(value = "ADMIN/Category")
	public String category(Model M) {

		M.addAttribute("categorylist", categorydao.showAllCategory());
		M.addAttribute("status", false);
		M.addAttribute("edit", false);
		M.addAttribute("category", new Category());
		M.addAttribute("title", "Category");
		M.addAttribute("userClickCategory", true);
		return "page";

	}

	@RequestMapping(value = "ADMIN/setcategory")
	public String setcat(@Valid @ModelAttribute("category") Category category, BindingResult result, Model M) {
		System.out.println("print me");
		if (result.hasErrors()) {
			M.addAttribute("categorylist", categorydao.showAllCategory());
			M.addAttribute("status", "true");
			M.addAttribute("edit", false);
			M.addAttribute("category", category);
			M.addAttribute("title", "Category");
			M.addAttribute("userClickCategory", true);
			return "page";

		} else {
			try {
				categorydao.addCategory(category);
				return "redirect:/ADMIN/Category";
			} catch (Exception e) {
				M.addAttribute("categorylist", categorydao.showAllCategory());
				M.addAttribute("status", "true");
				M.addAttribute("edit", false);
				M.addAttribute("category", new Category());
				M.addAttribute("title", "Category");
				M.addAttribute("userClickCategory", true);
				return "page";
			}
		}

	}

	@RequestMapping(value = "ADMIN/delcat")
	public String delcat(@RequestParam(name = "cname") String Categoryname, Model M) {
		try {
			categorydao.deleteCategory(Categoryname);
			return "redirect:/ADMIN/Category";
		} catch (Exception e) {
			M.addAttribute("categorylist", categorydao.showAllCategory());
			M.addAttribute("status", "true");
			M.addAttribute("edit", false);
			M.addAttribute("category", new Category());
			M.addAttribute("title", "Category");
			M.addAttribute("userClickCategory", true);
			return "page";
		}
	}

	@RequestMapping(value = "ADMIN/editcat")
	public String showcategory(@RequestParam(name = "cname") String Categoryname, Model M) {

		M.addAttribute("categorylist", categorydao.showAllCategory());
		M.addAttribute("status", false);
		M.addAttribute("edit", true);
		M.addAttribute("category", categorydao.showCategory(Categoryname));
		M.addAttribute("title", "Category");
		M.addAttribute("userClickCategory", true);
		return "page";

	}

	@RequestMapping(value = "setcustomer")
	public String addcustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Register");
			model.addAttribute("userClickRegistration", true);
			model.addAttribute("customer", customer);
			model.addAttribute("status", true);
			model.addAttribute("edit", false);

			return "page";
		}

		try {
			CustomerDAO.addCustomer(customer);
			model.addAttribute("title", "Sign In");
			model.addAttribute("regsucces",true);
			model.addAttribute("loginerror", false);
			model.addAttribute("userClickLogin", true);
			model.addAttribute("catlist", categorydao.showAllCategory());
			return "page";
		} catch (Exception e) {
			model.addAttribute("title", "Register");
			model.addAttribute("userClickRegistration", true);
			model.addAttribute("customer", customer);
			model.addAttribute("status", true);
			return "page";
		}

	}

	@RequestMapping(value = "addHotels/{id}")
	public String addPayment(@PathVariable int id, @RequestParam(value = "qnty") int qnty, Model M, HttpSession session) {
		
	if(session.getAttribute("userpayid")!= null) {
	
		ArrayList<Payment> Paymentlist = (ArrayList<Payment>) payment
				.show(Integer.parseInt(session.getAttribute("userPaymentid").toString()));
		Hotels Hotels = hoteldao.getHotel(id);
		if (Hotels.getRoomsavailabe() >= qnty) {

			for (Payment PaymentItem : Paymentlist) {
				if (PaymentItem.getPid() == id) {
					PaymentItem.setQty(qnty);
					PaymentItem.setTotal(qnty * Hotels.getPrice());
					payment.add(PaymentItem);
					return "redirect:/Payment/viewPayment";
				}
			}

			Payment Payment = new Payment();
			Payment.setPid(Integer.parseInt(session.getAttribute("userPaymentid").toString()));
			Payment.setPid(Hotels.getHotelid());
			Payment.setPname(Hotels.getHotelname());
			Payment.setQty(qnty);
			Payment.setPprice(Hotels.getPrice());
			Payment.setTotal(Hotels.getPrice());
			payment.add(Payment);

			return "redirect:/Payment/viewPayment";

		} else {
			M.addAttribute("msg", true);
			M.addAttribute("Hotelslist", hoteldao.showallHotel());
			M.addAttribute("Hotels", hoteldao.getHotel(id));
			M.addAttribute("catlist", categorydao.showAllCategory());
			M.addAttribute("title", "Hotels Info");
			M.addAttribute("userClickHotelsInfo", true);
			return "page";

		}
	}
	else {
		session.setAttribute("pid",id);
		session.setAttribute("qid", qnty);
		return "redirect:/login";
	}
	}

	@RequestMapping(value = "Payment/viewPayment")
	public String viewPayment(Model M, HttpSession session) {
		ArrayList<Payment> Paymentlist = (ArrayList<Payment>) payment
				.show(Integer.parseInt(session.getAttribute("userPaymentid").toString()));
		M.addAttribute("title", "Payment");
		M.addAttribute("Paymentlist", Paymentlist);
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("userClickPayment", true);
		session.setAttribute("Paymentsize", Paymentlist.size());
		return "page";

	}

	@RequestMapping(value = "deletePayment/{id}")
	public String deletePayment(@PathVariable int id, Model M, HttpSession session) {
		payment.delete(id);
		return "redirect:/Payment/viewPayment";

	}

	@RequestMapping(value = "Address")
	public String Address(Model M, HttpSession session) {

		M.addAttribute("title", "Address");
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("userClickCheckout", true);
		M.addAttribute("address", new BillingAddress());
		M.addAttribute("addresslist",
				billingAddressDAO.list(Integer.parseInt(session.getAttribute("userPaymentid").toString())));
		return "page";

	}

	@RequestMapping(value = "setaddress")
	public String setCategory(@Valid @ModelAttribute("address") BillingAddress address, BindingResult result, Model M,
			HttpSession session) {

		if (result.hasErrors()) {
			M.addAttribute("title", "Address");
			M.addAttribute("userClickCheckout", true);
			M.addAttribute("catlist", categorydao.showAllCategory());
			M.addAttribute("address", address);
			M.addAttribute("addresslist",
					billingAddressDAO.list(Integer.parseInt(session.getAttribute("userPaymentid").toString())));

			return "page";
		}

		try {

			address.setPayId(Integer.parseInt(session.getAttribute("userPaymentid").toString()));
			billingAddressDAO.add(address);
			return "redirect:/Address";
		} catch (Exception e) {
			M.addAttribute("title", "Address");
			M.addAttribute("userClickCheckout", true);
			M.addAttribute("catlist", categorydao.showAllCategory());
			M.addAttribute("address", address);
			M.addAttribute("addresslist",
					billingAddressDAO.list(Integer.parseInt(session.getAttribute("userPaymentid").toString())));
			return "page";
		}

	}

	@RequestMapping(value = "editadd/{id}")
	public String editAddress(@PathVariable int id, Hotels Hotels, Model M, HttpSession session) {
		M.addAttribute("title", "Address");
		M.addAttribute("userClickCheckout", true);
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("address", billingAddressDAO.show(id));
		M.addAttribute("addresslist",
				billingAddressDAO.list(Integer.parseInt(session.getAttribute("userPaymentid").toString())));
		return "page";
	}

	@RequestMapping(value = "deladd/{id}")
	public String deladd(@PathVariable int id, Model model) {
		billingAddressDAO.delete(id);
		return "redirect:/Address";
	}

	@RequestMapping(value = "invoice/{aid}")
	public String invoice(@PathVariable int aid, Model model, HttpSession session) {
		ArrayList<Payment> Paymentlist = (ArrayList<Payment>) payment
				.show(Integer.parseInt(session.getAttribute("userPaymentid").toString()));
		Long uuid = UUID.randomUUID().getMostSignificantBits();
		String id = "OD" + uuid.toString().replace('-', '2');
		Iterator<Payment> Paymentiterator = Paymentlist.listIterator();
		while (Paymentiterator.hasNext()) {
			Payment Payment = Paymentiterator.next();
			Hotels Hotels = hoteldao.getHotel(Payment.getPid());
			Hotels.setRoomsavailabe(Hotels.getRoomsavailabe() - Payment.getQty());
			hoteldao.addHotel(Hotels);

			CustomerBookings c = new CustomerBookings();
			c.setPid(Payment.getPid());
			c.setId(id);
			c.setAddid(aid);
			c.setDate(new Date());
			c.setPid(Payment.getPid());
			c.setPname(Payment.getPname());
			c.setQty(Payment.getQty());
			c.setSubtotal(Payment.getTotal());
			bookingDAO.insert(c);
			payment.delete(Payment.getId());

		}

		try {
			String recipientAddress = SecurityContextHolder.getContext().getAuthentication().getName();
			Customer customer = CustomerDAO.showcustomer(recipientAddress);
			String uname = customer.getName();
			String usubject = "Order Confirmation";
			String finalmessage = "Hi" + uname + ":,\n\n Your order is confirmed.\n\n Your order number is" + id
					+ "\n\n\n regards\n\n Admin";
			SimpleMailMessage email = new SimpleMailMessage();
			email.setTo(recipientAddress);
			email.setSubject(usubject);
			email.setText(finalmessage);
			mailSender.send(email);
		} catch (Exception e) {

		}

		return "redirect:/viewbill/" + id + "/" + aid;
	}

	@RequestMapping(value = "viewbill/{oid}/{aid}")
	public String viewbill(Model M, HttpSession session, @PathVariable String oid, @PathVariable int aid) {
		List<CustomerBookings> custorder = bookingDAO.viewreceipt(oid);
		M.addAttribute("title", "Order");
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("userClickInvoice", true);
		M.addAttribute("baddress", billingAddressDAO.show(aid));
		M.addAttribute("orderid", oid);
		M.addAttribute("orderdetail", custorder);
		session.setAttribute("Paymentsize",
				payment.show(Integer.parseInt(session.getAttribute("userPaymentid").toString())).size());
		return "page";

	}

	@RequestMapping(value = "viewallbookings")
	public String viewallorders(Model M, HttpSession session) {

		int Paymentid = Integer.parseInt(session.getAttribute("userPaymentid").toString());
		M.addAttribute("title", "My Orders");
		M.addAttribute("bookinglist", bookingDAO.viewAllbooking(Paymentid));
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("userClickAllBookings", true);
		return "page";
	}

	@RequestMapping(value = "Payment/changepassword")
	public String changepassword(Model model) {

		model.addAttribute("title", "Change Password");

		model.addAttribute("catlist", categorydao.showAllCategory());
		model.addAttribute("userClickChangePassword", true);
		model.addAttribute("usercred", new UserCredentials());
		model.addAttribute("msg", false);
		return "page";

	}

	@RequestMapping(value = "Payment/updatepassword")
	public String updatepassword(@ModelAttribute("usercred") UserCredentials uc, Model model,
			HttpServletRequest request) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		UserCredentials olduc = CustomerDAO.showcred(email);

		String oldpassword = request.getParameter("oldpass");

		if (oldpassword.equals(olduc.getPassword())) {
			olduc.setPassword(uc.getPassword());
			CustomerDAO.saveorupdatepassword(olduc);
			model.addAttribute("title", "Change Password");
			model.addAttribute("catlist", categorydao.showAllCategory());
			model.addAttribute("userClickChangePassword", true);
			model.addAttribute("usercred", new UserCredentials());
			model.addAttribute("msg", true);

		} else {
			model.addAttribute("title", "Change Password");
			model.addAttribute("catlist", categorydao.showAllCategory());
			model.addAttribute("userClickChangePassword", true);
			model.addAttribute("usercred", new UserCredentials());
			model.addAttribute("msg1", true);
		}

		return "page";

	}

	@RequestMapping(value = "/resetpassword")
	public String resetpassword(Model model, HttpServletRequest request) {
		String email = request.getParameter("j_username");

		UserCredentials olduc = CustomerDAO.showcred(email);
		if (olduc != null) {
			String s = UUID.randomUUID().toString().substring(0, 8);
			System.out.println(s);
			String finalmessage = "Hi,\n\n your new password is \n\n " + s + " \n\n regards\n\n Admin";
			SimpleMailMessage pemail = new SimpleMailMessage();
			pemail.setTo(email);
			pemail.setSubject("Your new password for computer mart");
			pemail.setText(finalmessage);
			mailSender.send(pemail);
			olduc.setPassword(s);
			CustomerDAO.saveorupdatepassword(olduc);
			model.addAttribute("title", "Sign In");
			model.addAttribute("catlist", categorydao.showAllCategory());
			model.addAttribute("userClickLogin", true);
			model.addAttribute("loginerror", false);

		} else {
			model.addAttribute("title", "Sign In");
			model.addAttribute("catlist", categorydao.showAllCategory());
			model.addAttribute("userClickLogin", true);
			model.addAttribute("loginerror", true);

		}

		return "page";

	}

	@RequestMapping(value = "Payment/addwish/{id}")
	public String addwish(@PathVariable int id, Model model, HttpSession session) {
		ArrayList<Wish> wishlist = (ArrayList<Wish>) wishDAO
				.show(Integer.parseInt(session.getAttribute("userPaymentid").toString()));
		Hotels Hotels = hoteldao.getHotel(id);
		for (Wish wish : wishlist) {
			if (wish.getPaymentId() == id) {
				wishDAO.add(wish);
				return "redirect:/Payment/viewwish";
			}
		}

		Wish wish = new Wish();
		wish.setHid(id);
		wish.setPaymentId(Integer.parseInt(session.getAttribute("userPaymentid").toString()));
		wish.setHname(Hotels.getHotelname());
		wish.setHprice(Hotels.getPrice());

		wishDAO.add(wish);
		return "redirect:/Payment/viewwish";

	}

	@RequestMapping(value = "Payment/viewwish")
	public String viewPayment1(Model model, HttpSession session) {
		ArrayList<Wish> wishlist = (ArrayList<Wish>) wishDAO
				.show(Integer.parseInt(session.getAttribute("userPaymentid").toString()));
		model.addAttribute("title", "Wishlist");
		model.addAttribute("wishlist", wishlist);
		model.addAttribute("catlist", categorydao.showAllCategory());
		model.addAttribute("userClickWish", true);
		model.addAttribute("msg", false);
		return "page";

	}

	@RequestMapping(value = "deletewish/{id}")
	public String delPayment(@PathVariable int id, Model model) {
		wishDAO.delete(id);
		return "redirect:/Payment/viewwish";
	}

}
