package com.mangesh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mangesh.datagenerator.InvoiceGenerator;
import com.mangesh.kakfa.service.KafkaProducerService;

@SpringBootApplication
public class SpringCloudStreamProducerApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamProducerApplication.class, args);
	}
	

    @Autowired
    private KafkaProducerService producerService;

    @Autowired
    private InvoiceGenerator invoiceGenerator;

    @Value("${application.configs.invoice.count}")
    private int INVOICE_COUNT;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        for (int i = 0; i < INVOICE_COUNT; i++) {
            producerService.sendMessage(invoiceGenerator.getNextInvoice());
            Thread.sleep(1000);
        }
    }

}
