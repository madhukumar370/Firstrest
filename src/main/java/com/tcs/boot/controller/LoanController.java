package com.tcs.boot.controller;



import org.springframework.http.*;
 
import java.util.HashMap;

import java.util.List;

import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.RestController;
 
import com.tcs.boot.entity.Loan;

import com.tcs.boot.service.LoanService;
 
@RestController

@RequestMapping("/loan/api/v1.0")

public class LoanController {
 
	@Autowired

	LoanService service;
 
	// http://localhost:9999//loan/api/v1.0/create

	/*

	 * @PostMapping("/create") public Loan loanApplication(@RequestBody Loan loan) {

	 * return service.addLoan(loan); }

	 */

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Loan> loanApplication(@RequestBody Loan loan) {

		// return service.addLoan(loan);

		// return new ResponseEntity<>(HttpStatus.CREATED);

		Loan newLoan = service.addLoan(loan);

		// return new ResponseEntity<Loan>(newLoan,HttpStatus.OK);

		HttpHeaders headers = new HttpHeaders();

		headers.add("xx-created-by", "madhu");

		headers.add("content-type", "application/json");// MIME type

		// return new ResponseEntity<Loan>(newLoan,headers,HttpStatus.OK);

		return ResponseEntity.ok(newLoan);

	}
 
	// http://localhost:9999//loan/api/v1.0/fetch/1

	/*

	 * @GetMapping("/fetch/{id}") public Loan getLoan(@PathVariable Long id) {

	 * return service.getLoan(id); }

	 */

	@GetMapping("/fetch/{id}")

	public ResponseEntity<Loan> getLoan(@PathVariable Long id) {

		// do this when id isn't found

		// return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //--set the values and

		// send

		// return ResponseEntity.status(400).body(null); //creating body set and send

		// static method
 
		Loan loan = service.getLoan(id);

		// if(false)

		if (loan != null) {

			return new ResponseEntity<>(service.getLoan(id), HttpStatus.OK);

		} else {

			throw new IllegalArgumentException("Invalid loan id" + id);

		}

	}
 
	@GetMapping("/all")

	public List<Loan> getLoans() {

		return service.getLoans(); 

	}
 
	@PutMapping("/modify") // for complete update

	public Loan doUpdate(@RequestBody Loan loan) {

		return service.update(loan);

	}
 
	@PutMapping("/modify2") // for complete update

	public Loan doUpdate2(@RequestBody Loan loan) {

		return service.update2(loan);

	}
 
	/*

	 * @DeleteMapping("/remove/{id}") public void delete(@PathVariable Long id) {

	 * service.remove(id); }

	 */

	@DeleteMapping("/remove/{id}")

	public ResponseEntity<Void> delete(@PathVariable Long id) {

		service.remove(id);
 
		return new ResponseEntity<>(HttpStatusCode.valueOf(204));

	}
 
	/*

	 * //in Rest Controller

	 * 

	 * @ExceptionHandler(value = {IllegalArgumentException.class})

	 * 

	 * @ResponseStatus(HttpStatus.BAD_REQUEST) public

	 * ResponseEntity<Map<String,String>> handleHandler(IllegalArgumentException

	 * ex){ Map<String,String> errorMap = new HashMap<>();

	 * 

	 * errorMap.put("Loanid", ex.getMessage()); return

	 * ResponseEntity.status(400).body(errorMap); }

	 */
 
}

 
