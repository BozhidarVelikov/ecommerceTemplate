package com.bojidarET.ecommerceTemplate.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="image_uri")
public class ImageUri {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String uri;
	
	private boolean mainImage;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn()
	private Product product;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public boolean isMainImage() {
		return mainImage;
	}

	public void setMainImage(boolean mainImage) {
		this.mainImage = mainImage;
	}
}
