package com.store.greenShoes.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.store.greenShoes.DTO.OrderDTO;
import com.store.greenShoes.DTO.ProductWithImageDTO;
import com.store.greenShoes.model.BillingAddress;
import com.store.greenShoes.model.Color;
import com.store.greenShoes.model.OrderDetails;
import com.store.greenShoes.model.Orders;
import com.store.greenShoes.model.PaymentInformation;
import com.store.greenShoes.model.Product;
import com.store.greenShoes.model.ProductSizeColor;
import com.store.greenShoes.model.ShippingAddress;
import com.store.greenShoes.model.Size;
import com.store.greenShoes.model.Users;
import com.store.greenShoes.repository.BillingAddressRepository;
import com.store.greenShoes.repository.ColorRepository;
import com.store.greenShoes.repository.ImageRepository;
import com.store.greenShoes.repository.OrderDetailsRepository;
import com.store.greenShoes.repository.OrdersRepository;
import com.store.greenShoes.repository.PaymentRepository;
import com.store.greenShoes.repository.ProductRepository;
import com.store.greenShoes.repository.ProductSizeColorRepository;
import com.store.greenShoes.repository.ShippingAddressRepository;
import com.store.greenShoes.repository.SizeRepository;
import com.store.greenShoes.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	OrdersRepository ordersRepository;
	@Autowired
	OrderDetailsRepository orderDetailRepository;
	@Autowired
	ProductSizeColorRepository productSizeColorRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SizeRepository sizeRepository;
	@Autowired
	ColorRepository colorRepository;
	@Autowired
	ShippingAddressRepository shippingAddressRepo;
	@Autowired
	BillingAddressRepository billingAddressRepo;
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	ShoppingCartServices shoppingcart;
	@Autowired
	ImageRepository imageRepository;

	public OrderDTO postorder(OrderDTO orderData, Users customer) {
		Orders order = new Orders();
		float total = 0;
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDate now = LocalDate.now();  
//		Date myDate = new Date();
		order.setOrderDate(now);
		order.setTotalPrice(orderData.getTotal());
		Users user = null;
		if (customer != null) {
			user = usersRepository.getReferenceById(customer.getUserId());
		}
		ShippingAddress shippingAddress;
		PaymentInformation paymentInformation;
		BillingAddress billingAddress;
		if (orderData.getShippingAddress() != null) {
			shippingAddress = shippingAddressRepo.save(orderData.getShippingAddress());
		} else {
			shippingAddress = user.getShippingAddress();
		}
		if (orderData.getBillingAddress() != null) {
			billingAddress = billingAddressRepo.save(orderData.getBillingAddress());
		} else {
			billingAddress = user.getBillingAddress();
		}
		if (orderData.getPaymentInformation() != null) {
			paymentInformation = orderData.getPaymentInformation();
			paymentInformation.setBillingAddress(billingAddress);
			paymentInformation = paymentRepository.save(paymentInformation);
		} else {
			paymentInformation = user.getPaymentInformation();
		}
		order.setPayment(paymentInformation);
		order.setShippingAddress(shippingAddress);
		if (user != null) {
			order.setUser(user);
		}
		order = ordersRepository.save(order);
		// List<ProductSizeColorQuantityDTO>
		// pscList=orderData.getProductSizeColorQuantityList();
		orderData.setBillingAddress(billingAddress);
		orderData.setPaymentInformation(paymentInformation);
		orderData.setShippingAddress(shippingAddress);
		List<ProductWithImageDTO> pscList = orderData.getProductWithImageDTO();
		for (ProductWithImageDTO psc : pscList) {
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setOrder(order);
			Product product = productRepository.getReferenceById(psc.getProductId());
			Size size = sizeRepository.getReferenceById(psc.getSizeId());
			Color color = colorRepository.getReferenceById(psc.getColorId());
			ProductSizeColor psc1 = productSizeColorRepository.findByProductSizeColor(product, size, color);
			orderDetails.setProductSizeColor(psc1);
			psc1 = productSizeColorRepository.getReferenceById(psc1.getId());
			orderDetails.setQuantity(psc.getQuantity());
			psc1.setQuantity(psc1.getQuantity() - psc.getQuantity());
			productSizeColorRepository.save(psc1);
			orderDetails.setPrice(psc.getQuantity() * psc1.getProductId().getPrice());
			//total+=orderDetails.getPrice();
			orderDetailRepository.save(orderDetails);
		}if(orderData.getCartId()!=null) {
		shoppingcart.removeProducts(orderData.getCartId());}
		orderData.setOrderId(order.getOrderID());
		return orderData;
	}

	public List<OrderDTO> getAllOrders(Long uid) {
		List<Orders> orders = ordersRepository.findByUser(usersRepository.getReferenceById(uid));
		List<OrderDTO> orderDTOList = new ArrayList<>();
		for (Orders order : orders) {
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setOrderId(order.getOrderID());
			orderDTO.setUserId(uid);
			orderDTO.setOrderDate(order.getOrderDate());
			orderDTO.setPaymentInformation(order.getPayment());
			orderDTO.setShippingAddress(order.getShippingAddress());
			orderDTO.setTotal(order.getTotalPrice());
			List<OrderDetails> orderDetails = orderDetailRepository.findByOrder(order);
			List<ProductWithImageDTO> productSizeColorList = new ArrayList<>();
			for (OrderDetails orderDetail : orderDetails) {
				//ProductSizeColorDTO pscDTO = new ProductSizeColorDTO();
				ProductWithImageDTO pscDTO=new ProductWithImageDTO();
				pscDTO.setProductId(orderDetail.getProductSizeColor().getProductId().getId());
				pscDTO.setColorId(orderDetail.getProductSizeColor().getColorId().getID());
				pscDTO.setSizeId(orderDetail.getProductSizeColor().getSizeId().getID());
				pscDTO.setQuantity(orderDetail.getQuantity());
				pscDTO.setImage(imageRepository.getByProductId(orderDetail.getProductSizeColor().getProductId().getId()).get(0));
				productSizeColorList.add(pscDTO);
			}
			orderDTO.setProductWithImageDTO(productSizeColorList);
			orderDTOList.add(orderDTO);
		}

		return orderDTOList;
	}

	public OrderDTO getOrder(Long oid) {
		Orders singleOrder = ordersRepository.getReferenceById(oid);
		if (singleOrder == null) {
			return null;
		}
		List<OrderDetails> singleOrderDetails = orderDetailRepository.findByOrder(singleOrder);
		List<ProductWithImageDTO> singleOrderPSC = new ArrayList<>();
		for (OrderDetails o : singleOrderDetails) {
			ProductWithImageDTO pscDTO = new ProductWithImageDTO();
			pscDTO.setProductId(o.getProductSizeColor().getProductId().getId());
			pscDTO.setColorId(o.getProductSizeColor().getColorId().getID());
			pscDTO.setSizeId(o.getProductSizeColor().getSizeId().getID());
			pscDTO.setQuantity(o.getQuantity());
			pscDTO.setImage(imageRepository.getByProductId(o.getProductSizeColor().getProductId().getId()).get(0));
			singleOrderPSC.add(pscDTO);
		}
		OrderDTO singleOrderDTO = new OrderDTO();
		singleOrderDTO.setOrderId(singleOrder.getOrderID());
		singleOrderDTO.setOrderDate(singleOrder.getOrderDate());
		singleOrderDTO.setPaymentInformation(singleOrder.getPayment());
		singleOrderDTO.setShippingAddress(singleOrder.getShippingAddress());
		singleOrderDTO.setProductWithImageDTO(singleOrderPSC);
		return singleOrderDTO;
	}
	@Transactional
	public Object DeleteOrder(Long oid) {
		Orders order=ordersRepository.getReferenceById(oid);
		if(order!=null) {
			List<OrderDetails> orders= orderDetailRepository.findByOrder(order);
			for(OrderDetails singleOrder: orders) {
				ProductSizeColor psc = productSizeColorRepository.getReferenceById(singleOrder.getProductSizeColor().getId());
				psc.setQuantity(psc.getQuantity()+singleOrder.getQuantity());
				productSizeColorRepository.save(psc);
				
			}
		orderDetailRepository.deleteByOrder(order);
		ordersRepository.deleteById(oid);
		return ResponseEntity.ok("Cancelled Successfully");}
		return ResponseEntity.badRequest().body("No Orders with this ID");
	}

}
