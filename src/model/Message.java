package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ticket_id")
	private Ticket ticket;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id")
	private User author;
	
	@NotNull
	private String content;
	
	@NotNull
	private Date created;
}
