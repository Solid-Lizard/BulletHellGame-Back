package com.bulelthell.game.bullethellbackend.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailRequestRest {
	@JsonProperty("subject")
	private String subject;
	
	@JsonProperty("text")
	private String text;

}
