package kz.project.Blog.exceptions;

import kz.project.Blog.shared.utils.codes.ErrorCode;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceException extends RuntimeException {

    protected String message;
    protected ErrorCode errorCode;
}
