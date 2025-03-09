package com.devansh.exception;

import java.util.HashMap;

public record ErrorResponse(
        HashMap<String, String> error
) {
}
