package aivars.adf.exception;

import aivars.adf.common.Resource;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final Resource resource;
    private final transient Object id;

    public NotFoundException(Resource resource, Object id) {
        this.resource = resource;
        this.id = id;
    }
}
