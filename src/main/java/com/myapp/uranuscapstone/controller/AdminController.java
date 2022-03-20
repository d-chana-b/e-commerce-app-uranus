package com.myapp.uranuscapstone.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.uranuscapstone.dto.CouponDTO;
import com.myapp.uranuscapstone.model.Category;
import com.myapp.uranuscapstone.model.Coupon;

import com.myapp.uranuscapstone.model.LoginAdmin;

import com.myapp.uranuscapstone.model.Event;

import com.myapp.uranuscapstone.model.Product;
import com.myapp.uranuscapstone.service.CategoryService;
import com.myapp.uranuscapstone.service.CouponService;
import com.myapp.uranuscapstone.service.EventService;
import com.myapp.uranuscapstone.service.ProductService;

@Controller
public class AdminController {

	// @Autowired
	// ProductRepository productRepository;

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/product-photos";
	/*
	 * @GetMapping("/admin") public String adminHome() { return "/Admin/adminHome";
	 * }
	 * 
	 * /* @GetMapping("/admin/product") public String adminProduct() { return
	 * "/Admin/productlist"; }
	 */

	/*
	 * @GetMapping("/admin/product/add") public String getProduct() { return
	 * "/Admin/addproduct"; }
	 */

	// login page of admin
	@GetMapping("/loginAdmin")
	public String showLogin() {
		return "/Admin/Login";
	}

	// Check for Credentials
	@PostMapping("/loginAdmin")
	public String login(@ModelAttribute(name = "loginForm") LoginAdmin login, Model m) {
		String uname = login.getUsername();
		String pass = login.getPassword();
		if (uname.equals("Admin") && pass.equals("Admin@123")) {
			m.addAttribute("uname", uname);
			m.addAttribute("pass", pass);
			return "redirect:/admin";
		}
		m.addAttribute("error", "Incorrect Username & Password");
		return "/Admin/Login";

	}

	@GetMapping("/admin")
	public String listProduct(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "/Admin/adminHome";
	}

	@GetMapping("/admin/product/add")
	public String createProductForm(Model model) {
		// create student object to hold student form data
		model.addAttribute("product", new Product());
		model.addAttribute("category", categoryService.getAllCategory());
		return "/Admin/addproduct";
	}

	/*
	 * @GetMapping("/product/edit/{id}") public String editProductForm(@PathVariable
	 * Long id, Model model) { model.addAttribute("product",
	 * productService.getProductById(id)); return "/Admin/addproduct"; }
	 * 
	 * 
	 * /* @PostMapping("/admin/product/add") public String
	 * saveProduct(@ModelAttribute("product") Product product) {
	 * productService.saveProduct(product); return "redirect:/admin"; }
	 */

	@PostMapping("/admin/product/add")
	public String saveProduct(@ModelAttribute("product") Product product,
			@RequestParam("productImage") MultipartFile file, @RequestParam("imageName") String imgName,
			RedirectAttributes attributes) throws IOException {

		/*
		 * Product existingProduct =new Product();
		 * existingProduct.setId(product.getId());
		 * existingProduct.setProductName(product.getProductName());
		 * existingProduct.setCategory(product.getCategory());
		 * existingProduct.setPrice(product.getPrice());
		 * existingProduct.setProductImageName(product.getProductImageName());
		 */

		String imageUUId;
		if (!file.isEmpty()) {
			imageUUId = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDirectory, imageUUId);
			Files.write(fileNameAndPath, file.getBytes());
		} else {
			imageUUId = imgName;
		}
		product.setProductImageName(imageUUId);
		productService.saveProduct(product);
		String message = (product.getProductId() == null) ? "Product successfully added!"
				: "Product successfully updated!";
		attributes.addFlashAttribute("message", message);
		return "redirect:/admin";
	}

	@GetMapping("admin/update/{id}")
	public String updateProduct(@PathVariable Long id, Model model) {
		model.addAttribute("category", categoryService.getAllCategory());
		Optional<Product> product = productService.getProductById(id);

		if (product.isPresent()) {

			model.addAttribute("product", product.get());
			return "/Admin/addproduct";
		} else {
			return "404";
		}

	}

	// handler method to handle delete student request

	@GetMapping("admin/delete/{id}")
	public String deleteProduct(@PathVariable Long id, RedirectAttributes attributes) {
		productService.deleteProductById(id);
		attributes.addFlashAttribute("deleteMessage", "Product succesfully deleted!");
		return "redirect:/admin";
	}

	////////////////////// Coupon Session

	@Autowired
	CouponService couponService;

	@GetMapping("/admin/coupon")
	public String listCoupon(Model model) {

		model.addAttribute("coupons", couponService.getAllCoupon());
		return "/Admin/coupon";
	}

	@GetMapping("/admin/coupon/add")
	public String createCouponForm(Model model) {
		// create coupon
		model.addAttribute("coupon", new Coupon());
		model.addAttribute("event", eventService.getAllEvent());
		model.addAttribute("category", categoryService.getAllCategory());
		return "/Admin/addcoupon";
	}

	@PostMapping("/admin/coupon/add")
	public String saveCoupon(Coupon coupon, RedirectAttributes attributes) {

		couponService.saveCoupon(coupon);
		String message = (coupon.getId()==null) ? "Coupon successfully added!"
				: "Coupon successfully updated!";
		attributes.addFlashAttribute("message", message);
		return "redirect:/admin/coupon";
	}

	@GetMapping("admin/updatecoupon/{id}")
	public String updateCoupon(@PathVariable int id, Model model) {
		model.addAttribute("event", eventService.getAllEvent());
		model.addAttribute("category", categoryService.getAllCategory());
		Optional<Coupon> coupon = couponService.getCouponById(id);

		if (coupon.isPresent()) {

			model.addAttribute("coupon", coupon.get());
			return "/Admin/addcoupon";
		} else {
			return "404";
		}
	}

	@GetMapping("admin/deletecoupon/{id}")
	public String deleteCoupon(@PathVariable Long id, RedirectAttributes attributes) {
		couponService.deleteCouponById(id);
		attributes.addFlashAttribute("deleteMessage", "Coupon succesfully deleted!");
		return "redirect:/admin/coupon";
	}

	////////// EVENTS
	@Autowired
	EventService eventService;

	@GetMapping("/admin/event")
	public String listEvent(Model model) {
		model.addAttribute("events", eventService.getAllEvent());
		return "/Admin/events";
	}

	@GetMapping("/admin/event/add")
	public String createEventForm(Model model) {
		// create events
		model.addAttribute("event", new Event());
		return "/Admin/addevents";
	}

	@PostMapping("/admin/event/add")
	public String saveEvent(Event event,RedirectAttributes attributes) {

		eventService.saveEvent(event);
		String message = (event.getId()==null) ? "Event successfully added!"
				: "Event successfully updated!";
		attributes.addFlashAttribute("message", message);
		return "redirect:/admin/event";
	}

	@GetMapping("admin/updateevent/{id}")
	public String updateEvent(@PathVariable int id, Model model) {
		Optional<Event> event = eventService.getEventById(id);

		if (event.isPresent()) {

			model.addAttribute("event", event.get());
			return "/Admin/addevents";
		} else {
			return "404";
		}
	}

	@GetMapping("admin/deleteevent/{id}")
	public String deleteEvent(@PathVariable Long id,RedirectAttributes attributes) {
		eventService.deleteEventById(id);
		attributes.addFlashAttribute("deleteMessage", "Event succesfully deleted!");
		return "redirect:/admin/event";
	}

}
