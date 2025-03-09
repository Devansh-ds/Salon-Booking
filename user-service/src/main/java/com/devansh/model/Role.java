package com.devansh.model;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

public enum Role {
    CUSTOMER,
    OWNER,
    ADMIN
}
