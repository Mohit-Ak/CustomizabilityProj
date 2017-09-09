package com.ub.customizability.actions.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
/**
 * NOT USED FOR CUSTOMIZABILITY PROJECT
 */
/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
}
