package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Milestone implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ticket_id")
	private Ticket ticket;
	
	@NotNull
	private String name;
	
	@NotNull
	private Boolean done;
}
