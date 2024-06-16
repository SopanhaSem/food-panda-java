package model.dto;

import lombok.Builder;
import model.entities.Customer;

import java.sql.Date;
@Builder
public record OrderDto(String order_name, String order_description, Customer customer, Date ordered_at)
{ }
