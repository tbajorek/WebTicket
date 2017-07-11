package model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Invitation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	@NotNull
	private String email;
	
	@NotNull
	private String hash;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id")
	private Department department;

}
