package com.it4895q.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostComment {

	@Id
	@GeneratedValue
	private Long id;

	private String review;

	private Date createAt;

	/**
	 * Relationship ManyToOne between PostComment and Post
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PostComment))
			return false;
		return id != null && id.equals(((PostComment) o).getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

}
