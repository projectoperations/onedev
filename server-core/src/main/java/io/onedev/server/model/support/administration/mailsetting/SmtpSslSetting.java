package io.onedev.server.model.support.administration.mailsetting;

import io.onedev.server.annotation.Editable;

import java.io.Serializable;
import java.util.Properties;

@Editable
public interface SmtpSslSetting extends Serializable {

	void configure(Properties properties);
	
}
