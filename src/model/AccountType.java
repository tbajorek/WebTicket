package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class AccountType implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	@NotNull
	private String name;
	
	@OneToMany(mappedBy = "type")
	private List<User> users;

}
