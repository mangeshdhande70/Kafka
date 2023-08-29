package com.mangesh.bindings;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;


import com.mangesh.invoice.model.PosInvoice;
import com.mangesh.utils.HadoopRecord;
import com.mangesh.utils.Notification;

public interface IPosListenerBinding {
	
	
	@Input("notification-input-channel")
    KStream<String, PosInvoice> notificationInputStream();

    @Output("notification-output-channel")
    KStream<String, Notification> notificationOutputStream();

    @Input("hadoop-input-channel")
    KStream<String, PosInvoice> hadoopInputStream();

    @Output("hadoop-output-channel")
    KStream<String, HadoopRecord> hadoopOutputStream();
    
//    
//    @Input("input-channel-notification")
//	KStream<String, String> inputKStream();

}
