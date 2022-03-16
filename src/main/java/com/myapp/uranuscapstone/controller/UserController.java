package com.myapp.uranuscapstone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myapp.uranuscapstone.model.Cart;
import com.myapp.uranuscapstone.model.CartItems;
import com.myapp.uranuscapstone.model.Product;
import com.myapp.uranuscapstone.model.User;
import com.myapp.uranuscapstone.repository.CartRepository;
import com.myapp.uranuscapstone.repository.ProductRepository;
import com.myapp.uranuscapstone.repository.UserRepository;
import com.myapp.uranuscapstone.service.CartItemService;
import com.myapp.uranuscapstone.service.CategoryService;
//import com.myapp.uranuscapstone.service.CustomProductService;
import com.myapp.uranuscapstone.service.ProductService;
import com.myapp.uranuscapstone.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	CategoryService categoryService;

	@Autowired
	CartRepository cartRepo;

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());

		return "/User/Registration";
	}

	@GetMapping("/login")
	public String showLogin() {
		return "/User/Login";
	}

	@PostMapping("/process_register")
	public String processRegister(User user) {
		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		 String encodedPassword = passwordEncoder.encode(user.getPassword());
		 user.setPassword(encodedPassword);

		userRepo.save(user);

		return "/User/register_success";
	}

	////// INDEX SESSION
	// @RequestMapping ("/productService")
	@Autowired
	private ProductService productService;

	// @Autowired
	// private CustomProductService customProductService;

	@GetMapping("/index")
	public String userPage() {
		return "/User/index";
	}

	@GetMapping("/index/product_list")
	public String listProduct(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("category", categoryService.getAllCategory());
		// model.addAttribute("products", customProductService.getAllByName());
		return "/User/productList";
	}

	@GetMapping("/test")
	public String testPage() {
		return "/User/productDetails";
	}

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

	//go to product details page
	@GetMapping("/index/product_details/{id}")
	public String showProductDetails(Model model, @PathVariable Long id) {
		/* Product product = productService.showProductById(id); */
		model.addAttribute("products", productService.showProductById(id));
		/* model.addAttribute("product",product); */
		return "/User/productDetails";
	}
	
	//add to cart functionality
	@Autowired
	CartItemService cartItemService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/add_to_cart/{id}")
	public String addToCart(@PathVariable Long id) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Product product = productService.showProductById(id);
		User user = userService.getAuthUser(auth);
		int quantity = 1;
		cartItemService.addProduct(product, quantity, user);
		return "redirect:/index/product_list";
	}
	
	
	// cart controller
	
	@GetMapping("/cart")
	public String cartUser(Model model) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getAuthUser(auth);
		
		List<CartItems> cartItems = cartItemService.index(user);
		
		model.addAttribute("cartItem",cartItems);
		return "/User/cart";
	}
	
	@GetMapping("/cart_minus/{id}")
	public String updateMinusCart(@PathVariable Integer id) {
		CartItems cartItem = cartItemService.show(id);
		int total = cartItem.getQuantity() - 1;
		if(total<=1) {
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

	@GetMapping("/destroy_cart_item")
	public String delete(Integer id) {
		cartItemService.delete(id);
		return "redirect:/cart";
	}

}
