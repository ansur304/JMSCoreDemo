package com.ansur.jms;

import java.util.Scanner;

import ptp.ConsumerPTP;
import ptp.ProducerPTP;

public class MainBroker {

	public static void main(String[] args) {
		
		System.out.println("Welcome ! \nEnter the message to send ::");
		Scanner sc = new Scanner(System.in);
		new ProducerPTP().produceMsg(sc.nextLine());
		new ConsumerPTP().consumeMsg();
		sc.close();
	}

}
