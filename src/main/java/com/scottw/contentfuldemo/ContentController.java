package com.scottw.contentfuldemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.CDAResource;

@RestController
//@RequestMapping("contentful-demo")
public class ContentController {

	
	private CDAClient getClient() {
		return CDAClient.builder()
		    .setSpace("uklhatfbpwlu")
		    .setToken("uHn2YXRKv8-QB3NEmkJzjrNh5VCzcjITAUs8OdYPWtY")
		    .build();
		
	}

	@RequestMapping(value = "/contentful-demo/products", method = RequestMethod.GET)
	public String getProducts() {

		CDAClient client = getClient();
		
		// Fetch entries
		CDAArray array = 
		    client
		        .fetch(CDAEntry.class)
		        .withContentType("product")
		        .include(1)
		        .all();
		
//		array.items().forEach((item) -> System.out.println(item.attrs().toString()));

	
		String output = "";
		
		for(CDAResource item : array.items()) {
			String entryId = item.id();

			Product product = client
				    .observeAndTransform(Product.class)
				    .one(entryId)
				    .blockingSingle();
		

			System.out.println(item.attrs().toString()+"\n\r");
			System.out.println(product.toString()+"\n\r");

			output = output + product.toString() + "<br><br>";
			
			//Doing this for now, as I don't know if you can unwrap an asset
			for(CDAAsset image: product.images) {
				System.out.println("image fields = " + image.id() + ", " + image.mimeType() + ", " + image.title() + ", " + image.url() + "\n\r");
				output = output + "image fields = " + image.id() + ", " + image.mimeType() + ", " + image.title() + ", " + image.url() + "\n\r" + "<br><br>";
			}

//			System.out.println("product details - id = " + entryId);
//			System.out.println(product.productDescription + "\n\r");
			
		}
		return output;
	}
	@RequestMapping(value = "/contentful-demo/categories", method = RequestMethod.GET)
	public String getCategories() {

		CDAClient client = getClient();
		
		// Fetch entries
		CDAArray array = 
		    client
		        .fetch(CDAEntry.class)
		        .withContentType("category")
		        .include(1)
		        .all();
		
//		array.items().forEach((item) -> System.out.println(item.attrs().toString()));

	
		
		String output = "";
		
		
		for(CDAResource item : array.items()) {
			String entryId = item.id();

			Category category = client
				    .observeAndTransform(Category.class)
				    .one(entryId)
				    .blockingSingle();
		

			System.out.println(item.attrs().toString()+"\n\r");
			System.out.println(category.toString()+"\n\r");

			output = output + category.toString() + "<br><br>";
			
			//Doing this for now, as I don't know if you can unwrap an asset
			System.out.println("icon fields = " + category.icon.id() + ", " + category.icon.mimeType() + ", " + category.icon.title() + ", " + category.icon.url());

			output = output + "icon fields = " + category.icon.id() + ", " + category.icon.mimeType() + ", " + category.icon.title() + ", " + category.icon.url() + "<br><br>";

//			System.out.println("product details - id = " + entryId);
//			System.out.println(product.productDescription + "\n\r");
			
		}
		return output;
	}
	@RequestMapping(value = "/contentful-demo/brands", method = RequestMethod.GET)
	public String getBrands() {

		CDAClient client = getClient();
		
		// Fetch entries
		CDAArray array = 
		    client
		        .fetch(CDAEntry.class)
		        .withContentType("brand")
		        .include(1)
		        .all();
		
//		array.items().forEach((item) -> System.out.println(item.attrs().toString()));

	
		String output = "";
		
		
		for(CDAResource item : array.items()) {
			String entryId = item.id();

			Brand brand = client
				    .observeAndTransform(Brand.class)
				    .one(entryId)
				    .blockingSingle();
		

			System.out.println(item.attrs().toString()+"\n\r");
			System.out.println(brand.toString()+"\n\r");

			output = output + brand.toString() + "<br><br>";
			

			//Doing this for now, as I don't know if you can unwrap an asset
			System.out.println("logo fields = " + brand.logo.id() + ", " + brand.logo.mimeType() + ", " + brand.logo.title() + ", " + brand.logo.url());

			output = output + "logo fields = " + brand.logo.id() + ", " + brand.logo.mimeType() + ", " + brand.logo.title() + ", " + brand.logo.url() +
"<br><br>";
			

//			System.out.println("product details - id = " + entryId);
//			System.out.println(product.productDescription + "\n\r");
			
		}
		return output;
	}
}
