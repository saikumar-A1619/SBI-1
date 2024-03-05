package com.hcl.model;

import org.springframework.stereotype.Component;

@Component
public class SbiInput {

	SBIHeader header;
	SBIBody body;

	public SBIHeader getHeader() {
		return header;
	}

	public void setHeader(SBIHeader header) {
		this.header = header;
	}

	public SBIBody getBody() {
		return body;
	}

	public void setBody(SBIBody body) {
		this.body = body;
	}

}
