package com.niit.Back.DAO;

import java.util.List;

import com.niit.Back.model.Payment;

public interface PaymentDAO

{

	boolean add(Payment payment);
	boolean delete(int id);
    List<Payment> show(int Paymentid);
}
	