package org.basic_crud.usingjdbc;

public record CustomerUpdateRequest (String name, String email, Integer age){}
