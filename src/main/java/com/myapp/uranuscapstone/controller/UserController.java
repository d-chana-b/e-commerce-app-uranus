package com.myapp.uranuscapstone.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.jar.Attributes;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.uranuscapstone.model.CartItems;
import com.myapp.uranuscapstone.model.Coupon;
import com.myapp.uranuscapstone.model.OrderDetail;
import com.myapp.uranuscapstone.model.Product;
import com.myapp.uranuscapstone.model.Role;
import com.myapp.uranuscapstone.model.User;
import com.myapp.uranuscapstone.repository.CouponRepository;
import com.myapp.uranuscapstone.repository.OrderDetailRepository;
import com.myapp.uranuscapstone.repository.ProductRepository;
import com.myapp.uranuscapstone.repository.UserRepository;
import com.myapp.uranuscapstone.service.CartItemService;
import com.myapp.uranuscapstone.service.CategoryService;
import com.myapp.uranuscapstone.service.CouponService;
import com.myapp.uranuscapstone.service.OrderDetailService;
//import com.myapp.uranuscapstone.service.CustomProductService;
import com.myapp.uranuscapstone.service.ProductService;
import com.myapp.uranuscapstone.service.RoleService;
import com.myapp.uranuscapstone.service.UserService;

@Controller
public class UserController {

	private final int DISCOUNT_DIVIDER = 100; // for cart items diveder
	private final int DELIVERY_DATE = 7;

	@Autowired
	UserRepository userRepo;

	@Autowired
	CategoryService categoryService;

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		 model.addAttribute("user", new User());

		return "/User/Registration";
	}

	@GetMapping("/login")
	public String showLogin() {
		return "/User/Login";
	}
	
	@Autowired
	RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@PostMapping("/register")
	public String processRegister(@ModelAttribute("user") User user,HttpServletRequest request)throws ServletException {

		/*
		 * BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); String
		 * encodedPassword = passwordEncoder.encode(user.getPassword());
		 * user.setPassword(encodedPassword);
		 */

		String password = user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		List<Role> roles = new ArrayList<>();
		roles.add(roleService.show((long) 2));
		user.setRoles(roles);
		userRepo.save(user);
		request.login(user.getEmail(), password);
		//return "/User/register_success";
		return "redirect:/"; //homepage
	}

	////// INDEX SESSION
	// @RequestMapping ("/productService")
	@Autowired
	private ProductService productService;

	// @Autowired
	// private CustomProductService customProductService;


	
	// landing page of the application
	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("couponList",couponService.getAllCoupon());
		return "/User/index";
	}
	
	/*
	 * @GetMapping("/index") public String userPage() { return "/User/userIndex"; }
	 */

	@GetMapping("/index/product_list")
	public String listProduct(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("category", categoryService.getAllCategory());
		// model.addAttribute("products", customProductService.getAllByName());
		return "/User/productList";
	}

	@GetMapping("/test")
	public String testPage() {
		return "/User/homepage";
	}

	// @GetMapping("/test")
	// public String testPage() {
	// return "/User/productDetails";
	// }

	@GetMapping("/index/product/{id}")
	public String categorylist(@PathVariable(value = "id") String id, Model model) {
		model.addAttribute("products", productService.getByCategoryName(id));
		model.addAttribute("category", categoryService.getAllCategory());
		// model.addAttribute("products", customProductService.getAllByName());
		return "redirect:/index/product_list";
	}

	@GetMapping("/index/category/{id}")
	public String shopByCategory(Model model, @PathVariable int id) {
		model.addAttribute("category", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProductByCategoryId(id));
		return "/User/productList";
	}

	// go to product details page
	@GetMapping("/index/product_details/{id}")
	public String showProductDetails(Model model, @PathVariable Long id) {
		/* Product product = productService.showProductById(id); */
		model.addAttribute("products", productService.showProductById(id));
		/* model.addAttribute("product",product); */
		return "/User/productDetails";
	}

	// add to cart functionality
	@Autowired
	CartItemService cartItemService;

	@Autowired
	UserService userService;

	@PostMapping("/add_to_cart/{id}")
	public String addToCart(@PathVariable Long id,Model model,RedirectAttributes attributes) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Product product = productService.showProductById(id);
		User user = userService.getAuthUser(auth);
		int quantity = 1;
		cartItemService.addProduct(product, quantity, user);
		attributes.addFlashAttribute("addItem","Successfully added to the cart!");
		return "redirect:/index/product_list";
	}

	// cart controller
	@Autowired
	CouponService couponService;

	@GetMapping("/cart")
	public String cartUser(Model model, @RequestParam("couponName") Optional<String> couponName) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getAuthUser(auth);
		Coupon coupon = couponService.findCoupon(couponName);
		String applyCoupon = "";
		double total = 0;
		double subTotal = 0;
		List<CartItems> cartItems = cartItemService.index(user);

		if (cartItems.isEmpty()) {
			model.addAttribute("cartItem", "NoData");
		} else {
			total = cartItems.stream().map(item -> item.getProduct().getPrice() * item.getQuantity())
					.mapToDouble(num -> num.doubleValue()).sum();
			subTotal = cartItems.stream().map(item -> item.getProduct().getPrice() * item.getQuantity())
					.mapToDouble(num -> num.doubleValue()).sum();
			model.addAttribute("cartItem", cartItems);
		}

		if (coupon == null && couponName.isPresent()) {
			model.addAttribute("message", "Invalid coupon code");
		} else if (coupon != null) {
			LocalDate couponEndDate = coupon.getEvent().getEndDate().toLocalDate();
			if (couponEndDate.isBefore(LocalDate.now())) {
				model.addAttribute("message", "Coupon already expired!");
			} else {
				double couponDiscount = coupon.getDiscount();
				List<CartItems> qualifiedProduct = cartItems.stream()
						.filter(it -> it.getProduct().getCategory().equals(coupon.getCategory()))
						.collect(Collectors.toList());
				double totalOfQualifiedProduct = qualifiedProduct.stream()
						.map(item -> item.getProduct().getPrice() * item.getQuantity())
						.mapToDouble(num -> num.doubleValue()).sum() * (couponDiscount / DISCOUNT_DIVIDER);
				model.addAttribute("success", (int)couponDiscount + "% on all " + coupon.getCategory().getName()+".");
				applyCoupon = coupon.getCouponName();
				total -= totalOfQualifiedProduct;
			}
		}
		int cartSize = cartItems.size();
		
		model.addAttribute("couponApply",applyCoupon);
		model.addAttribute("cartSize",cartSize);
		model.addAttribute("subTotal",subTotal);
		model.addAttribute("total", total);
		return "/User/cart";
	}

	@GetMapping("/cart_minus/{id}")
	public String updateMinusCart(@PathVariable Integer id) {
		CartItems cartItem = cartItemService.show(id);
		int total = cartItem.getQuantity() - 1;
		if (total <= 1) {
			total = 1;
		}
		cartItem.setQuantity(total);
		cartItemService.save(cartItem);
		return "redirect:/cart";
	}

	@GetMapping("/cart_add/{id}")
	public String updateAddCart(@PathVariable Integer id) {
		CartItems cartItem = cartItemService.show(id);
		int total = cartItem.getQuantity() + 1;
		cartItem.setQuantity(total);
		cartItemService.save(cartItem);
		return "redirect:/cart";
	}

	@GetMapping("/delete_cart_item")
	public String delete(Integer id) {
		cartItemService.delete(id);
		return "redirect:/cart";
	}

	@Autowired
	OrderDetailRepository orderDetailRepo;

	@Autowired
	OrderDetailService orderDetailService;

	// check out
	@GetMapping("/checkout/{id}")
	public String showCheckout(Model model,@PathVariable String id) {
		// Optional<OrderDetail> orderDetails =
		// orderDetailService.getOrderDetailsById(id);
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userDetails = userService.getAuthUser(auth);
		Optional<String> couponName = Optional.ofNullable(id);
		Coupon coupon = couponService.findCoupon(couponName);
		double total = 0;
		List<CartItems> cartItems = cartItemService.index(userDetails);
		if (cartItems.isEmpty()) {
			model.addAttribute("cartItem", "NoData");
		} else {
			total = cartItems.stream().map(item -> item.getProduct().getPrice() * item.getQuantity())
					.mapToDouble(num -> num.doubleValue()).sum();
			model.addAttribute("cartItem", cartItems);
		}
		
		if (coupon == null && couponName.isPresent()) {
			model.addAttribute("message", "Invalid coupon code");
		} else if (coupon != null) {
			LocalDate couponEndDate = coupon.getEvent().getEndDate().toLocalDate();
			if (couponEndDate.isBefore(LocalDate.now())) {
				model.addAttribute("message", "Coupon already expired!");
			} else {
				double couponDiscount = coupon.getDiscount();
				List<CartItems> qualifiedProduct = cartItems.stream()
						.filter(it -> it.getProduct().getCategory().equals(coupon.getCategory()))
						.collect(Collectors.toList());
				double totalOfQualifiedProduct = qualifiedProduct.stream()
						.map(item -> item.getProduct().getPrice() * item.getQuantity())
						.mapToDouble(num -> num.doubleValue()).sum() * (couponDiscount / DISCOUNT_DIVIDER);
				model.addAttribute("success", (int)couponDiscount + "% on all " + coupon.getCategory().getName()+".");
				//applyCoupon = coupon.getCouponName();
				total -= totalOfQualifiedProduct;
			}
		}
		model.addAttribute("couponApply",id);
		int cartSize = cartItems.size();
		model.addAttribute("cartSize",cartSize);
		model.addAttribute("cartItem", cartItems);
		model.addAttribute("total", total);
		// gawa ka na lang ng if else statement later
		model.addAttribute("user", userDetails);
		return "/User/checkout";
	}

	@PostMapping("/checkout/{id}")
	public String checkout(@RequestParam("totalAmount") Double totalAmount,@PathVariable String id, RedirectAttributes attributes) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Date orderDate = Date.valueOf(LocalDate.now());
		Date deliveryDate = Date.valueOf(LocalDate.now().plusDays(DELIVERY_DATE));
		User user = userService.getAuthUser(auth);
		// List<CartItems> cartItems = cartItemService.index(user, "IC");
		// cartItems.forEach(it->it.setStatus("CO"));
		OrderDetail order = new OrderDetail();
		order.setUser(user);
		order.setTotalAmount(totalAmount);
		order.setOrderDate(orderDate);
		order.setDeliveryDate(deliveryDate);
		orderDetailRepo.save(order);
		// cartItemService.saveAll(cartItems);

		
		attributes.addFlashAttribute("orderSuccess", "Order successfully placed"); // redirect attribute para sa mga e
		return "redirect:/cart";
	}
	
	
	//checkout if no coupon apply
	@GetMapping("/checkout")
	public String showCheckout(Model model) {
		// Optional<OrderDetail> orderDetails =
		// orderDetailService.getOrderDetailsById(id);
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userDetails = userService.getAuthUser(auth);
		double total = 0;
		List<CartItems> cartItems = cartItemService.index(userDetails);
		if (cartItems.isEmpty()) {
			model.addAttribute("cartItem", "NoData");
		} else {
			total = cartItems.stream().map(item -> item.getProduct().getPrice() * item.getQuantity())
					.mapToDouble(num -> num.doubleValue()).sum();
			model.addAttribute("cartItem", cartItems);
		}
		int cartSize = cartItems.size();
		model.addAttribute("cartSize",cartSize);
		model.addAttribute("cartItem", cartItems);
		model.addAttribute("total", total);
		// gawa ka na lang ng if else statement later
		model.addAttribute("user", userDetails);
		return "/User/checkout";
	}
	
	@PostMapping("/checkout")
	public String checkout(@RequestParam("totalAmount") Double totalAmount, RedirectAttributes attributes) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Date orderDate = Date.valueOf(LocalDate.now());
		Date deliveryDate = Date.valueOf(LocalDate.now().plusDays(DELIVERY_DATE));
		User user = userService.getAuthUser(auth);
		// List<CartItems> cartItems = cartItemService.index(user, "IC");
		// cartItems.forEach(it->it.setStatus("CO"));
		OrderDetail order = new OrderDetail();
		order.setUser(user);
		order.setTotalAmount(totalAmount);
		order.setOrderDate(orderDate);
		order.setDeliveryDate(deliveryDate);
		orderDetailRepo.save(order);
		// cartItemService.saveAll(cartItems);

		
		attributes.addFlashAttribute("orderSuccess", "Order successfully placed"); // redirect attribute para sa mga e
		return "redirect:/cart";
	}


	// order details
	@GetMapping("/order_details")
	public String orderDetails(Model model) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getAuthUser(auth);
		List<OrderDetail> orderDetail = orderDetailService.index(user);
		List<CartItems> cartItem = cartItemService.index(user);
		model.addAttribute("user", user);// for displaying the name of the user
		model.addAttribute("cartItem", cartItem);// displaying the purchased product
		model.addAttribute("orderDetails", orderDetail);
		return "/User/OrderDetails";
	}

	// for contact link
	@GetMapping("/contact")
	public String showContact() {
		return "/User/contact";
	}

	// for about link
	@GetMapping("/About")
	public String showAbout() {
		return "/User/About";
	}
}
