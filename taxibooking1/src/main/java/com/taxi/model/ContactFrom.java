package com.taxi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
@Table(name="contactfrom")
public class ContactFrom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Name cannot be Empty")
	@Size(min = 2, max = 30, message = "Invalid Name Size")
	@Column(length=30)
	private String name;

	@NotEmpty(message = "Email cannot be Empty")
	@Size(min = 2, max = 30, message = "invalid Email Size")
	@Column(length=50)
	private String email;

	@NotNull
	@Min(value = 1000000000, message = "Phone number must be 10 digits")
	@Max(value = 9999999999L, message = "Phone must be 10 digits")
	@Column(length=10)
	private Long phone;

	@NotEmpty(message = "message cannot be Empty")
	@Size(min = 5, max = 530, message = "Atleaste Write 5 characters and less than 530 charaters")
	@Column(length = 530)
	private String message;
}
