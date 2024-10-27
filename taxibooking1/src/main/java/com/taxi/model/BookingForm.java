package com.taxi.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="booking_from")
public class BookingForm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message="Name can't be Empty")
	@NotBlank(message="Name can't be Blank")
	@Size(min=2, max=30, message="Name length must more 2 and less than 30 charaters")
	@Pattern(regexp = "[A-Za-z]+", message="Name should contain only Alphabets")
	@Column(length= 30)
	private String name;
	
	@NotEmpty(message="From can't be Empty")
	@NotBlank(message="From can't be Blank")
	@Size(min=2, max=100, message="From length must more 2 and less than 100 charaters")
	@Column(length= 100, name="source")
	private String from;
	
	@NotEmpty(message="Email can't be Empty")
	@NotBlank(message="Email can't be Blank")
	@Size(min=7, max=100, message="email length must more 7 and less than 100 charaters")
	@Column(length= 100)
	private String email;
	
	@NotEmpty(message="To can't be Empty")
	@NotBlank(message="To can't be Blank")
	@Size(min=2, max=100, message="To length must more 2 and less than 100 charaters")
	@Column(name="destination", length=100)
	private String to;
	
	@NotNull(message="time Must Be selected")
	private LocalTime time;
	
	@NotNull(message="time Must Be selected")
	private LocalDate date;
	
	@NotEmpty(message="Chosse One")
	@Column(length = 20)
	private String comfort;
	
	@Min(value=1, message ="Atleast One Adult must Be selected")
	@Max(value=4, message ="Atmost only 4 Adult can Be selected")
	private int adult;
	

	@Max(value=3, message ="Atmost only 4 Adult can Be selected")
	private int children;
	
	@NotEmpty(message="Message can't be Empty")
	@NotBlank(message="Message can't be Blank")
	@Size(min=2, max=500, message="Message length must more than 5 and less than 500 charaters")
	@Column(length = 500)
	private String message;
	
}
