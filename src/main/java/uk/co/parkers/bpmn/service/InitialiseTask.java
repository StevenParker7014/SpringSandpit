package uk.co.parkers.bpmn.service;

import org.activiti.bpmn.model.ServiceTask;
import org.springframework.stereotype.Component;

@Component
public class InitialiseTask extends ServiceTask {

	public void storeEntity(){
		System.err.println("BOO");
	}


}
