package edu.luc.fall2014.comp433.project.rest.representation;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class BookstoreURI {

	// TODO remove hard coded
	private String cxfUrlPattern = "/rest";
	private String servletContext = "/project";
	private URI uri;

	public BookstoreURI(String uri) {
		try {
			this.uri = new URI(uri);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	public BookstoreURI(URI uri) {
		this(uri.toString());
	}

	public BookstoreURI(String uri, String identifier) {
		this(uri.toString() + "/" + identifier);
	}

	public BookstoreURI(URI uri, String identifier) {
		this(uri.toString() + "/" + identifier);
	}

	public BookstoreURI(String uri, String pathWithMultipleParam,
			Map<String, String> params) {
		for (Map.Entry<String, String> entry : params.entrySet()) {
			pathWithMultipleParam = pathWithMultipleParam.replace(
					entry.getKey(), entry.getValue());
		}
		try {
			this.uri = new URI(uri + "/" + pathWithMultipleParam);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	public String getSimpleIdentifier() {
		String path = uri.getPath();
		return path.substring(path.lastIndexOf("/") + 1, path.length());
	}

	public String getBaseUri() {
		String port = "";
		if (uri.getPort() != 80)
			port = ":" + uri.getPort();
		return uri.getScheme() + "://" + uri.getHost() + port + servletContext
				+ cxfUrlPattern;
	}

	public URI getFullUri() {
		return uri;
	}

	public String getCustomerPath() {
		return getCustomerPath(null);
	}

	public String getCustomerPath(String pathParam) {
		String path = getBaseUri() + "/customers";
		if (null != pathParam)
			path = path + "/" + pathParam;
		return path;
	}

	public String getOrderPath() {
		return getOrderPath(null);
	}

	public String getOrderPath(String pathParam) {
		String path = getBaseUri() + "/orders";
		if (null != pathParam)
			path = path + "/" + pathParam;
		return path;
	}

	public String getCancelOrderPath(String orderId) {
		if (null == orderId) {
			throw new IllegalArgumentException("id cannot be null");
		}
		return getOrderPath(orderId) + "/cancel";
	}

	public String getOrderStatusPath(String orderId) {
		if (null == orderId) {
			throw new IllegalArgumentException("id cannot be null");
		}
		return getOrderPath(orderId) + "/status";
	}

	public String getOrderByCustomerPath(String customerId) {
		if (null == customerId) {
			throw new IllegalArgumentException("id cannot be null");
		}
		return getOrderPath() + "/customers/" + customerId;
	}

	public String getBookPath() {
		return getBookPath(null);
	}

	public String getBookPath(String bookId) {
		String path = getBaseUri() + "/books";
		if (null != bookId)
			path = path + "/" + bookId;
		return path;
	}

	public String getBookDescriptionPath(String bookId) {
		if (null == bookId) {
			throw new IllegalArgumentException("id cannot be null");
		}
		return getBookPath(bookId) + "/description";
	}

	public String getAddressPath(String addressId) {
		String path = getBaseUri() + "/addresses";
		if (null != addressId)
			path = path + "/" + addressId;
		return path;
	}

	public String getAddressByCustomerPath(String customerId) {
		return getAddressPath() + "/customers/" + customerId;
	}

	public String getAddressPath() {
		return getAddressPath(null);
	}

	public String getPaymentPath(String paymentId) {
		String path = getBaseUri() + "/payments";
		if (null != paymentId)
			path = path + "/" + paymentId;
		return path;
	}

	public String getPaymentPath() {
		return getPaymentPath(null);
	}

}
