package com.musicapplication.musicapplication.controllers;

import com.musicapplication.musicapplication.entities.User;
import com.musicapplication.musicapplication.services.UserServiceImplementation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;


import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
    @Autowired
    UserServiceImplementation service;
    // Display the payment page
    @GetMapping("/pay")
    public String pay() {

        return "pay";
    }
    // Handle payment success
    @GetMapping("/payment-success")
    public String paymentSuccess(HttpSession session) {
        // Retrieve user email from the session

        String mail = (String) session.getAttribute("email");
        // Retrieve user from the database based on email

        User u = service.getUser(mail);
        // Set the user's premium status to true

        u.setPremium(true);
        // Update the user in the database

        service.updateUser(u);
        // Redirect to the user login page

        return "userlogin";
    }
    // Handle payment failure

    @GetMapping("/payment-failure")
    // Redirect to the user login page in case of payment failure

    public String paymentFailure() {
        return "userlogin";
    }
    // Create a Razorpay order for payment

    @SuppressWarnings("finally")
    @PostMapping("/createOrder")
    @ResponseBody
    public String createOrder() {
// Set the amount for the premium subscription
        int amount = 10;
        Order order = null;
        try {
            // Initialize Razorpay client with API key and secret

            RazorpayClient razorpay = new RazorpayClient("rzp_test_kTu9geCj6cJJZ8", "O6c5XcLHFkBiku1JrgTqzF9I");
            // Create a JSON object for order creation

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount * 100); // amount in the smallest currency unit
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "order_rcptid_11");
            // Create the order using Razorpay API

            order = razorpay.orders.create(orderRequest);

        } catch (RazorpayException e) {
            e.printStackTrace();
        } finally {
            return order.toString();
        }
    }
    // Verify the payment signature

    @PostMapping("/verify")
    @ResponseBody
    public boolean verifyPayment(@RequestParam String orderId, @RequestParam String paymentId,
                                 @RequestParam String signature) {
        try {
            // Initialize Razorpay client with your API key and secret
            RazorpayClient razorpayClient = new RazorpayClient("rzp_test_kTu9geCj6cJJZ8", "O6c5XcLHFkBiku1JrgTqzF9I");
            // Create a signature verification data string
            String verificationData = orderId + "|" + paymentId;

            // Use Razorpay's utility function to verify the signature
            boolean isValidSignature = Utils.verifySignature(verificationData, signature, "O6c5XcLHFkBiku1JrgTqzF9I");

            return isValidSignature;
        } catch (RazorpayException e) {
            e.printStackTrace();
            return false;
        }
    }
}